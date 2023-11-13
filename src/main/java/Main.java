public class Main {

    public static void main(String[] args){

        System.out.println("======== Sistema Bancario ========");
        System.out.println("\n");

        Cliente pessoa = new PessoaFisica("Pedro", "Rua Joao e Maria", "284.451.997-13", 19, 'M');

        try {
            Conta cc = new ContaCorrente(547, 4321, "senhasupersecreta", 3500, pessoa, 6000);

            cc.salvarConta();

            Conta contaCarregada = Conta.carregarConta(cc.getAgencia(), cc.getNumero());

            if (contaCarregada == null) {
                System.out.println("Erro ao carregar a conta.");
            } else {

                System.out.println("Conta Carregada:");
                System.out.println(contaCarregada.toString());
            }

        } catch (IllegalArgumentException e) {
            System.out.println("ERRO: " + e.getMessage());
        } finally {
            System.out.println("Processo concluido com sucesso!");
        }
    }
}
