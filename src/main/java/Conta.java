import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.*;

public abstract class Conta implements ITaxas, Serializable {

    private int numeroAgencia;
    private int numero;
    private Cliente dono;
    private double saldo;
    private double limite;
    private ArrayList<Operacao> operacoes;

    private static int TOTAL_CONTAS = 0;

    public Conta(int numero, double saldo, double limite, Cliente dono) {
        this.numero = numero;
        this.saldo = saldo;
        this.limite = limite;
        this.dono = dono;
        this.operacoes = new ArrayList<>();
        this.numeroAgencia = numeroAgencia;

        TOTAL_CONTAS++;
    }

    public boolean depositar(double valor) {
        if (valor >= 0) {
            this.saldo += valor;
            this.operacoes.add(new OperacaoDeposito(valor));
            return true;
        } else {
            return false;
        }
    }

    public boolean sacar(double valor) {
        if (valor >= 0 && valor <= this.saldo && valor <= this.limite) {
            this.saldo -= valor;
            this.operacoes.add(new OperacaoSaque(valor));
            return true;
        } else {
            return false;
        }
    }

    public boolean transferir(double valor, Conta destino) {
        if (this.sacar(valor)) {
            destino.depositar(valor);
            return true;
        } else {
            return false;
        }
    }

    public void imprimirExtrato(int order) {
        ArrayList<Operacao> operacoesOrdenada = (ArrayList<Operacao>) this.operacoes.clone();

        if (order == 1)
            Collections.sort(operacoesOrdenada);

        System.out.println("=== Extrato da Conta ===");
        for (Operacao op : operacoesOrdenada) {
            System.out.println(op);
        }

    }

    public void imprimirExtratoTaxas() {
        double totalTaxas = this.calcularTaxas();

        System.out.println("=== Extrato de Taxas ===");
        System.out.println("Manutenção da conta: " + this.calcularTaxas());

        System.out.println("Operações");
        for (Operacao op : this.operacoes) {
            System.out.println(op.getClass().getName() + ": " + op.calcularTaxas());
            totalTaxas += op.calcularTaxas();
        }

        System.out.println("Total: " + totalTaxas);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "\n\tnumero=" + numero +
                ",\n\tdono=" + dono +
                ",\n\tsaldo=" + saldo +
                ",\n\tlimite=" + limite +
                ",\n\toperacoes=" + this.operacoes.toString() +
                "\n}";
    }

    @Override
    public boolean equals(Object outro) {
        if (outro instanceof Conta) {
            Conta outraConta = (Conta) outro;
            return this.getNumero() == outraConta.getNumero();
        }
        return false;
    }

    public int getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(int numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public static double getTotalContas() {
        return Conta.TOTAL_CONTAS;
    }


    public void salvarConta() {
        String nomeArquivo = this.getNumeroAgenciaConta() + ".ser";

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            outputStream.writeObject(this);
            System.out.println("Conta salva com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar a conta: " + e.getMessage());
        }
    }


    public static Conta carregarConta(int numeroAgencia, int numeroConta) {
        String nomeArquivo = numeroAgencia + "-" + numeroConta + ".ser";

        File arquivo = new File(nomeArquivo);

        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado: " + nomeArquivo);
            return null;
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            Conta conta = (Conta) inputStream.readObject();
            System.out.println("Conta carregada com sucesso de " + nomeArquivo);
            return conta;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar a conta: " + e.getMessage());
            return null;
        }
    }


    private String getNumeroAgenciaConta() {
        return this.getNumeroAgencia() + "-" + this.getNumero();
    }
}


