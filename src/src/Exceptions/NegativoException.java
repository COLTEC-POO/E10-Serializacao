package Exceptions;

public class NegativoException extends  Exception {
    public NegativoException(double valor) {
        super("ERRO: Valor negativo na operação, valor inválido: " + valor);
    }
}
