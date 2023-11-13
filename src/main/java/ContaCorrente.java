public class ContaCorrente extends Conta {


    public ContaCorrente(int numero, int agencia, String senha, double saldo, Cliente dono, double limite) {

        super(numero, agencia, senha, saldo, dono, limite);

        if (this.limite < -100) {
            throw new IllegalArgumentException("Limite da Conta Corrente inferior ao permitido: " + limite);
        }

    }

    @Override
    public void setLimite() throws IllegalArgumentException{
        if (this.limite < -100) {
            throw new IllegalArgumentException("Limite da Conta Corrente inferior ao permitido.");
        }

    }

    public double calculaTaxas(){

        if(this.getDono() instanceof PessoaFisica){
            return 10.0;
        }
        else if(this.getDono() instanceof PessoaJuridica){
            return 20.0;
        } else{
            System.out.println("Nao esta relacionada a classes Pessoa Fisica OU Juridica.");
            return 0.0;

        }
    }

    public double calculaTaxasOperacao(){
        return 0.0;
    }
}
