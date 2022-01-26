package main.java;

public class OperacaoDeposito extends Operacao {
    public OperacaoDeposito (double valor){
        super('d', valor);
    }

    public double calculaTaxas() {
        return 0;
    }



}
