import java.io.Serializable;

public class ContaCorrente extends Conta implements Serializable {

    public ContaCorrente(int numeroAgencia, int numero, double saldo, double limite, Cliente dono) {
        super(numero, saldo, limite, dono);
    }

    @Override
    public void setLimite(double limite) {
        super.setLimite(limite);
        if (limite < -100)
            super.setLimite(-100);
    }

    @Override
    public double calcularTaxas() {
        return this.getDono().calcularTaxas();
    }
}
