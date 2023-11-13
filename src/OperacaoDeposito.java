public class OperacaoDeposito extends Operacao implements ITaxas{

    public OperacaoDeposito(double valor){
        super('d', valor);
    }

    public double calculaTaxa(){
        return 0.0;
    }

    @Override
    public char getTipo() {
        return 's';
    }

    @Override
    public String toString() {
        return " " + this.data + " d " + " " + this.valor;
    }
}
