public class OperacaoSaque extends Operacao{

    //------------------------------------------ Metodo Construtor -----------------------------------------------//

    public OperacaoSaque(double valor){
        super('s', valor);
    }

    //---------------------------------------- Fim Metodo Construtor ---------------------------------------------//



    //--------------------------------------------- Metodos Impressão --------------------------------------------//

    /**
     * Metodo Redefinido para retornar caracteristicas da Operacao.
     * @return String com caracteristicas.
     */
    public String toString(){
        String retorno = "DATA: " + this.getData() +
                " TIPO: SAQUE" +
                " VALOR: " + this.getValor();

        return retorno;
    }

    //------------------------------------------- Fim Metodos Impressão ------------------------------------------//

    //--------------------------------------------- Metodos Implements -------------------------------------------//


    //------------------------------------------- Fim Metodos Implements -----------------------------------------//

}
