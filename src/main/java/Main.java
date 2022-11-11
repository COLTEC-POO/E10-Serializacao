public class Main {
    public static void main(String[] args) {

        // inicialização Cliente

        PessoaFisica jessica = new PessoaFisica();
        jessica.setNome("Jéssica Alves");
        jessica.setCpf("123.455.789-11");
        jessica.setEndereco("Rua Laranjeiras");
        jessica.setIdade(21);
        jessica.setSexo('f');

        PessoaFisica mateus = new PessoaFisica();
        mateus.setNome("Mateus Silva");
        mateus.setCpf("121.555.999-10");

        PessoaJuridica empresa = new PessoaJuridica();
        empresa.setNome("Banco X");
        empresa.setCnpj("10.510.789/0001-12");
        empresa.setEndereco("Avenida Raja Gabaglia, 822");
        empresa.setSetor("Financeiro");
        empresa.setNumFuncionarios(52);

        PessoaJuridica empresa2 = new PessoaJuridica();
        empresa2.setCnpj("10.510.789/0001-12");

        // inicialização Conta

        Conta minhaConta = new ContaUniversitaria();
        Conta suaConta = new ContaPoupanca();
        Conta suaEmpresa = new ContaCorrente();

        minhaConta.setDono(jessica);
        minhaConta.setNumConta(1111);
        minhaConta.setAgencia(1110);
        minhaConta.setSaldo(0);

        suaConta.setDono(mateus);
        suaConta.setNumConta(2222);
        suaConta.setAgencia(2220);

        suaEmpresa.setDono(empresa);
        suaEmpresa.setNumConta(3333);
        suaEmpresa.setAgencia(3330);
        suaEmpresa.setSaldo(0);

        // Tratamento de Exception para Conta Universitária

        try {
            minhaConta.setLimite(100); // limite mínimo e máximo 0 e 500
            minhaConta.depositar(200);
            minhaConta.sacar(50);
            //System.out.println(minhaConta.toString());
        } catch (ValorNegativoException ex) {
            System.out.println("Erro: Valor de depósito ou saque inválido\n" + ex.getMessage());
        } catch (SemLimiteException ex) {
            System.out.println("Erro: Limite inválido\n" + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("Erro: Limite inválido para este tipo de conta" + ex.getMessage());
        }

        System.out.print("\n");

        // Tratamento de Exception para Conta Corrente

        try {
            suaEmpresa.setLimite(1000); // limite mínimo -100
            suaEmpresa.depositar(900);
            suaEmpresa.sacar(400);
            //System.out.println(suaEmpresa.toString());
        } catch (ValorNegativoException ex) {
            System.out.println("Erro: Valor de depósito ou saque inválido\n" + ex.getMessage());
        } catch (SemLimiteException ex) {
            System.out.println("Erro: Limite inválido\n" + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("Erro: Limite inválido para este tipo de conta" + ex.getMessage());
        }

        // Serialização

        minhaConta.gravarDados(); // Agencia: 1110 Conta: 1111
        suaConta.gravarDados(); // Agencia: 2220 Conta: 2222
        suaEmpresa.gravarDados(); // Agencia: 3330 Conta: 3333

        Conta.lerDados(1110, 1111);

    }
}