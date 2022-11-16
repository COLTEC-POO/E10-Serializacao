public class ContaPoupanca extends Conta{

    //Metodos
    public double setLimite(double limite) throws IllegalArgumentException {

        if(limite <= 1000 && limite >= 100)
            System.out.println("Limite alterado de " + this.getLimite() +
                    " para " + limite);
        else
            throw new IllegalArgumentException("ERRO: Não foi possível alterar o limite limite mínimo: 100");

        return this.getLimite();
    }

    public double calculaTaxas() {
        double taxa = 0;

        if(this.getDono() instanceof PessoaFisica){
            taxa = 0;
        }

        if(this.getDono() instanceof PessoaJuridica){
            taxa = 0;
        }

        return taxa;
    }
}