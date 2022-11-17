import javax.swing.*;
import java.io.*;

public class Main {

    public static void main(String[] args){
        System.out.println("Bem vindo ao sistema Bancário\n");

        Cliente cliente1 = new PessoaFisica("Eduardo", "Belo Horizonte",20, "12345679-01", 'M');
        Cliente cliente2 = new PessoaJuridica("Coltec", "Av Antônio Carlos", "123465789-00", 30, "Educação");
        Conta contaTeste = new ContaCorrente(89,1251, -100, cliente1);
        Conta contaTeste2 = new ContaPoupanca(95, 1575, 800, cliente2);
        Conta contaTeste3;

        try {
            contaTeste2.depositar(135);
            contaTeste2.sacar(35);
            contaTeste2.sacar(100);
            contaTeste2.depositar(1500);
            contaTeste2.sacar(870);
            contaTeste2.sacar(213);
            contaTeste2.depositar(750);
            contaTeste2.depositar(600);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        contaTeste2.serializarConta();

        contaTeste3 = desserializarConta(96, 1575);
        System.out.println(contaTeste3);

    }

    private static Conta desserializarConta(int agencia, int numConta) {
        try{
            String arquivoEntrada = agencia+"-"+numConta+".ser";
            FileInputStream fStream = new FileInputStream(arquivoEntrada);
            ObjectInputStream oStream = new ObjectInputStream(fStream);

            Conta conta = (Conta) oStream.readObject();
            oStream.close();
            return conta;

        } catch (IOException e) {
            System.out.println("Não foi possível encontrar a conta solicitada");
        } catch (ClassNotFoundException e) {
            System.out.println("Não foi possível encontrar as informações da conta");
        }
        return null;
    }

}