import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;

public abstract class Conta implements ITaxas, Serializable {
    private int numeroDaConta;
    private int numeroDaAgencia;
    private Cliente dono;
    private double saldo;
    protected double limite;
    static int totalDeContas = 0;
    private List<Operacao> operacoes;

//    construtores

    public Conta (){
        totalDeContas++;
        this.operacoes = new ArrayList<>();
    }

//     getters

    public int getNumeroDaConta(){
        return this.numeroDaConta;
    }

    public int getNumeroDaAgencia() {
        return numeroDaAgencia;
    }
    public Cliente getDono(){
        return this.dono;
    }

    public double getSaldo(){
        return this.saldo;
    }

    public double getLimite(){
        return this.limite;
    }
// setters

    public void setDono(Cliente dono){
        this.dono = dono;
    }

    public void setNumeroDaAgencia(int numeroDaAgencia) {
        this.numeroDaAgencia = numeroDaAgencia;
    }
    public void setNumeroDaConta(int numeroDaConta){
        this.numeroDaConta = numeroDaConta;
    }

    public abstract void setLimite(int limite);

    //    Métodos

    public String toString(){
        return String.format("%s \nNumero da conta: %d\nSaldo atual da conta: R$ %f \nLimite: R$ %f", this.dono.toString(), this.numeroDaConta, this.saldo, this.limite);
    }

    boolean sacar(double valor) throws ValorNegativoException, SemLimiteException{
        Operacao opr;

        if (valor < 0){
            throw new ValorNegativoException("Valor de saque negativo " + valor);
        }

        if (valor > this.limite){
            throw new SemLimiteException("Valor de saque acima do limite da conta ");
        }

        if (valor <= this.saldo) {
            this.saldo -= valor;
            opr = new OperacaoSaque (valor);
            this.operacoes.add(opr);
            return true;
        } else {
            return false;
        }
    }

    void depositar(double valor) throws ValorNegativoException{
        Operacao opr;

        if (valor < 0){
            throw new ValorNegativoException("Valor de deposito negativo " + valor);
        }

        this.saldo += valor;
        opr = new OperacaoDeposito (valor);
        this.operacoes.add(opr);
    }

    boolean transferir(Conta destino, double valor) throws ValorNegativoException, SemLimiteException{
        boolean saqueRealizado;
        saqueRealizado = this.sacar(valor);
        if (saqueRealizado) {
            destino.depositar(valor);
            return true;
        } else {
            return false;
        }
    }

    public boolean equals(Object obj){
        if (obj instanceof Conta){
            Conta that = (Conta) obj;
            return (this.numeroDaConta == that.numeroDaConta);
        }else{
            return false;
        }
    }

    void imprimirExtrato (){

        System.out.println("\t //Extrato de trasaçoes da conta " + getDono().nome + " // \n");
        System.out.println("Por data da transacao:\n");
        for (Operacao atual: this.operacoes){
            System.out.println(atual);
        }
        System.out.println("Por tipo de transacao:\n");

        Collections.sort(operacoes);
        for (Operacao atual: this.operacoes){
            System.out.println(atual);
        }
    }

    void imprimirExtratoTaxas (){
        int i;
        double totalTaxas = 0;

        System.out.println("\t //Extrato de taxas " + getDono().nome + " // \n");
        System.out.println("Manutencao da conta \nR$" + calculaTaxas() + "\n");
        System.out.println("Operacoes");

        for (Operacao operacaoAtual : this.operacoes){
            System.out.println(operacaoAtual.getTipo() + ": " + operacaoAtual.calculaTaxas());
            totalTaxas = totalTaxas + operacaoAtual.calculaTaxas();
        }

        totalTaxas = totalTaxas + calculaTaxas();
        System.out.println("\n");
        System.out.println("Total de taxas: R$ " + totalTaxas);
    }

    void salvarContas (){

        String nomeDoArquivo = this.numeroDaAgencia+"-"+this.numeroDaConta+".ser";
        try {
            FileOutputStream fStream = new FileOutputStream(nomeDoArquivo);
            ObjectOutputStream oStream = new ObjectOutputStream(fStream);
            oStream.writeObject(this);
            oStream.close();
        }catch(FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch(IOException e) {
            System.out.println("Erro inesperado na escrita do dado");
            e.printStackTrace();
        }

    }

    void carregaConta (int numeroDaAgencia, int numeroDaConta){

        String nomeDoArquivo = numeroDaAgencia+"-"+numeroDaConta+".ser";

        try{
            FileInputStream fStream = new FileInputStream(nomeDoArquivo);
            ObjectInputStream oStream = new ObjectInputStream(fStream);

            Conta conta = (Conta) oStream.readObject();

            oStream.close();
        }catch(FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch(IOException e) {
            System.out.println("Erro inesperado na leitura do arquivo");
            e.printStackTrace();
        }catch(ClassNotFoundException e) {
        System.out.println("Classe não encontrada");
    }
    }

}
