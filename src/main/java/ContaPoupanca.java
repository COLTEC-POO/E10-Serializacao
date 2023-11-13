import java.io.Serializable;

public class ContaPoupanca extends Conta implements Serializable {
    public ContaPoupanca(int numero, double saldo, double limite, Cliente dono) {
        super(numero, saldo, limite, dono);
    }

    @Override
    public void setLimite(double limite) {
        super.setLimite(limite);
        if (limite > 1000)
            super.setLimite(1000);
        if (limite < 100)
            super.setLimite(100);
    }

    @Override
    public double calcularTaxas() {
        return 0;
    }
}
