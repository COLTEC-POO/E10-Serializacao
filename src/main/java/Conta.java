import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Conta implements ITaxas, Serializable{
    Scanner entrada = new Scanner(System.in);
    public static int contadorNumContas = 0;
    private Cliente titular;
    private int numConta;
    private double minLimite;
    private double maxLimite;
    private double saldo;
    private double limite;
    private List<Operacao> operacoes;
    public Conta(int numConta, Cliente titular){
        this.numConta = numConta;
        this.saldo = 0;
        this.titular = titular;

        Conta.contadorNumContas ++;
        operacoes = new ArrayList<>();
    }

    ///ações
    /// TIPO NOMEMETODO(TIPO NOME, TIPO NOME, .....){
    /// } corpo do método

    void imprimir(){
        System.out.println("************************");
        System.out.println("\n\nNúmero da conta: " + numConta);
        System.out.println("Titular: " + this.titular.getNome());
        System.out.println("Endereço: " + this.titular.getEndereco());
        System.out.println("Saldo: " + this.saldo);
        System.out.println("************************");

    }
    public boolean sacar(double valor) throws ValorNegativoException, LimiteException{
        if(valor < 0){
            throw new ValorNegativoException("Saldo insuficiente");
        }
        else if(valor > this.maxLimite || valor <this.minLimite) {
            throw new LimiteException("Valor acima do limite atual");
        }
        else{
            this.saldo -= valor;
            this.operacoes.add(new OperacaoSaque(valor));
            return true;
        }
    }

    public boolean depositar(double valor) throws ValorNegativoException{
        if(valor < 0){
            throw new ValorNegativoException("Saldo insuficiente");
        }else{
            this.saldo += valor;
            this.operacoes.add(new OperacaoDeposito(valor));
            return  true;
        }

    }

    public boolean transferir(Conta destino, double valor) throws ValorNegativoException, LimiteException{
        if(valor < 0){
            throw new ValorNegativoException("Impossível transferir um valor negativo");
        }
        else if(this.sacar(valor)){
            destino.depositar(valor);
            return true;
        } else {
            return  false;
        }
    }

    public void imprimirExtrato() {

        System.out.println("\nExtrato da conta de número " + this.numConta + "\n");
        for (Operacao atual : this.operacoes) {
            System.out.println(atual.getData() + "\t" + atual.getTipo() + "\t" + atual.getValor());

        }
    }

    public String toString() {
        String contaStr = "Titular: " + this.titular.getNome() + "\nNúmero da conta conta: " + this.numConta +
                "\nSaldo: " + this.saldo + "\nLimite: " + this.maxLimite +  "\nExtrato:\n";
        for (Operacao operacao : this.operacoes) {
            contaStr += operacao.toString();
        }
        return contaStr;
    }

    public boolean equals(Object obj){
        if(obj instanceof Conta){
            Conta conta = (Conta) obj;
            if(this.numConta == conta.numConta);
            return true;
        }else{
            return false;
        }
    }
    public void imprimirTaxas(){

        System.out.println("\tTaxas da conta de número " +this.getNumConta());
        System.out.println("Taxas: " + this.calculaTaxas() + "\n");
        System.out.println("Operações");
        double totalTaxas = this.calculaTaxas();
        for (Operacao operacao : this.operacoes) {
            if (operacao == null) {
                break;
            }
            double taxaOperacao = operacao.calculaTaxas();
            if(operacao.calculaTaxas() > 0) {
                if (operacao.getTipo() == 's') {
                    System.out.println("Saque: " + operacao.calculaTaxas());
                } else {
                    System.out.println("Depósito: " + operacao.calculaTaxas());
                }
            }
            totalTaxas += taxaOperacao;
        }
        System.out.printf("\nTotal: " + totalTaxas);
    }
    public void serializarConta() {
        try {
            String fileOutput = this.titular + "-" + this.numConta + ".ser";
            FileOutputStream fStream = new FileOutputStream(fileOutput);
            ObjectOutputStream oStream = new ObjectOutputStream(fStream);
            oStream.writeObject(this);
            oStream.close();
        } catch (IOException e){
            System.out.println("Impossível serializar conta, tente novamente");
        }
    }
    public static Conta desserializarConta(String nome, int numConta) {
        try{
            String arquivoEntrada = nome+"-"+numConta+".ser";
            FileInputStream fStream = new FileInputStream(arquivoEntrada);
            ObjectInputStream oStream = new ObjectInputStream(fStream);

            Conta conta = (Conta) oStream.readObject();
            oStream.close();
            return conta;

        } catch (IOException e) {
            System.out.println("Não foi possível encontrar a conta solicitada");
        } catch (ClassNotFoundException e) {
            System.out.println("Não foi possível encontrar as informações da conta");
        }
        return null;
    }
    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }



    public double getLimite(){
        return limite;
    }

    public abstract boolean setLimite(double valor);


    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }
    public double getMinLimite() {
        return minLimite;
    }
    public void setMinLimite(double minLimite) {
        this.minLimite = minLimite;
    }
    public double getMaxLimite() {
        return maxLimite;
    }

    public void setMaxLimite(double maxLimite) {
        this.maxLimite = maxLimite;
    }
}