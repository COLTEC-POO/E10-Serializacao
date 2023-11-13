import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class ContaUniversitaria extends Conta{
    public ContaUniversitaria(int numero, double saldo, Cliente dono, double limite) {

        super(numero, saldo, limite, dono);
        setLimite(limite);

    }
    public void setLimite(double limite){
        if (limite>=0 && limite<=1000) {
            this.limite = limite;
            System.out.println("Limite da Conta Corrente permitido!");
        }else {
            this.limite=0;
            System.out.println("Limite da Conta Corrente negado!");
        }
    }
    @Override
    public void sacar(double valor) {
        double saldoAtual = getSaldo();
        if (valor>=0) {
            if (valor >= 0 && valor <= saldoAtual) {
                this.setSaldo(saldoAtual - valor);

                this.operacoes.add(new OperacaoSaque(valor));

                operacaoAtual++;
            } else {
                System.out.println("Dinheiro indisponivel, valor disponivel: R$: " + saldoAtual);
            }
        }else {
            throw new ValorNegativoException(valor);
        }
    }

    @Override
    public void depositar(double valor) {
        double saldoAtual = getSaldo();

        if (valor >= 0) {
            this.setSaldo(saldoAtual + valor);

            this.operacoes.add(new OperacaoDeposito(valor));
            operacaoAtual++;
        }else {
            throw new ValorNegativoException(valor);
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
                    return o1.getTipo().compareTo(o2.getTipo());
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
    public double calculaTaxa() {
        return 0.0;
    }
}
