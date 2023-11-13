package Exceptions;

public class LimiteException extends Exception {
    public LimiteException(double valor) {
        super("ERRO: Valor de saque não disponível! " + valor);
    }
}
