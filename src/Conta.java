import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Conta implements ITaxas, Serializable {

    //----------------------------------------- Atribultos Classe ------------------------------------------------//

    public static Integer numContasCriadas = 0;

    public static Integer numOperacoesContas;
    List<Operacao> operacoesConta = new ArrayList<>();

    private  Integer numConta;
    private Cliente cliente;
    private double saldoConta;
    private double limiteSaldoMinConta;
    private double limiteMovimentacaoConta;

    //---------------------------------------- Fim Atribultos Classe ---------------------------------------------//



    //------------------------------------------ Metodo Construtor -----------------------------------------------//

    public Conta(Cliente cliente, double saldoInicial){
        setNumConta(10000 + numContasCriadas);
        setCliente(cliente);

        if ((saldoInicial >= 0)) setSaldoConta(saldoInicial);
        else setSaldoConta(0);

        numContasCriadas++;
        numOperacoesContas = 0;
    }

    //---------------------------------------- Fim Metodo Construtor ---------------------------------------------//



    //--------------------------------------------- Metodos Get --------------------------------------------------//

    public Integer getNumConta() {
        return numConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getSaldoConta() {
        return saldoConta;
    }

    public double getLimiteSaldoMinConta() {
        return limiteSaldoMinConta;
    }

    public double getlimiteMovimentacaoConta() {
        return limiteMovimentacaoConta;
    }

    public Integer getNumOperacoesContas(){
        return numOperacoesContas;
    }

    //-------------------------------------------- Fim Metodos Get -----------------------------------------------//



    //--------------------------------------------- Metodos Set --------------------------------------------------//

    /**
     * Altera numero da conta;
     * @param numConta Novo numero de conta;
     */
    protected void setNumConta(Integer numConta) {
        this.numConta = numConta;
    }

    /**
     * Altera dados do cliente;
     * @param cliente Objeto contendo dados do cliente;
     */
    protected void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Altera valor de saldo em conta;
     * @param saldoConta Valor de saldo novo;
     */
    protected void setSaldoConta(double saldoConta) {
        this.saldoConta = saldoConta;
    }

    /**
     * Altera valor minimo de permanência de salda em conta;
     * @param limiteSaldoMinConta Valor minimo de permanência;
     */
    protected void setLimiteSaldoMinConta(double limiteSaldoMinConta) {
        this.limiteSaldoMinConta = limiteSaldoMinConta;
    }

    /**
     * Altera valor de limite de movimentacao da conta;
     * @param limiteMovimentacaoConta valor maximo de movimentacao;
     */
    protected void setLimiteMovimentacaoConta(double limiteMovimentacaoConta) {
        this.limiteMovimentacaoConta = limiteMovimentacaoConta;
    }

    /**
     * Atualiza numero de operações;
     */
    protected void setNumOperacoesContas(){
        numOperacoesContas = operacoesConta.size();
    }

    //-------------------------------------------- Fim Metodos Set ------------------------------------------------//



    //--------------------------------------------- Definição Conta -----------------------------------------------//

    /**
     * Função usada para definir limites para contas;
     * @param limiteMovimentacao limite solicitado;
     */
    protected abstract void definirLimites(double limiteMovimentacao);

    /**
     * Valida que limite solicitado de movimentacao seja possitivo ou zero;
     * @param valorSolicitado valor do limite solicitado;
     * @return valor do limite aprovado de movimentacao;
     */
    protected abstract double validarLimiteMovimentacao(double valorSolicitado);

    /**
     * Função usada para solicitar novamente o valor do limite de movimentação;
     * @return valor valido de limite de movimentação;
     */
    protected abstract double solicitarNovoValor();

    /**
     * Usada para redefinir valor do limite de movimentação;
     */
    protected abstract  void redefinirValorLimite();

    //------------------------------------------- Fim Definição Conta ---------------------------------------------//



    //-------------------------------------- Metodos Usabilidade Conta --------------------------------------------//

    /**
     * Função para realizar saques em conta;
     * @param valorSaque valor solicitado de saque;
     * @param senha senha para validar operação;
     * @return status final de operação: True = se saque realizado/ False = se saque negado;
     */
    public boolean saque(double valorSaque, String senha) throws ValorNegativoException, SemLimiteException, FileNotFoundException, IOException{
        boolean retorno, SALDO_INSUFICIENTE, VALOR_INVALIDO;

        retorno = validarOperacao(senha);

        if(retorno){

            VALOR_INVALIDO = (valorSaque < 0 || valorSaque > limiteMovimentacaoConta);
            SALDO_INSUFICIENTE = (valorSaque > (this.getSaldoConta() - limiteSaldoMinConta));

            if(SALDO_INSUFICIENTE){
                System.out.println("\n___IMPOSSIVEL REALIZAR OPERACAO___\n___SALDO INSUFICIENTE___");
                retorno =  false;

            }else if(VALOR_INVALIDO){
                if(valorSaque < 0){
                    throw new ValorNegativoException("ERRO: valor invalido");
                }else {
                    throw new SemLimiteException("ERRO: valor acima do limite da conta");
                }
            }else{
                operacoesConta.add(new OperacaoSaque(valorSaque));

                int indexUltimaOp = operacoesConta.size() - 1;
                double taxa = operacoesConta.get(indexUltimaOp).calcularTaxas();
                this.setSaldoConta(this.getSaldoConta() - (valorSaque + taxa));

                this.setNumOperacoesContas();
            }
        }else{
            System.out.println("___IMPOSSIVEL REALIZAR OPERACAO NO MOMENTO - TENTE NOVAMENTE MAIS TARDE___");
        }

        this.salvaConta();
        return retorno;
    }

    /**
     * Função para realizar o depósito de um valor;
     * @param valorDeposito valor a ser depositado.
     * @return true = caso deposito realizado // false = caso ocorra erro.
     */
    public boolean depositar(double valorDeposito) throws ValorNegativoException, FileNotFoundException, IOException{
        boolean retorno = true, VALOR_VALIDO;

        VALOR_VALIDO = (valorDeposito > 0);

        if(VALOR_VALIDO){
            operacoesConta.add(new OperacaoDeposito(valorDeposito));

            int indexUltimaOp = operacoesConta.size() - 1;
            double taxa = operacoesConta.get(indexUltimaOp).calcularTaxas();
            this.setSaldoConta(this.getSaldoConta() + (valorDeposito - taxa));

            this.setNumOperacoesContas();
        }else{
            throw new ValorNegativoException("ERRO: valor invalido");
        }

        this.salvaConta();
        return retorno;
    }

    /**
     * Função para realizar transferência de um valor x entre contas.
     * @param destinatario Endereço Conta destino.
     * @param valorTransferencia Valor a ser tranferido
     * @param senha senha para validar operação;
     * @return true = se tranferencia realizada // false = caso ocorra erros.
     */
    public boolean tranferencia(Conta destinatario, double valorTransferencia, String senha) throws ValorNegativoException, SemLimiteException, FileNotFoundException, IOException {
        boolean retorno;


        retorno = saque(valorTransferencia, senha); //Tenta realizar o saque do valor.

        if (retorno) {

            retorno = destinatario.depositar(valorTransferencia); // Se sacou, tenta depositar na conta destino.

            if(!retorno){ // Se não conseguiu depositar, cancela operação;
                int indexUltimaOp = operacoesConta.size() - 1;
                double taxa = operacoesConta.get(indexUltimaOp).calcularTaxas();

                operacoesConta.remove(indexUltimaOp);

                this.setSaldoConta(valorTransferencia + taxa);
            }
        }

        return retorno;
    }

    //------------------------------------ Fim Metodos Usabilidade Conta ------------------------------------------//



    //----------------------------------------- Metodos de Validação ----------------------------------------------//

    /**
     * Valida operação com a senha informada pelo cliente;
     * @return True = Senha verificada, False = Senha Incorreta;
     */
    public boolean validarOperacao(String senha){
        boolean retorno = this.cliente.autenticarChave(senha);
        return retorno;
    }

    //--------------------------------------- Fim Metodos de Validação --------------------------------------------//



    //-------------------------------------------- Metodos Impressao ----------------------------------------------//

    /**
     * Metodo Redefinido para retornar caracteristicas da Conta.
     * @return String com caracteristicas.
     */
    public String toString(){
        this.setNumOperacoesContas();
        return cliente.toString() + "\n====== DADOS CONTA ======" +
                                    "\nNUMERO: " + getNumConta() +
                                    "\nSALDO DISPONIVEL: " + getSaldoConta() +
                                    "\nLIMITE MOVIMENTACAO: " + getlimiteMovimentacaoConta() +
                                    "\nNUMERO OPERACOES: " + this.getNumOperacoesContas() +
                                    "\n=========================";
    }

    /**
     * Exibe dados basicos da conta;
     */
    public void exibirTitularConta(){
        System.out.println("\n====== DADOS CONTA ======");
        System.out.println("NUMERO: " + getNumConta());
        System.out.println("TITULAR: " + getCliente().getNome());
        System.out.println("=========================");
    }

    /**
     * Imprime Extrato de Taxas da conta;
     */
    public void extratoTaxas(){
        double taxas = this.calcularTaxas();

        exibirTitularConta();
        System.out.println("===== EXTRATO TAXAS =====");

        for(Operacao atual : this.operacoesConta){
            if(atual instanceof OperacaoSaque){
                taxas += atual.calcularTaxas();
                atual.imprimirExtratoTaxas();
            }
        }

        System.out.println("TOTAL: R$ " + taxas);
        System.out.println("=========================");
    }

    /**
     * Imprime o extrato da conta com base num ordenamento;
     * @param ordenamento !0 = Padrão (Data)/ 0 = Tipo (Depositos/Saques)
     */
    public void imprimirExtato(int ordenamento){
        exibirTitularConta();

        operacoesConta.get(0).setMedotoComparacao(ordenamento);
        Collections.sort(operacoesConta);

        for (Operacao atual : operacoesConta)
            System.out.println(atual.toString());

        System.out.println("=========================");
    }

    public void salvaConta () throws FileNotFoundException, IOException{
        FileOutputStream fStream = new FileOutputStream("0001-" + getNumConta() + ".ser");
        ObjectOutputStream outputStream = new ObjectOutputStream(fStream);
        outputStream.writeObject(this);
        outputStream.close();
    }

    //------------------------------------------- Fim Metodos Impressao -------------------------------------------//


}
