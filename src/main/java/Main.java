import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bem vindo ao sistema Bancário");

        
        Cliente titanBr0202 = 
        new ClientePessoaFisica("Eduardo","Rua Mexico",20,"11111111",'m');
      Conta arthur = new ContaCorrente("Arthur",25,"22222222",true);
      Conta joaoMontandon;
      if(titanBr0202.autenticar("11111112")){
        System.out.println("Autenticação valida");
      }
      else{
        System.out.println("Autenticação invalida");
      }
        arthur.contaArquivoSerializa();
      joaoMontandon = contaArquivoDeserializa("Joao",29);
        System.out.println(joaoMontandon);
      
    }
    private static Conta contaArquivoDeserializa(String nome, int numConta) {
        try{String arquivoDeseri = nome+"-"+numConta+".ser";
            FileInputStream fileStream = new FileInputStream(arquivoDeseri);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);

            Conta conta = (Conta) objectStream.readObject();
            objectStream.close();
            return conta;}
            catch (ClassNotFoundException e) {
            System.out.println("Dados não encontrados");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}