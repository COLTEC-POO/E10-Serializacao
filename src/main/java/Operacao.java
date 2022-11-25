import java.io.*;
import java.util.Date;
import java.lang.Comparable;



/**
 * Classe responsável por registrar operações de saque e depósitos realizados em contas correntes.
 */
public abstract class Operacao implements ITaxas, Comparable<Operacao>, Serializable{

    private  Date data;
    private char tipo;
    private double valor;
    public static int totalOperacoes = 0;
    public Date getData() {
        return data;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Operacao(char tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        data = new Date();
        Operacao.totalOperacoes++;
    }

    public String toString() {
        String operacao = "Operação inválida";

        return operacao;
    }

    public int compareTo(Operacao obj){
        if(this.getTipo() == 'd'&& obj.getTipo() == 's')
            return -1;

        if(this.getTipo() == 's'&& obj.getTipo() == 'd')
            return 1;

        return 0;
    }

}