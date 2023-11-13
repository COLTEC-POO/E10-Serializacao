import java.io.Serializable;

public class ContaUniversitaria extends Conta implements Serializable {
    public ContaUniversitaria(int numero, double saldo, double limite, Cliente dono) {
        super(numero, saldo, limite, dono);
        this.setLimite(limite);
    }

    @Override
    public void setLimite(double valor) {
        super.setLimite(valor);
        if (valor > 500)
            super.setLimite(500);
        if (valor < 0)
            super.setLimite(0);
    }

    @Override
    public double calcularTaxas() {
        return 0;
    }
}
