import java.io.IOException;
import java.io.Serializable;

public class Main {
    public static void main (String[]args)  {

        //cadastro pessoas juridica
        PessoaJuridica Sorvebao=new PessoaJuridica();
        Sorvebao.setNome("Sorvebao");
        Sorvebao.setEndereco("Rua da Prata,111 - Itabirito");
        Sorvebao.setCnpj("11.101.102/0001-99");
        Sorvebao.setNumFuncionarios(50);
        Sorvebao.setSetor("Alimenticio");

        //cadastro pessoas fisicas
        //Jonas
        PessoaFisica Jonas=new PessoaFisica();
        Jonas.setNome("Jonas");
        Jonas.setEndereco("Rua C,20-Horto");
        Jonas.setcpf("001.002.003-55");
        Jonas.setidade(18);
        Jonas.setGenero('m');


        //inicialização das Contas
        Conta jonas= new ContaCorrente(Jonas,"1","22");
        Conta sorvebao=new ContaCorrente(Sorvebao,"55","11");


        //Tratamento de exceções
        //testes
        try{

            //depositando dinheiro na conta
            //jonas.depositar(800);
            sorvebao.depositar(300);

            //sacando dinheiro na conta
            //jonas.sacar(500);
           // jonas.sacar(50);
            //jonas.sacar(18);
            //sorvebao.sacar(1230);
            sorvebao.sacar(250);

            //testa salvar conta
            sorvebao.salvaconta(sorvebao);
            jonas.salvaconta(jonas);

        }catch( ValorNegativoException e){
            System.out.println(e.getMessage());

        }catch( SemLimiteException e){
            System.out.println(e.getMessage());

        }finally {
            //ultimos comandos que serão executados no bloco try catch
            System.out.println("Obrigado por utilizar o sistema!");
        }

    }

}
