package Operacao;

public class OperacaoSaque extends Operacao {
    public OperacaoSaque(double valor) {
        super('s', valor);
        qtdOperacao++;
    }

    @Override
    public String getTipoOperacao() {
        return "s";
    }

    @Override
    public String getDetalhesOperacao() {
        return this.data + " s " + this.valor;
    }

    public String toString() {
        return " " + this.data + " s " + " " + this.valor;
    }
    @Override
    public double calculaTaxas() {
        return 0.05;
    }
}