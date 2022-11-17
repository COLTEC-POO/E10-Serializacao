public class ContaUniversitaria extends Conta {

    public ContaUniversitaria(int agencia, int numConta, double limiteMax, Cliente titular) {
        super(agencia, numConta, titular);
        this.setLimite(limiteMax);
    }

    public void setLimite(double limiteMax) {
        if(limiteMax > 500 || limiteMax < 0){
            throw new IllegalArgumentException("Valor de limite inválido");
        }
        else {
            this.limiteMax = limiteMax;
        }
        this.limiteMin = 0;
    }

    public double calculaTaxas(){
        double taxa = 0;

        return taxa;
    }
}
