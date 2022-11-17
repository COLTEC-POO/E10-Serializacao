import java.io.Serializable;
import java.util.Date;
import java.lang.Comparable;

/**
 * Classe responsável por registrar operações de saque e depósitos realizados em contas correntes.
 */
public abstract class Operacao implements ITaxas, Comparable<Operacao>, Serializable {

    /* Data de realização da operação */
    private Date data;

    /* Tipo da operação */
    private char tipo;

    /* Valor da operação */
    private double valor;

    // Mantém a contagem total de operações realizadas
    public static double totalOperacoes = 0;

    public Operacao(char tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        data = new Date();
        Operacao.totalOperacoes++;
    }

    public Date getData() {
        return data;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        if(tipo == 'd' || tipo == 's') {
            this.tipo = tipo;
        }
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String toString() {
        String operacaoStr = "Operação inválida";

        return operacaoStr;
    }

    public int compareTo(Operacao obj){
        if(this.getTipo() == 'd'&& obj.getTipo() == 's')
            return -1;

        if(this.getTipo() == 's'&& obj.getTipo() == 'd')
            return 1;

        return 0;
    }

}