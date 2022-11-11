import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Conta implements ITaxas, Serializable {

    private Cliente dono;
    private int numConta;
    private int agencia;
    private double saldo;
    public double limite;
    private transient List <Operacao> operacoes;
    public transient static int totalContas;

    public Conta() {
        this.numConta = numConta;
        this.saldo = saldo;
        this.limite = limite;
        this.operacoes = new ArrayList<>();
        this.agencia = agencia;
        Conta.totalContas++;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
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

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public abstract void setLimite(double limite) throws SemLimiteException;

    public void depositar (double valor) throws ValorNegativoException{

        if(valor < 0){
            throw new ValorNegativoException("Não foi possível realizar o depósito de: R$" + valor + " **");
        }
        else {
            this.saldo = this.saldo + valor;
            this.operacoes.add(new OperacaoDeposito(valor));
        }
    }

    public void sacar(double valor) throws ValorNegativoException, SemLimiteException{

        if (valor > this.saldo || valor < 0){
            throw new ValorNegativoException("** Não foi possível realizar o saque de: R$" + valor + " **");
        }

        if(valor > this.limite){
            throw new SemLimiteException("** O valor para saque inserido é maior que o limite permitido para a conta **");
        }

        else {
            this.saldo = this.saldo - valor;
            this.operacoes.add(new OperacaoSaque(valor));
        }
    }

    /*
    public boolean transferir (Conta destino, double valor){
        boolean saqueRealizado = this.sacar(valor);

        if (saqueRealizado){
            destino.depositar(valor);
            return true;

        }else {
            return false;
        }
    }

     */

    public boolean equals(Object obj) {

        Conta contaNum = (Conta) obj;

        if (this.numConta == (contaNum.numConta)){
            return true;
        }
        else
            return false;

    }

    public String toString(){

        return "Dono: " + getDono().nome + "\n" + "Número da conta: " + getNumConta() + "\n" + "Agência: " + getAgencia() +
                "\nSaldo: " + getSaldo() + "\n" + "Limite: " + getLimite();

    }

    public int imprimirExtrato(int opcao) {

        if (opcao == 1) {
            System.out.println("\n- Visualização Padrão: de acordo com a data de inserção na lista\n");

            for (Operacao atual : this.operacoes) {
                System.out.println(atual);
            }
        }
        if (opcao == 2) {

            System.out.println("\n- Visualização Ordenada: de acordo com o tipo (depósito, saque)\n");
            Collections.sort(this.operacoes);

            for (Operacao atual : this.operacoes) {
                System.out.println(atual);
            }
        }

        return opcao;
    }

    public void imprimirExtratoTaxas(){

        double taxaManutencao = this.calculaTaxas();
        double totalOperacoes = 0;

        System.out.println(" =============== Extrato de Taxas ===============");
        System.out.println("Nome: " + this.dono.nome);
        System.out.println("Manutenção da conta: R$" + taxaManutencao);
        System.out.println("\nOperações");

        for (Operacao atual: this.operacoes){

            if (atual instanceof OperacaoSaque) {
                totalOperacoes += ((OperacaoSaque) atual).calculaTaxas();
                System.out.println("Saque: " + ((OperacaoSaque) atual).calculaTaxas());
            }
        }

        totalOperacoes = totalOperacoes + taxaManutencao;

        System.out.println("\nTotal: R$" + totalOperacoes);
    }

    public void gravarDados() {

        String caminho = this.agencia + "-" + this.numConta + ".ser";

        try {
            File arquivo = new File(caminho);
            FileOutputStream fileOutput = new FileOutputStream(arquivo);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileOutput);

            objectStream.writeObject(this);

            objectStream.close();

            System.out.println("Dados gravados com sucesso!");

        } catch(FileNotFoundException ex) {
            System.out.println("Erro: arquivo não encontrado!");
            ex.printStackTrace();

        } catch(IOException ex) {
            System.out.println("Erro na gravação dos dados!");
            ex.printStackTrace();
        }
    }

    public static void lerDados(int numeroAgencia, int numeroConta) {

        String caminho = numeroAgencia + "-" + numeroConta + ".ser";

        try {
            File arquivo = new File(caminho);
            FileInputStream fileInput = new FileInputStream(arquivo);
            ObjectInputStream objInput = new ObjectInputStream(fileInput);

            Conta lerConta = (Conta) objInput.readObject();

            objInput.close();

            System.out.println("Leitura realizada com sucesso!\n");

            System.out.println(lerConta);


        } catch(FileNotFoundException ex) {
            System.out.println("Erro: arquivo não encontrado!");
            ex.printStackTrace();

        } catch(IOException ex) {
            System.out.println("Erro na leitura dos dados!");
            ex.printStackTrace();

        } catch (ClassNotFoundException ex) {
            System.out.println("Erro: classe não encontrada");
            ex.printStackTrace();
        }
    }
}