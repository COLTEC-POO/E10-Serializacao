import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.lang.Comparable;

public abstract class Operacao implements  ITaxas, Serializable, Comparable<Operacao>{

    //----------------------------------------- Atribultos Classe ------------------------------------------------//

    public static Integer nOperacoes = 0;
    public static int medotoComparacao = 1;

    private Date data;
    private char tipo;
    private double valor;


    //---------------------------------------- Fim Atribultos Classe ---------------------------------------------//



    //------------------------------------------ Metodo Construtor -----------------------------------------------//

    public Operacao(char tipo, double valor){
        setTipo(operacaoValida(tipo));
        this.valor = valor;
        data = new Date();
        setnOperacoes();
    }

    //---------------------------------------- Fim Metodo Construtor ---------------------------------------------//



    //--------------------------------------------- Metodos Get --------------------------------------------------//

    public static Integer getnOperacoes() {
        return nOperacoes;
    }

    public Date getData() {
        return data;
    }

    public char getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    //-------------------------------------------- Fim Metodos Get -----------------------------------------------//



    //--------------------------------------------- Metodos Set --------------------------------------------------//

    /**
     * Altera valor nOperacoes;
     */
    public static void setnOperacoes() {
        nOperacoes++;
    }

    /**
     * Altera valor data;
     * @param data  ovo valor para data;
     */
    private void setData(Date data) {
        this.data = data;
    }

    /**
     * Altera valor tipo de operacao;
     * @param tipo novo tipo de operacao;
     */
    private void setTipo(char tipo) {
        this.tipo = tipo;
    }

    /**
     * Altera valor da operacao;
     * @param valor novo valor para operacao;
     */
    private void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Altera metodo de ordenação do extrato;
     * @param novoMetodo Forma de impressão do extrato. 1 = Padrao, 0 = Ordenado;
     */
    public void setMedotoComparacao(Integer novoMetodo){
        medotoComparacao = novoMetodo;
    }

    //-------------------------------------------- Fim Metodos Set -----------------------------------------------//



    //--------------------------------------------- Metodos Validação --------------------------------------------//

    /**
     * Valida que informação operacao foi informada corretamente.
     * @param operacao Char relativo à operacao realizada.
     * @return Char relativo à operacao realizada.
     */
    public char operacaoValida(char operacao){
        Scanner entrada = new Scanner(System.in);
        boolean OPERACAOINVALIDA;

        operacao = Character.toUpperCase(operacao);
        OPERACAOINVALIDA = operacao != 'S' && operacao != 'D';

        while (OPERACAOINVALIDA) {
            System.out.println("\n-----!IDADE INVALIDA TENTE NOVAMENTE!------\nDigite a idade: ");
            operacao = entrada.next().charAt(0);
            OPERACAOINVALIDA = operacao != 'S' && operacao != 'D';
            entrada.nextLine();
        }

        return operacao;
    }

    //------------------------------------------- Fim Metodos Validação ------------------------------------------//



    //--------------------------------------------- Metodos Impressão --------------------------------------------//

    /**
     * Metodo Redefinido para retornar caracteristicas da Operacao.
     * @return String com caracteristicas.
     */
    public String toString(){
        String retorno = "DATA: " + this.getData() +
                " TIPO: "+ ((this.getTipo() == 's')? "SAQUE": "DEPOSITO") +
                " VALOR: " + this.getValor();

        return retorno;
    }

    //------------------------------------------- Fim Metodos Impressão ------------------------------------------//



    //--------------------------------------------- Metodos Implements -------------------------------------------//

    /**
     * Calcula taxas baseado no tipo de operacao realizada;
     * @return valor da taxa;
     */
    public double calcularTaxas(){
        double valorTaxa = 0;

        if(this.getTipo() == 'S')
            valorTaxa = 0.05;

        return  valorTaxa;
    };

    /**
     * Medoto usado para exibir valor da taxa cobrada.
     */
    public void imprimirExtratoTaxas() {
        if(this.getTipo() == 'S')
            System.out.println("SAQUE: R$ " + calcularTaxas());
        else if (this.getTipo() == 'D')
            System.out.println("SAQUE: R$ " + calcularTaxas());
    }

    @Override
    public int compareTo(Operacao o) {
        int retorno = 0;

        if(medotoComparacao == 0)
            retorno = Integer.compare(Character.compare(this.getTipo(), o.getTipo()), 0);
       /* else
            if(getData().compareTo(o.getData()) > 0)    retorno = 1;
            else if(getData().compareTo(o.getData()) < 0)   retorno = 1;*/

        return retorno;
    }

    //------------------------------------------- Fim Metodos Implements -----------------------------------------//
}
