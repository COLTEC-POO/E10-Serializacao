public class Main {

    public static void main(String[] args) {

        Cliente pf = new PessoaFisica("João", "Av. Antonio Carlos, 6627",
                "111.111.111-11", 35, 'm');

        // Criando uma conta corrente para esse cliente
        Conta cc = new ContaCorrente(123, 10, 2000, 100, pf);

        //Fazendo operações de saques e depósitos
        cc.depositar(1000);
        cc.depositar(2000);
        cc.sacar(500);
        cc.depositar(3000);
        cc.sacar(10);
        cc.sacar(15);

        cc.imprimirExtratoTaxas();
        cc.salvarConta();

        System.out.println("Ordenado");
        cc.imprimirExtrato(1);

        System.out.println("Ordem Original");
        cc.imprimirExtrato(0);

        Conta contaCarregada = Conta.carregarConta(cc.getNumeroAgencia(), cc.getNumero());

        if (contaCarregada != null) {
            System.out.println(contaCarregada.toString());
        } else {
            System.out.println("Erro ao carregar a conta.");
        }

    }
}
