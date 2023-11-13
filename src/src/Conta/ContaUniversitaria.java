package Conta;

import Cliente.Cliente;

import Exceptions.LimiteException;
import Exceptions.NegativoException;
import Exceptions.setArgumentException;
import Operacao.Operacao;
import Operacao.OperacaoDeposito;
import Operacao.OperacaoSaque;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContaUniversitaria extends Conta {

    public ContaUniversitaria(int numero, String senha, double saldo, String dono, double limite, Cliente cliente) throws setArgumentException {
        super(numero, senha, saldo, dono, limite, cliente);

        try {
            setLimite(limite);
        } catch (setArgumentException erro) {
            System.out.println(erro.getMessage());
        }
    }

    @Override
    public void sacar(double valor) throws LimiteException, NegativoException {
        double saldoAtual = getSaldo();

            if (valor > 0 && valor <= saldoAtual && valor <= limite) {
                // Adiciona a taxa de saque ao saldo
                this.setSaldo(saldoAtual - valor);
                this.operacoes.add(new OperacaoSaque(valor));
                numOp++;
            } else if (valor > limite) {
                throw new LimiteException(valor);
            } else {
                throw new NegativoException(valor);
            }
    }

    @Override
    public void depositar(double valor) {
        double saldoAtual = getSaldo();

        if (valor >= 0) {
            this.setSaldo(saldoAtual + valor);

            this.operacoes.add(new OperacaoDeposito(valor));
            numOp++;
        }
    }

    @Override
    public void imprimirExtrato(int tipoOrdenacao) {
        List<Operacao> operacoesOrdenadas = new ArrayList<>(this.operacoes);

        if (tipoOrdenacao == 1) {
            // Ordenar por data de inserção (padrão)
            Collections.sort(operacoesOrdenadas);
        } else if (tipoOrdenacao == 2) {
            // Ordenar por tipo de operação (primeiro depósitos, depois saques)
            Collections.sort(operacoesOrdenadas, new Comparator<Operacao>() {
                @Override
                public int compare(Operacao o1, Operacao o2) {
                    return o1.getTipoOperacao().compareTo(o2.getTipoOperacao());
                }
            });
        } else {
            System.out.println("Tipo de ordenação inválido.");
            return;
        }

        System.out.println("Extrato da conta de " + this.dono);
        for (Operacao operacao : operacoesOrdenadas) {
            System.out.println(operacao);
        }
    }

    @Override
    public double setLimite(double valor) throws setArgumentException {
//            Limite máximo de 500 reais, e limite mínimo de 0 reais.
        if (valor >= 0 && valor <= 500) {
            return this.limite = valor;

        } else {
            throw new setArgumentException("ERRO: Limite deve estar entre 0 e 500. Limite inserido: ", valor);
        }
    }

    @Override
    public double calculaTaxas() {
        return 0;
    }
}