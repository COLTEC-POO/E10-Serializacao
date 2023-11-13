package Operacao;
import ITaxas.ITaxas;

import java.io.Serializable;
import java.util.Date;

// Classe principal de Operacao
public abstract class Operacao implements ITaxas, Comparable<Operacao>, Serializable {
    public double valor;

    Date data;

    char tipo;

    static int qtdOperacao = 0;

    public Operacao(char tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;

        data = new Date();
    }

    @Override
    public int compareTo(Operacao outraOperacao) {

        return this.data.compareTo(outraOperacao.data);
    }

    public abstract String getTipoOperacao();

    public abstract String getDetalhesOperacao();

    public abstract String toString();
}

