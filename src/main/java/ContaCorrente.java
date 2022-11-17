public class ContaCorrente extends Conta{

    public ContaCorrente(int agencia, int numConta, double limiteMin, Cliente titular) {
        super(agencia, numConta, titular);
        this.setLimite(limiteMin);
    }

    public void setLimite(double limiteMin) {
        if(limiteMin < -100 || limiteMin > 0){
            throw new IllegalArgumentException("Valor de limite inválido");
        }
        else {
            this.limiteMin = limiteMin;
        }
        this.limiteMax = Double.MAX_VALUE;
    }

    public double calculaTaxas(){
        double taxa = 0;
        if(this.getTitular() instanceof PessoaFisica){
            taxa = 10;
        }
        if(this.getTitular() instanceof PessoaJuridica){
            taxa = 20;
        }
        return taxa;
    }

    public String toString() {
        String contaStr = "Titular......: " + this.getTitular().getNome() + "\nNum conta....: " + this.getNumConta() +
                "\nSaldo........: " + this.getSaldo() + "\nLimite.......: " + this.limiteMin  +
                "\nExtrato......:\n";
        for(Operacao operacao : this.getOperacoes()){
            contaStr += operacao.toString();
        }

        return contaStr;
    }
}
