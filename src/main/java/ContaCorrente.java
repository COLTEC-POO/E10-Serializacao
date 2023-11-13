public class ContaCorrente extends Conta {

    public ContaCorrente(int numero, int agencia, Cliente dono, double saldo) {
        super(numero, agencia, dono, saldo);
    }

    public void setLimite(double limite) {
        if (limite < -100) {
            throw new IllegalArgumentException("Valor de limite não permitido para conta corrente!\n");
        }
        this.limite = limite;
    }

    public double calculaTaxas(){

        double tax = 0.0;
        
        if (this.dono instanceof PessoaFisica){
            tax = 10.0;
        }

        if (this.dono instanceof PessoaJuridica){
            tax = 20.0;
        }
        return tax;
    }
}