import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main (String [] args) {

        double media;

        ContaUniversitaria minhaConta;
        minhaConta = new ContaUniversitaria ();

        ContaCorrente novaConta;
        novaConta = new ContaCorrente ();

        PessoaFisica brenda;
        brenda = new PessoaFisica ();

        PessoaJuridica novoCliente;
        novoCliente = new PessoaJuridica ();

        minhaConta.setDono(brenda);
        minhaConta.setNumeroDaConta(101);
        minhaConta.setNumeroDaAgencia(001);
        try {
            minhaConta.setLimite(500);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        minhaConta.salvarContas();

        brenda.nome = "Brenda Sales";
        brenda.idade = 24;
        brenda.sexo = 'F';
        brenda.CPF = "000.000.000-00";
        brenda.endereco = "Rua X, bairro Y.";


        novaConta.setDono(novoCliente);
        novaConta.setNumeroDaConta(202);
        novaConta.setNumeroDaAgencia(002);
        try {
            novaConta.setLimite(20000);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        novaConta.salvarContas();

        novoCliente.nome = "Empresa";
        novoCliente.setor = "TI";
        novoCliente.cnpj = "111.111.111-11";
        novoCliente.numFuncionarios = 150;
        novoCliente.endereco = "Rua Y, bairro X.";

        System.out.println(minhaConta);
        System.out.println ("------------------------------------------");
        System.out.println(novaConta);
        System.out.println ("------------------------------------------");

        minhaConta.carregaConta(1,101);

        System.out.println(minhaConta);

//        try {
//            minhaConta.depositar(5000);
//            System.out.println("Saldo atual: R$ " + minhaConta.getSaldo());
//            minhaConta.sacar(400);
//            System.out.println("Saldo atual: R$ " + minhaConta.getSaldo());
//            minhaConta.transferir(novaConta, 500);
//            System.out.println("Saldo atual conta de origem: R$ " + minhaConta.getSaldo());
//            System.out.println("Saldo atual conta de destino: R$ " + novaConta.getSaldo());
//            minhaConta.depositar(2000);
//            System.out.println("Saldo atual: R$ " + minhaConta.getSaldo());
//            minhaConta.depositar(-100);
//            System.out.println("Saldo atual: R$ " + minhaConta.getSaldo());
//
//        }catch (ValorNegativoException e){
//            System.out.println(e.getMessage());
//        }catch (SemLimiteException e){
//            System.out.println((e.getMessage()));
//        }
//
//        System.out.println ("------------------------------------------");
//        minhaConta.imprimirExtrato();
//        System.out.println ("------------------------------------------");
//
//        media = (float)Operacao.totalOperacoes / (float)Conta.totalDeContas;
//        System.out.println("Media do numero de operações em relação ao numero de contas: " + media);
//
//        System.out.println ("\n");
//
//        minhaConta.imprimirExtratoTaxas();
//
//        System.out.println ("\n");
//
//        novaConta.imprimirExtratoTaxas();

    }
}

