public class ContaUniversitaria extends Conta {

    public ContaUniversitaria(int numero, int agencia, Cliente dono, double saldo) {
        super(numero, agencia, dono, saldo);
    }

    public void setLimite(double limite) {
        if (limite > 500 || limite < 0) {
            throw new IllegalArgumentException("Valor de limite não permitido para conta universitária!\n");
        }
        this.limite = limite;
    }

    public double calculaTaxas(){
        double tax = 0.0;
        return tax;
    }
}
