// Importação das subclasses de Cliente do pacote Cliente
import Cliente.Cliente;
import Cliente.PessoaFisica;
import Cliente.PessoaJuridica;

// Importação das subclasses de Conta do pacote Conta
import Conta.Conta;
import Conta.ContaCorrente;
import Conta.ContaPoupanca;
import Conta.ContaUniversitaria;
import Exceptions.LimiteException;
import Exceptions.NegativoException;
import Exceptions.setArgumentException;

// Importação de bibliotecas Java
import java.util.List;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws setArgumentException {

        // Inicialização de dados
        PessoaFisica pF = new PessoaFisica("João", "Rua A", "12345678900", 30, 'M');
        ContaPoupanca contaP = new ContaPoupanca(1, "123", 20000, "João", 1000, pF);

        PessoaFisica duqueP = new PessoaFisica("Duque", "Rua A", "12345678901", 18, 'M');
        ContaCorrente duque = new ContaCorrente(2, "12", 200, "Duque", 200, duqueP);

        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(pF);
        listaClientes.add(duqueP);

        System.out.println("======Sistema Bancário======");

        System.out.println("Donos registrados:");
        for(Cliente c: listaClientes) {
            System.out.println(c.nome);
        }
        System.out.println();

        duque.salvarConta();

        // Carregar a conta
        Conta contaCarregada = Conta.carregarConta("2-Duque.txt");
        if (contaCarregada != null) {
            System.out.println("Conta carregada:\n" + contaCarregada);
        }
    }

}