import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Conta implements ITaxas, Serializable {

    public static int contadorNumContas = 0;
    public int contadorNumOperacoes = 0;

    // atributos
    public String nome;
    public int numConta;
    private double saldo;
    public String cpf;
    public boolean tipo;

    public Operacao[] operacoes = new Operacao[1000];

    // ações
    public abstract double calculaTaxas();
    public String toString(){
        String produtoConta = "Nome: "+this.nome+"Tipo: "+this.tipo+"cpf: "+this.cpf+"Saldo"+this.saldo+"Número da conta: "+this.numConta;
        return produtoConta;
    }
    public boolean equals(Object comparado){
        return true;
    }
    public Conta(String nome, int numConta, String cpf, boolean tipo){
        this.nome = nome;
        this.numConta = numConta;
        this.saldo = 0;
        this.cpf = cpf;
        this.tipo = tipo;
        Conta.contadorNumContas++;
    }

    public void imprimirSaldo(){
        System.out.println("==== Conta " +this.numConta + " ====");
        System.out.println("Nome:  "+ this.nome);
        System.out.println("Saldo: "+ this.saldo);
    }

    public boolean sacar(double valor) throws ValorNegativoException{
        if(valor > this.saldo ){

            return false;
        }
        else if(valor < 0){
            throw  new ValorNegativoException ("Valor negativo");
        }
        else{
            this.saldo -=valor;
            this.operacoes[contadorNumOperacoes] = new OperacaoSaque('s', valor);
            contadorNumOperacoes++;
            return true;
        }
    }

    public boolean depositar(double valor)throws ValorNegativoException{
        if(valor < 0){
            throw  new ValorNegativoException ("Valor negativo");
        } else{
            this.operacoes[contadorNumOperacoes] = new OperacaoDeposito('d', valor);
            contadorNumOperacoes++;
            this.saldo += valor;
            return true;
        }
    }
    public void extrato(){
        for (int i =0;i< contadorNumOperacoes;i++) {
            System.out.println(this.operacoes[i].data + "    " + this.operacoes[i].tipo + "    " + this.operacoes[i].valor);
            System.out.println();
        }


    }
    public boolean transferir(Conta destino, double valor)throws ValorNegativoException{
        if(this.sacar(valor)){
            destino.depositar(valor);
            return true;
        } else{
            return false;
        }

    }
    public void contaArquivoSerializa() {
        try {
            String arquivoSeri = this.nome + "-" + this.numConta + ".ser";

            FileOutputStream fileStream = new FileOutputStream(arquivoSeri);

            ObjectOutputStream outputStream = new ObjectOutputStream(fileStream);
            outputStream.writeObject(this);
            outputStream.close();
        } catch (IOException e){

            System.out.println("Erro ao salvar dados da conta");
        }
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public double getSaldo(){
        return this.saldo;
    }
}