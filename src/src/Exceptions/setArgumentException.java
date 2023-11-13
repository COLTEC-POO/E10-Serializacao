package Exceptions;

public class setArgumentException extends Exception {
    public setArgumentException(String mensagem, double valor) {
        super(mensagem + valor);
    }
}
