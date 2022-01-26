package main.java;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PessoaFisica TaldoSidoka = new PessoaFisica();
        PessoaJuridica PastelãoChina = new PessoaJuridica();
        Conta contas0 = new ContaCorrente();
        Conta contas1 = new ContaPoupanca();
        contas0.setMemoryCliente(TaldoSidoka);
        contas0.setMemoryId(1);
        contas0.setLimite(500.0D);
        contas0.setMemoryAgencia(1100);
        TaldoSidoka.idade = 20;
        TaldoSidoka.cpf = "164-192-554-888-54";
        TaldoSidoka.endereco = "Rua: Sinuka - Numero: 915 - Bairro: Diamantina";
        TaldoSidoka.sexo = 'M';
        TaldoSidoka.nome = "Taldo Sidoka";
        contas1.setMemoryCliente(PastelãoChina);
        contas1.setMemoryId(2);
        contas1.setLimite(1000.0D);
        contas1.setMemoryAgencia(1200);
        PastelãoChina.cnpj = "154-326-644-658-54";
        PastelãoChina.endereco = "Rua: Paraná - Numero: 125 - Bairro: Diamantina";
        PastelãoChina.numFuncionarios = 20;
        PastelãoChina.setor = "Pastelaria";
        PastelãoChina.nome = "Pastelão do China";
        contas0.saveContas();
        contas1.saveContas();

        byte Menu;
        do {
            System.out.println();
            System.out.println("------------------");
            System.out.println("- Banco Intactoz -");
            System.out.println("------------------");
            System.out.println("1- Dados Clientes");
            System.out.println("2- Deposito");
            System.out.println("3- Saque");
            System.out.println("4- Transferência");
            System.out.println("5- Media");
            System.out.println("6- Extrato");
            System.out.println("7- Taxas");
            System.out.println("8- Sacar Limite");
            System.out.println("9- Serialização");
            System.out.println("10- Exit");
            System.out.println("------------------");
            System.out.print("Digite a opção desejada: ");
            Menu = input.nextByte();
            System.out.println();
            switch(Menu) {
                case 0:
                    break;
                case 1:
                    System.out.println("----------Banco Intactoz---------");
                    System.out.println(contas0);
                    System.out.println("----------------------------------");
                    System.out.println(contas1);
                    System.out.println("----------------------------------");
                    break;
                case 2:
                    try {
                        contas0.depositar(100.0D);
                        contas1.depositar(100.0D);
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println("Deposito realizado com Sucesso!");
                        System.out.println("          ------------          ");
                        System.out.println("Dados Atualizados apos deposito:");
                        System.out.println("----------------------------------");
                        System.out.println("Novo Saldo " + TaldoSidoka.nome + ":" + contas0.getMemorySaldo());
                        System.out.println("Novo Saldo " + PastelãoChina.nome + ":" + contas1.getMemorySaldo());
                        System.out.println("----------------------------------");
                    } catch (ValorNegativoException var13) {
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println(var13.getMessage());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 3:
                    try {
                        contas0.sacar(50.0D);
                        contas1.sacar(50.0D);
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println("Saque realizado com Sucesso!");
                        System.out.println("          ------------          ");
                        System.out.println("Dados Atualizados apos saque:");
                        System.out.println("----------------------------------");
                        System.out.println("Novo Saldo " + TaldoSidoka.nome + ":" + contas0.getMemorySaldo());
                        System.out.println("Novo Saldo " + PastelãoChina.nome + ":" + contas1.getMemorySaldo());
                        System.out.println("----------------------------------");
                    } catch (ValorNegativoException var12) {
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println(var12.getMessage());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 4:
                    try {
                        contas0.transferir(contas1, 50000.0D);
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println("Transferência realizado com Sucesso!");
                        System.out.println("          ------------          ");
                        System.out.println("Dados Atualizados apos transferência:");
                        System.out.println("----------------------------------");
                        System.out.println("Novo Saldo " + TaldoSidoka.nome + ":" + contas0.getMemorySaldo());
                        System.out.println("Novo Saldo " + PastelãoChina.nome + ":" + contas1.getMemorySaldo());
                        System.out.println("----------------------------------");
                    } catch (ValorNegativoException var11) {
                        System.out.println(var11.getMessage());
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println(var11.getMessage());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 5:
                    double media = (double)(Operacao.totalOperacoes / Conta.totalContas);
                    System.out.println("---------------------------Banco Intactoz-----------------------------");
                    System.out.println("Media do numero de operações em relação ao numero de contas: " + media);
                    System.out.println("----------------------------------------------------------------------");
                    break;
                case 6:
                    System.out.println("-------------------Banco Intactoz------------------");
                    System.out.println("Conta 1:");
                    contas0.imprimirExtrato(1);
                    System.out.println("----------------------------------------------------");
                    System.out.println("Conta 2:");
                    contas1.imprimirExtrato(2);
                    break;
                case 7:
                    System.out.println("-------------------Banco Intactoz------------------");
                    System.out.println("Conta 1:");
                    contas0.ImprimeTaxas();
                    System.out.println("----------------------------------------------------");
                    System.out.println("Conta 2:");
                    contas1.ImprimeTaxas();
                    break;
                case 8:
                    try {
                        contas0.sacarLimite(200.0D);
                        contas1.sacarLimite(-500.0D);
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println("Saque de limite realizado com Sucesso!");
                        System.out.println("          ------------          ");
                        System.out.println("Dados Atualizados apos deposito:");
                        System.out.println("----------------------------------");
                        System.out.println("Novo Saldo " + TaldoSidoka.nome + ":" + contas0.getMemorySaldo());
                        System.out.println("Novo Saldo " + PastelãoChina.nome + ":" + contas1.getMemorySaldo());
                        System.out.println("----------------------------------");
                        System.out.println("Novo Limite " + TaldoSidoka.nome + ":" + contas0.getMemoryLimite());
                        System.out.println("Novo Limite " + PastelãoChina.nome + ":" + contas1.getMemoryLimite());
                        System.out.println("----------------------------------");
                    } catch (SemLimiteException var10) {
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println(var10.getMessage());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 9:
                    System.out.println("----------Banco Intactoz---------");
                    contas0.showConta(1100, 1);
                    System.out.println("Arquivo 1100-1.ser criado");
                    System.out.println("Lendo e desserializando dados");
                    System.out.println("----------------------------------");
                    contas1.showConta(1200, 2);
                    System.out.println("Arquivo 1200-2.ser criado");
                    System.out.println("Lendo e desserializando dados");
                    System.out.println("----------------------------------");
                    break;
                case 10:
                    System.out.println("Saindo do Banco Intactoz...");
                    System.exit(0);
                default:
                    System.out.println("Error: Você escolheu uma opção invalida...");
            }
        } while(Menu != 10);

    }
}

