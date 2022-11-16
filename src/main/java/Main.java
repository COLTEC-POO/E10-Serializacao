public class Main {
    public static void main(String[] args) {

        Cliente pessoa = new PessoaFisica("Josef", "Rua das Gracas",
                "123.456.789-12", 21, 'm');
        Conta cc = new ContaCorrente();
        cc.setDono(pessoa);
        cc.setNumeroConta(1);

        try {
            cc.setLimite(500);
            cc.depositar(2000);
            cc.sacar(5000);


        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

        } catch (ValorNegativoException e) {
            System.out.println(e.getMessage());
        } catch (SemLimiteException e) {
            System.out.println(e.getMessage());
        }

        cc.salvarContasNoArquivo();
        cc.carregaConta(1);

    }
}