public class OperacaoDeposito extends Operacao implements ITaxas{

    public OperacaoDeposito(double valor) {
        super('d', valor);
    }

    public String toString(){

        String OperacaoStr =
                "===== Operacao  =====" +"\n" +
                        "Data: " + this.getData() + "\n" +
                        "Tipo: " + this.getTipo() + "\n" +
                        "Valor: " + this.getValor() + "\n" +
                        "==========================" + "\n";

        return OperacaoStr;
    }

    public double calculaTaxasOperacao() {
        return 0.0;
    }

    public double calculaTaxas() {
        return 0.0;
    }
}
