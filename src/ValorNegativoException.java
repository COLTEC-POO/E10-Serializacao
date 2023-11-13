public class ValorNegativoException extends Exception{
    public ValorNegativoException(Double valor) {
        super("Valor negativo: "+ valor);
    }
}
