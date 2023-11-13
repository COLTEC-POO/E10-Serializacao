import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public abstract class Conta implements ITaxas, Serializable{

    protected int numero;
    protected int agencia;
    protected String senha;
    protected double saldo;
    protected Cliente dono;
    protected double limite;
    private ArrayList<Operacao> operacoes;


    protected static int totalContas = 0;


    public String toString(){


        String ContaStr =
                "========== Conta =========" +"\n" +
                        "Numero da Conta: " + this.numero + "\n" +
                        "Numero da Agencia: " + this.agencia + "\n" +
                        "Saldo: " + this.saldo + "\n" +
                        "Dono: " + this.dono.nome + "\n" +
                        "Limte: " + this.limite + "\n" +
                        "==========================" + "\n";

        return ContaStr;
    }


    public Conta(int numero, int agencia, String senha, double saldo,Cliente dono,double limite){

        this.numero = numero;
        this.agencia = agencia;
        this.senha = senha;
        this.saldo = saldo;
        this.dono = dono;
        this.limite = limite;

        operacoes = new ArrayList<>();


        // Conta quantas novas contas já foram criadas
        totalContas++;

    }


    //public TIPO_RETORNO NOME_DO_MÉTODO(TIPO VARI1, TIPO VARI2,...){
    //}

    public void depositar(double valor) throws ValorNegativoException {

        if (valor < 0){
            throw new ValorNegativoException("Valor do deposito negativo: " + valor);
        }
        this.saldo += valor;
        this.operacoes.add(new OperacaoDeposito(valor));


    }

    public boolean sacar(double valor) throws ValorNegativoException {

        if (valor < 0){
            throw new ValorNegativoException("Valor de saque negativo: " + valor);
        }

        if(valor <= this.saldo) {
            this.saldo -= valor;

            this.operacoes.add(new OperacaoSaque(valor));
            return true;
        }
        else{
            return false;
        }


    }

    public void transferir(double valor, Conta destino) throws ValorNegativoException{
        if(this.sacar(valor)){
            destino.depositar(valor);

        }
        else{
            System.out.println("Nao foi possivel realizar a transferencia!");
        }
    }



    public void imprimirExtrato(){

        System.out.println("Extrato ordenado em ordem de insercao: ");
        System.out.println("\n");

        for(Operacao operacaoAtual : this.operacoes){
            System.out.println(operacaoAtual.getData() + "\t" + operacaoAtual.getTipo() + "\t" + operacaoAtual.getValor());
        }

        System.out.println("\n");
        System.out.println("Extrato ordenados pelos tipos: ");
        System.out.println("\n");

        Collections.sort(operacoes);

        for(Operacao operacaoAtual : this.operacoes){
            System.out.println(operacaoAtual.getData() + "\t" + operacaoAtual.getTipo() + "\t" + operacaoAtual.getValor());
        }

    }

    public boolean equals(Object outraC){

        if(outraC instanceof Conta){
            Conta outraConta = (Conta) outraC;
            return this.getNumero() == outraConta.getNumero();
        } else{
            return false;
        }
    }

    public void imprimirExtratoTaxas(){
        double totalTaxasOperacoes = 0.0;

        double manutencao = calculaTaxas();

        System.out.println("==== Extrato de Taxas ====");
        System.out.println("Manutencao da conta: " + manutencao);

        for(Operacao operacaoAtual : this.operacoes){

            double taxa = 0.0;

            if(operacaoAtual instanceof ITaxas){
                ITaxas operacaoComTaxas = (ITaxas) operacaoAtual;
                taxa = operacaoComTaxas.calculaTaxasOperacao();
            }

            if(operacaoAtual.getTipo() == 's'){

                System.out.println("Saque: " + taxa);
            }
            if(operacaoAtual.getTipo() == 'd'){
                System.out.println("Deposito: " + taxa);
            }
            totalTaxasOperacoes += taxa;
        }
        System.out.println("\nTotal: " + (manutencao + totalTaxasOperacoes));
    }

    public abstract void setLimite();


    public void salvarConta() {
        String Arquivo = this.getAgencia() + "-" + this.getNumero() + ".ser";

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Arquivo))) {
            outputStream.writeObject(this);
            System.out.println("Sua conta salva com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar a conta: " + e.getMessage());
        }
    }

    public static Conta carregarConta(int agencia, int numero) {
        String Arquivo = agencia + "-" + numero + ".ser";

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(Arquivo))) {
            Conta conta = (Conta) inputStream.readObject();
            System.out.println("Conta carregada com sucesso do arquivo: " + Arquivo);
            return conta;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar a conta: " + e.getMessage());
            return null;
        }
    }


    // DONO
    public Cliente getDono(){
        return this.dono;
    }
    public Cliente setDono(){
        return this.dono;
    }

    // NUMERO
    public int getNumero(){
        return this.numero;
    }
    public int setNumero(){
        return this.numero;
    }

    // AGENCIA
    public int getAgencia(){
        return this.agencia;
    }
    public int setAgencia(){
        return this.agencia;
    }

    // SALDO
    public double getSaldo(){
        return this.saldo;
    }

    //LIMITE
    public double getLimite(double limite){

        return this.limite;
    }

    // TOTAL DE CONTAS

    public static int getTotalContas(){
        return totalContas;
    }

}
