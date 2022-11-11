public class ContaCorrente extends Conta implements ITaxas {

    double taxa = 0;

    public void setLimite(double limite) throws IllegalArgumentException{
        if (limite < -100) {
            throw new IllegalArgumentException("\n** O limite mínimo para Conta Corrente é R$-100,00 **");
        } else {
            this.limite = limite;
        }
    }

    public double calculaTaxas() {
        if (getDono() instanceof PessoaFisica) {
            taxa = 10.0;
        }
        if (getDono() instanceof PessoaJuridica) {
            taxa = 20.0;
        }
        return taxa;
    }
}