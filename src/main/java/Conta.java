import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Conta implements ITaxas, Serializable{
    // atributos

    private static int contadorNumContas = 0;
    private Cliente titular;
    private int numConta;
    private int agencia;
    private double saldo;
    protected double limiteMin;
    protected double limiteMax;
    private List<Operacao> operacoes;

    // ações

    public Conta(int agencia, int numConta, Cliente titular){
        this.numConta = numConta;
        this.saldo = 0;
        this.titular = titular;
        this.agencia = agencia;
        Conta.contadorNumContas++;
        operacoes = new ArrayList<>();
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(PessoaFisica titular) {
        this.titular = titular;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public List<Operacao> getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(List<Operacao> operacoes) {
        this.operacoes = operacoes;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimiteMax() {
        return limiteMax;
    }

    public abstract void setLimite(double limite);

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public void imprimirSaldo(){
        System.out.println("==== Conta " +this.numConta + " ====");
        System.out.println("Nome:  "+ this.titular.getNome());
        System.out.println("Saldo: "+ this.saldo+"\n");
    }

    public boolean sacar(double valor) throws ValorNegativoException, SemLimiteException{
        if(valor < 0){
            throw new ValorNegativoException("Saldo insuficiente para sacar o valor de R$" + valor);
        }
        else if(valor > this.limiteMax || valor < this.limiteMin){
            throw new SemLimiteException("Valor de saque excede limite");
        }
        else{
            this.saldo -=valor;
            this.operacoes.add(new OperacaoSaque(valor));
            return true;
        }
    }

    public boolean depositar(double valor)  throws ValorNegativoException{
        if(valor < 0){
            throw new ValorNegativoException("Valor incorreto para depósito");
        }
        else{
            this.saldo += valor;
            this.operacoes.add(new OperacaoDeposito(valor));
            return true;
        }
    }

    public boolean transferir(Conta destino, double valor) throws ValorNegativoException, SemLimiteException {
        if(this.sacar(valor)){
            destino.depositar(valor);
            return true;
        } else{
            return false;
        }
    }

    public void imprimirExtrato(int ordem){

        System.out.println("=== Extrato da conta " + this.numConta + " ===");
        if(ordem == 1) {
            for (Operacao atual : this.operacoes) {
                System.out.println(atual.getData() + "\t" + atual.getTipo() + "\t" + atual.getValor());
            }
        }
        if(ordem == 2){
            List<Operacao> operacoesOrdenadas  = new ArrayList<>(this.operacoes);
            Collections.sort(operacoesOrdenadas);

            for (Operacao atual : operacoesOrdenadas) {
                System.out.println(atual.getData() + "\t" + atual.getTipo() + "\t" + atual.getValor());
            }
        }
        System.out.println();
    }

    public void mediaOperacoes(){
        double media;
        media = Operacao.totalOperacoes/Conta.contadorNumContas;
        System.out.println("A média de operações realizadas é "+media + "\n");
    }

    public String toString() {
        String contaStr = "Titular......: " + this.titular.getNome() + "\nNum conta....: " + this.numConta +
                "\nSaldo........: " + this.saldo + "\nLimite.......: " + this.limiteMax  +
                "\nExtrato......:\n";
        for(Operacao operacao : this.operacoes){
            contaStr += operacao.toString();
        }

        return contaStr;
    }

    public boolean equals(Object obj){
        if(obj instanceof Conta) {
            Conta objConta = (Conta) obj;
            if (this.numConta == objConta.numConta) {
                return true;
            } else {
                return false;
            }
        }
        else{
            return false;
        }
    }

    public void imprimirExtratoTaxas(){
        System.out.println("=== Extrato de Taxas ===");
        System.out.println("Manutenção da conta: " + this.calculaTaxas() + "\n");
        System.out.println("Operações");
        double totalTaxas = this.calculaTaxas();
        for (Operacao operacao : this.operacoes) {
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
        System.out.printf("\nTotal: %.2f\n", totalTaxas);
    }

    public void serializarConta() {
        try {
            String arquivoSaida = this.agencia + "-" + this.numConta + ".ser";
            FileOutputStream fStream = new FileOutputStream(arquivoSaida);
            ObjectOutputStream oStream = new ObjectOutputStream(fStream);
            oStream.writeObject(this);
            oStream.close();
        } catch (IOException e){
            System.out.println("Não foi possível salvar os dados da conta");
        }
    }

}