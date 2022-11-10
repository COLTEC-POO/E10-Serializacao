public class OperacaoDeposito extends Operacao {

    //------------------------------------------ Metodo Construtor -----------------------------------------------//

    public OperacaoDeposito(double valor){
        super('d', valor);
    }

    //---------------------------------------- Fim Metodo Construtor ---------------------------------------------//



    //--------------------------------------------- Metodos Impressão --------------------------------------------//

    /**
     * Metodo Redefinido para retornar caracteristicas da Operacao.
     * @return String com caracteristicas.
     */
    public String toString(){
        String retorno = "DATA: " + this.getData() +
                " TIPO: DEPOSITO" +
                " VALOR: " + this.getValor();

        return retorno;
    }

    //------------------------------------------- Fim Metodos Impressão ------------------------------------------//

}
