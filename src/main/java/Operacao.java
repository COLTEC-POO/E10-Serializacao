import java.util.Date;
import java.lang.Comparable;

public abstract class Operacao implements ITaxas, Comparable <Operacao>  {
    private Date data;
    private char tipo;
    private double valor;
    public static double totalOperacoes = 0;


    public Operacao(char tipo, double valor) {
        setTipo(tipo);
        setValor(valor);
        setData();
        Operacao.totalOperacoes++;
    }

    // Getters e Setters

    public Date getData() {
        return this.data;
    }

    public void setData(){
        this.data = new Date();
    }

    public char getTipo() {
        return this.tipo;
    }

    public void setTipo(char tipo){
        this.tipo = tipo;
    }

    public double getValor(){
        return this.valor;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    // Método que sobrescreve a função toString da class Object e transforma tudo em uma string - Atividade 05

    @Override
    public String toString(){
        String dadosOperacao = this.getData() + "\t" + this.getTipo() + "\t" + this.getValor();

        return dadosOperacao;
    }

    @Override
    public int compareTo(Operacao x){
        
        if(this.getTipo() < x.getTipo()) {
            return -1;
        }
        if(this.getTipo() > x.getTipo()){
            return 1;
        }
        return 0;
    }
}