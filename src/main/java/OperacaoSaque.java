import java.io.Serializable;

public class OperacaoSaque extends Operacao implements Serializable {

    public OperacaoSaque(double valor) {
        super('s', valor);
    }

    @Override
    public double calcularTaxas() {
        return 0.05;
    }
}
