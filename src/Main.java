import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        adicionaClientes();
        try {
            criaContas();

            contas.get(0).tranferencia(contas.get(0),50_000,"11.222.123/0001-00");
            contas.get(0).saque(1500,"111.111.111-11");
            contas.get(0).imprimirExtato(1);

        } catch (ValorNegativoException | SemLimiteException ex){
            System.out.println(ex.getMessage());
        }catch (IOException o){
            System.out.println(o.getStackTrace());
        }
    }

    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Conta> contas = new ArrayList<>();

    public static void adicionaClientes(){
        Cliente EmpresaA = new PessoaJuridica("EmpresaA", "Rua X", "Tec", "11.222.123/0001-00",7);
        Cliente Wallace = new PessoaFisica("Wallace", "Rua A", "111.111.111-11", 24, 'M');

        clientes.add(EmpresaA);
        clientes.add(Wallace);

    }

    public static void criaContas(){

        Conta ContaEmpresaA = new ContaCorrente(clientes.get(0),100_000,50000.0);
        Conta ContaWallace = new ContaCorrente(clientes.get(1), 1500.00, 850.0);

        contas.add(ContaEmpresaA);
        contas.add(ContaWallace);

    }

    public static void carregaContas(String agencia, Integer numConta){
        try {
            FileInputStream fileInputStream = new FileInputStream(agencia + numConta + ".ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Conta contaX = (Conta) objectInputStream.readObject();
            contas.add(contaX);
            objectInputStream.close();
        }catch (IOException ex){
            System.out.println(ex.getStackTrace());
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
