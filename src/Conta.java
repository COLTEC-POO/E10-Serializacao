import java.util.ArrayList;
import java.util.List;
import java.io.*;

public abstract class Conta implements ITaxas, Serializable {

    //atribuições
    protected int numero;
    protected Cliente dono;
    protected double saldo;
    protected double limite;
    //private String senha;
    protected List<Operacao> operacoes;
    protected int operacaoAtual=0;
    protected static int TOTAL_CONTAS=0;

    //acoes
    public Conta(int numero, double saldo, double limite, Cliente dono) {
        this.numero = numero;
        this.saldo = saldo;
        this.limite = limite;
        this.dono = dono;

        this.operacoes = new ArrayList<>();
        this.operacaoAtual = 0;

        TOTAL_CONTAS++;
    }
    public boolean equals(Object outro) {
        if (outro instanceof Conta) {
            Conta outraConta = (Conta) outro;

            System.out.println("As contas sao iguais");

            return this.numero == outraConta.numero;

        } else {
            System.out.println("As contas nao sao iguais");
            return false;
        }
    }
    public abstract void depositar(double valor);

    public abstract void sacar(double valor);

    public abstract void imprimirExtrato(int tipoOrdenacao);


    public String toString() {
        return "Número da Conta: " + this.numero + "\nSaldo: R$" + getSaldo() + "\nDono: " + this.dono + "\nLimite: R$" + this.limite + "\n";
    }

    public Cliente getDono(){
        return this.dono;
    }
    public void setDono(Cliente dono){
        this.dono = dono;
    }
    public double getSaldo() {
        return this.saldo;
    }

    public int getNumero(){
        return this.numero;
    }
    public void setNumero(int numero){
        this.numero=numero;
    }

    public double getLimite(){
        return this.limite;
    }
    abstract void setLimite(double limite);
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public static double getTotalContas() {
        return Conta.TOTAL_CONTAS;
    }
    public int getOperacaoAtual(){
        return this.operacaoAtual;
    }


    /*public void imprimir(){
        System.out.println("INFORMACOES DA CONTA:");
        System.out.println("Numero: "+this.numero);
        System.out.println("Nome: "+this.dono.nome);
        System.out.println("Saldo: "+this.saldo);
        System.out.println("Limite: "+this.limite);
        System.out.println("INFORMACOES DO CLIENTE: ");
        //System.out.println("CPF: "+this.dono.cpf);
        System.out.println("Endereco: "+this.dono.endereco);
        //System.out.println("Sexo: "+this.dono.sexo);
        //System.out.println("Idade: "+this.dono.idade);
    }*/

    public void extrato(){
        for (Operacao operacao : this.operacoes){
            operacao.imprimirExtrato(operacao);
        }
    }

    // Método para salvar a conta em arquivo
    public void salvarConta() {
        String nomeArquivo = this.numero + "-" + this.dono + ".txt";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(this);
            System.out.println("Conta salva com sucesso");
            System.out.println();
        } catch (IOException e) {
            System.out.println("Erro ao salvar a conta");
            System.out.println();
        }
    }

    public static Conta carregarConta(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            Conta conta = (Conta) ois.readObject();
            System.out.println("Conta carregada com sucesso");
            System.out.println();
            return conta;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar a conta");
            System.out.println();
            return null;
        }
    }
}


