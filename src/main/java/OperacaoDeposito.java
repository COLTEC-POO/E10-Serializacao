import java.io.Serializable;

public class OperacaoDeposito extends Operacao implements Serializable {

    public OperacaoDeposito(double valor) {
        super('d', valor);
    }

    @Override
    public double calcularTaxas() {
        return 0;
    }
}
