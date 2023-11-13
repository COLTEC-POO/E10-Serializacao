package Operacao;

public class OperacaoDeposito extends Operacao {
    public OperacaoDeposito(double valor) {
        super('d', valor);
        qtdOperacao++;
    }

    @Override
    public String getTipoOperacao() {
        return "d";
    }

    @Override
    public String getDetalhesOperacao() {
        return this.data + " d " + this.valor;
    }

    @Override
    public String toString() {
        return " " + this.data + " d " + " " + this.valor;
    }

    @Override
    public double calculaTaxas() {
        return 0;
    }
}