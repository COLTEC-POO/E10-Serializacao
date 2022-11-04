import java.io.Serializable;
import java.util.ArrayList;

public abstract class Conta implements Serializable{

    //atributos
    public static Integer qtdContas = 0;

    private Agencia agencia;
    private Cliente cliente;
    private Integer numConta;
    private Double saldo;
    private Double limite;
    private Boolean tipo;
    private ArrayList<Operacao> operacoes = new ArrayList<Operacao>();


    // Construct para iniciar a conta

    /**
     * @param cliente
     * @param tipo
     * @param saldo_inicial
     * @param limite
     * @param numConta
     */
    public Conta(Cliente cliente, Boolean tipo, Double saldo_inicial, Double limite, Integer numConta, Agencia agencia){
        try{
            validaCliente(cliente);
            validaTipo(tipo);
            validaSaldo(saldo_inicial);
            validaLimite(limite);
            validaNumConta(numConta);
            setAgencia(agencia);
            qtdContas++;
        }catch (Exception e) {
            System.out.println("Dados invalidos!");
        }
    }

    public Conta(){
        
    }

    //Getters
    public Cliente cliente(){
        return this.cliente;
    }

    public Boolean getTipo(){
        return this.tipo;
    }

    public Double getSaldo(){
        return this.saldo;
    }

    public Double getLimite(){
        return this.limite;
    }

    public Integer getNumConta(){
        return this.numConta;
    }

    public Double getMedia(){
        return 1.0 * operacoes.size() / qtdContas;
    }
    public Integer getQtdOperacao(){
        return this.operacoes.size();
    }
    public Agencia getAgencia(){
        return this.agencia;
    }
    public String getAgenciaConta(){
        return this.agencia.getAgencia() + "-" + this.numConta;
    }
    //FIM Getters

    //Setters
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public void setTipo(Boolean tipo){
        this.tipo = tipo;
    }

    public void setSaldoInicial(Double saldo){
        this.saldo = saldo;
    }

    public void setLimite(Double limite){
        if (limite < 0) {
            throw new IllegalArgumentException("Valor para Limite invalido, favor colocar um numero positivo");
        }
        this.limite = limite;
    }

    public void setNumConta(Integer numConta){
        this.numConta = numConta;
    }

    public void setAgencia(Agencia agencia){
        this.agencia = agencia;
    }
    //FIM Setters

    //Validadores de dados
    public void validaCliente(Cliente cliente){
        setCliente(cliente);
    }

    public void validaTipo(Boolean tipo){
        setTipo(tipo);
    }

    public void validaSaldo(Double saldo){
        if (saldo >= 0)
            setSaldoInicial(saldo);
    }

    public void validaLimite(Double limite){
        try{
            setLimite(0.0);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public void validaNumConta(Integer numConta){
        setNumConta(numConta);
    }
    //FIM Validadores


    //Operações com as contas
    public void imprimirSaldo() {
        System.out.println("Conta " + this.numConta);
        System.out.println("Saldo: R$" + this.saldo);
    };

    public Boolean sacar(Double valor) {
      if (valor > saldo || valor < 0) {
          return false;
      } else {
          this.saldo-= valor;
          System.out.println("Valor sacado: R$" + valor);
          System.out.println("Novo Saldo: R$" + this.saldo);
          operacoes.add(new OperacaoSaque(valor)); 
          return true;
      }
    }

    public Boolean depositar(Double valor) throws NegativeValueException{
        if (valor < 0){
            throw new NegativeValueException("Apenas numeros possitivos são validos para um deposito");
        } else {
            this.saldo += valor;
            System.out.println("Valor depositado: R$" + valor);
            System.out.println("Novo Saldo: R$" + this.saldo);
            operacoes.add(new OperacaoDeposito(valor));
            return true;
        }
    }

    public Boolean transferir(Conta destino, Double valor) throws InvalidLimitValueException{
        try{
            if (this.sacar(valor)) {
                destino.depositar(valor);
                return true;
            } else {
                throw new InvalidLimitValueException("Transferencia incompleta, saldo disponivel: R$" + this.saldo);
            }
        } catch(NegativeValueException e){
            e.printStackTrace();
        }
        return false;
    }

    public void mostraConta(){
        System.out.println("Conta " + this.numConta);
        System.out.println("Proprietário " + this.cliente.getNome());
        System.out.println("Saldo: R$" + this.saldo + " Limite: R$ " + this.limite);
    }

    public void mostraConta(Boolean compelta){
        System.out.println("Conta " + this.numConta);
        System.out.println("Saldo: R$" + this.saldo + " Limite: R$ " + this.limite);
        cliente.mostraCliente();
    }

    public void mostraExtrato(){
        System.out.println("Conta " + this.numConta + " Titular: " + this.cliente.getNome() + " Saldo: R$ " + this.saldo);
        if (operacoes.size() != 0){
            for (Operacao o : operacoes){
                if (o!= null){
                    o.extrato();
                }
            }
            System.out.println("Quantidade de Operações: " + this.operacoes.size());
        }
        else{
            System.out.println("Não há operações");
        }
    }

    public void mostraExtrato(Integer separado){
        System.out.println("Agencia: " + this.agencia.toString() + " Conta " + this.numConta + " Titular: " + this.cliente.getNome() + " Saldo: R$ " + this.saldo);
        if (operacoes.size() != 0){
            ArrayList<Operacao> saques = new ArrayList<Operacao>();
            ArrayList<Operacao> depositos = new ArrayList<Operacao>();

            //Collections.sort(operacoes);

            for (Operacao o : operacoes){
                if (o!= null){
                    if (o instanceof OperacaoSaque)
                        saques.add(o);
                    else
                        depositos.add(o);
                }
            }

            for (Operacao o : depositos){
                if (o!= null){
                    o.extrato();
                }
            }
            System.out.println("Depositos: " + depositos.size());

            for (Operacao o : saques){
                if (o!= null){
                    o.extrato();
                }
            }
            System.out.println("Saques: " + saques.size());

            System.out.println("Quantidade de Operações: " + this.operacoes.size());
        }
        else{
            System.out.println("Não há operações");
        }
    }

    public abstract Double calculaTaxas();

    public void imprimeExtratoTaxas(){
        System.out.println("\n=== Extrato de Taxas ===");

        System.out.println("\nManutenção de Conta: R$" + this.calculaTaxas());

        System.out.println("\n\nOperações");
        for (ITaxas operacao : operacoes) {
            if (operacao.calculaTaxas() != 0.0)
                System.out.println("Saque: " + operacao.calculaTaxas());
        }


    }
    //FIM Operações com as contas

}