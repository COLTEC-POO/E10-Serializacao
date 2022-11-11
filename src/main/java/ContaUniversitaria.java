public class ContaUniversitaria extends Conta implements ITaxas {

    public void setLimite(double limite) throws IllegalArgumentException{
        if(limite < 0 || limite > 500){
            throw new IllegalArgumentException("\n** O limite mínimo e máximo para Conta Universitária é respectivamente R$0,00 e R$500,00 **");
        }
        else{
            this.limite = limite;
        }
    }

    public double calculaTaxas() {
        return 0;
    }
}