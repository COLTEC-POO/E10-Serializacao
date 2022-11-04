import java.util.Scanner;

public class main {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        System.out.println("Bem Vindo ao sistema Bancario");
        
        Conta contaJoao, contaAtivo, contaMaria, contaClick;
        Cliente temp;
        Agencia agencia = new Agencia(0001);
        
        try{
            temp = new PessoaFisica("João", "Av Antonio Carlos, Coltec, Belo Horizonte MG", "15796345788", 33, "M");
            contaJoao = new ContaCorrente(temp , true, 500.00, 100.00, 6541, agencia);
            temp = new PessoaFisica("Maria", "Av Antonio Carlos, Coltec, Belo Horizonte MG", "22222222222", 24, "F");
            contaMaria = new ContaCorrente(temp , true, 8_422.00, 100.00, 9636, agencia);

            temp = new PessoaJuridica("Ativo247", "Av Antonio Carlos, Lagoinha, Belo Horizonte MG", "111222333000159", 12, "Informatica");
            contaAtivo = new ContaCorrente(temp , true, 3_422.00, 20.00, 92_365_636, agencia);
            temp = new PessoaJuridica("ClickAtende", "Rua Brasileia, Ouro Preto,  Belo Horizonte MG", "111222333000159", 3, "Eletronico");
            contaClick = new ContaCorrente(temp , true, 13_422.00, 1000.00, 96_992_036, agencia);

            BinDataManage fileJoao = new BinDataManage(contaJoao.getAgenciaConta());
            fileJoao.FileWrite(contaJoao);

            contaJoao = (Conta) fileJoao.FileRead();

            contaJoao.imprimirSaldo();
            contaMaria.imprimirSaldo();

            contaJoao.mostraConta();
            contaJoao.mostraConta(true);
            contaMaria.mostraConta();
            contaMaria.mostraConta(true);

            contaJoao.mostraExtrato();

            System.out.println("Quanto deseja transferir: ");
            Double valor = input.nextDouble();
            contaJoao.transferir(contaMaria, valor);
            
            contaJoao.imprimirSaldo();
            contaMaria.imprimirSaldo();

            contaJoao.mostraExtrato();
            
            contaJoao.depositar(2500.0);
            contaJoao.depositar(1500.0);
            contaJoao.sacar(200.0);
            contaJoao.sacar(3000.0);
            contaJoao.depositar(150.0);
            contaJoao.sacar(50.0);
    
            contaAtivo.depositar(20500.0);
            contaAtivo.depositar(11500.0);
            contaAtivo.sacar(20000.0);
    
            contaClick.depositar(2000.0);
            contaClick.depositar(500.0);
            contaClick.sacar(2000.0);

            
            contaJoao.mostraExtrato();
            contaMaria.mostraExtrato();
            contaClick.mostraExtrato();
            contaAtivo.mostraExtrato();
            
            System.out.println("Media de Operações: " + contaJoao.getMedia());
            
            contaJoao.imprimeExtratoTaxas();
            


            input.close();
        } catch(IllegalArgumentException e){
            e.printStackTrace();
        } catch(NegativeValueException e){
            e.printStackTrace();
        } catch(InvalidLimitValueException e){
            e.printStackTrace();
        }
    }
}