public class ContaPoupanca extends Conta{

    public ContaPoupanca(int numero, int agencia, String senha, double saldo, Cliente dono, double limite) {

        super(numero, agencia, senha, saldo, dono, limite);

        if (this.limite < 100 || this.limite > 1000) {
            throw new IllegalArgumentException("Limite da Conta Poupanca inferior ou superior ao permitido: "  + limite);
        }

    }

    @Override
    public void setLimite() throws IllegalArgumentException{

        if (this.limite < 100 || this.limite > 1000) {
            throw new IllegalArgumentException("Limite da Conta Poupanca inferior ou superior ao permitido.");
        }


    }

    public double calculaTaxas() {
        return 0.0;
    }

    public double calculaTaxasOperacao(){
        return 0.0;
    }
}
