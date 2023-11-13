public class OperacaoSaque extends Operacao implements ITaxas{
    //double valor;
    public OperacaoSaque(double valor){
        super('s', valor);
    }
    public double calculaTaxa(){
        return 0.5;
    }
    public char getTipo() {
        return 's';
    }
    public String toString() {
        return " " + this.data + " s " + " " + this.valor;
    }
}
