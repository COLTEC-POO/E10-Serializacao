import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;


public abstract class Conta implements ITaxas, Serializable{

    protected int numero;  
    protected int agencia;
    protected Cliente dono;
    protected double saldo;
    protected double limite;
    public static double totalContas = 0;
    private ArrayList <Operacao> operacoes = new ArrayList<>();


    // Método construtor da classe Conta

        Conta(int numero, int agencia, Cliente dono, double saldo) {
        setNumero(numero);
        setAgencia(agencia);
        setDono(dono);
        setSaldo(saldo);

        Conta.totalContas++;
    }

    // Getters e setters

    public Cliente getDono(){
        return this.dono;
    }

    public void setDono(Cliente dono){
        this.dono = dono;
    }

    public int getNumero(){
        return this.numero;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public double getSaldo (){
        return this.saldo;
    }

    public void setSaldo(double saldo){
        this.saldo = saldo;
    }

    public double getLimite(){
        return this.limite;
    }


    // Método abstrato para a definição de limites de cada tipo de conta

    public abstract void setLimite (double limite);


    // Método para sacar um valor de uma conta

    public void sacar (double valor) throws ValorNegativoException, SemLimiteException {

        if (valor <= 0) {
            throw new ValorNegativoException("Erro ao tentar sacar o valor de " + valor);
        }

        if (this.saldo < valor) {
            throw new SemLimiteException("Valor indisponível na conta de " + getDono().getNome());
        }

        this.saldo = saldo - valor;
        System.out.println("Saque de " + valor + " realizado com sucesso!");


        if (this.operacoes.size() < 1000) {
        this.operacoes.add(new OperacaoSaque(valor));
        } else {
            System.out.println("Numero de operacoes excedido!");
        }

        System.out.println("Saldo atual da conta de " + getDono().getNome() + ": " + getSaldo()); 
    }


    // Método para depositar um valor em uma conta.

    public void depositar (double valor) throws ValorNegativoException {

        if (valor <= 0) {
            throw new ValorNegativoException("Erro ao tentar depositar o valor de " + valor);
        }
        this.saldo += valor;
        System.out.println(valor + " depositado com sucesso!");
        System.out.println("Saldo atual da conta de " + getDono().getNome() + ": " + getSaldo());

        if (this.operacoes.size() < 1000) {
            this.operacoes.add(new OperacaoDeposito(valor));
        } else {
            System.out.println("Numero de operacoes excedido!");
        }


    }


    // Método para transferir um valor de uma conta para outra.

    public void transferir (Conta destino, double valor) throws ValorNegativoException, SemLimiteException {

        if (valor <= 0) {
            throw new ValorNegativoException("Não é possível transferir valores negativos!");
        }

        if (this.saldo < valor){
            throw new SemLimiteException("Saldo insuficiente!");
        }

        this.saldo -= valor;
        destino.saldo += valor;
        System.out.println("Valor transferido da conta de " + getDono().getNome() + " para a conta de " + getDono().getNome() + ": " + valor);
        System.out.println("Saldo atual da conta de " + getDono().getNome() + ": " + getSaldo());
        System.out.println("Saldo atual da conta de " + getDono().getNome() +  ": " + destino.saldo);   


    }

    // Método que calcula quais e quantas operações foram realizadas na conta

    public void extrato(int flag){

        switch (flag) {
            case 0: { 
                System.out.println("Extrato ordenado de acordo com a data de cada operação:");
                for (int i = 0; i < this.operacoes.size(); i++) {
                Operacao x = this.operacoes.get(i);
                System.out.println(x.toString());
                } 
                break;
            }
            case 1: {
                System.out.println("Extrato ordenado de acordo com o tipo de operação:");

                Collections.sort(operacoes); // Tentei clonar a arraylist operacoes utilizando ArrayList<Operacao> cloneToSort = (ArrayList<Operacao>)operacoes.clone(); mas tava dando warning de type safety

                for (Operacao sorted : operacoes){
                    System.out.println(sorted);
                }
                break;
            }
            default: {
                System.out.println("Tipo de ordenação inválido!");
                break;
            }
        }
    }

    // Método que sobrescreve a função toString da class Object e transforma tudo em uma string - Atividade 05

    @Override
    public String toString(){
        String dadosConta = 
                    "Dados da conta: " + "\n" + 
                    "Numero da conta: " + this.getNumero() + "\n" +
                    "Agencia: " + this.getAgencia() +"\n" +
                    "Dono da conta: " + this.getDono().getNome() + "\n" +
                    "Saldo: " + this.getSaldo() + "\n" +
                    "Limite: " + this.getLimite() + "\n";

        return dadosConta;
    }

    // Método que sobrescreve a função equals da class Object e compara se dois objetos são iguais - Atividade 05

    @Override
    public boolean equals(Object obj) {

        if(obj instanceof Conta) {
            Conta outraConta = (Conta) obj;
            if (this.getNumero() == outraConta.getNumero()){ // Por se tratar de um tipo primitivo (int) é possível utilizar o == em vez de this.getNumero().equals.etc
            return true;
            } else {
            return false;
            }
        } else {
            return false;
        }
    }

    public void imprimeExtratoTaxas() {

        double total = 0.0;
        double manutencao = this.calculaTaxas();

        System.out.println("\n=== Extrato de Taxas ===");
        System.out.println("Manutenção da conta: " + manutencao);
        System.out.println();

        for (int i = 0; i < this.operacoes.size(); i++) {
            Operacao x = this.operacoes.get(i);
            if (x instanceof OperacaoSaque) {
                System.out.println("Saque: " + x.calculaTaxas());
                total += x.calculaTaxas();
            }
        }

        System.out.println("\nTotal: " + String.format("%.2f", (total+manutencao)));
        System.out.println();
    }

    // Método para gravar uma conta - Exercício 10

    public String formatAgenciaConta(){
        String fileName = this.getAgencia() +"-"+ this.getNumero()+ ".ser";
        return fileName;
    }

    public void serializeAccount() {
        String fileName = this.formatAgenciaConta();

        try {
            FileOutputStream fStream = new FileOutputStream(fileName);
            ObjectOutputStream oStream = new ObjectOutputStream(fStream);

            oStream.writeObject(this);

            System.out.println("Conta salva no arquivo " + fileName);

            oStream.close();
        } catch (IOException ioe){
            System.out.println("Erro na serialização da conta: " + ioe.getMessage());
        }

    }

    public static Conta deserializeAccount(int agencia, int numero) {

        String fileName = agencia + "-" + numero + ".ser"; 

        try {
            FileInputStream fStream = new FileInputStream(fileName);
            ObjectInputStream oStream = new ObjectInputStream(fStream);
            Conta acc = (Conta) oStream.readObject();
            oStream.close();
            return acc;
        } catch (IOException | ClassNotFoundException e){
            return null;
        }


    }

}