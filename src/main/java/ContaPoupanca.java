public class ContaPoupanca extends Conta {

    public ContaPoupanca(int numero,int agencia, Cliente dono, double saldo) {
        super(numero, agencia, dono, saldo);
    }


    public void setLimite(double limite) throws IllegalArgumentException {
        if (limite > 1000 || limite < 100) {
            throw new IllegalArgumentException("Valor de limite não permitido para conta poupança!\n");
        }
        this.limite = limite;
    }

    public double calculaTaxas(){
        double tax = 0.0;
        return tax;
    }
}