public class ContaUniversitaria extends Conta{

    public ContaUniversitaria(int numero, int agencia, String senha, double saldo, Cliente dono, double limite) {

        super(numero, agencia, senha, saldo, dono, limite);

        if (this.limite < 0 || this.limite > 500) {
            throw new IllegalArgumentException("Limite da Conta Universitaria superior ou inferior ao permitido: "  + limite);
        }

    }

    @Override
    public void setLimite() throws IllegalArgumentException{
        if (this.limite < 0 || this.limite > 500) {
            throw new IllegalArgumentException("Limite da Conta Universitaria superior ou inferior ao permitido.");
        }

    }

    public double calculaTaxas() {
        return 0.0;
    }

    public double calculaTaxasOperacao(){
        return 0.0;
    }
}
