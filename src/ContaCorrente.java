import java.util.Scanner;

public class ContaCorrente extends Conta{

    //------------------------------------------ Metodo Construtor -----------------------------------------------//

    ContaCorrente(Cliente tipoCliente, double saldoInicial, double limiteMovimentacao){
        super(tipoCliente, saldoInicial);

        try {
            this.definirLimites(limiteMovimentacao);
        }catch (IllegalArgumentException ex){
            this.definirLimites(solicitarNovoValor());
        }

    }

    //---------------------------------------- Fim Metodo Construtor ---------------------------------------------//



    //--------------------------------------------- Definição Conta -----------------------------------------------//

    /**
     * Função usada para definir limites para contas;
     * @param limiteMovimentacao limite solicitado;
     */
    protected void definirLimites(double limiteMovimentacao){
        //Incluir valor de 0,05 relativo à cobrança de 0,05 para saque, limitando a -100 o saldo minimo da conta;
        this.setLimiteSaldoMinConta(-100 + 0.05);

        this.setLimiteMovimentacaoConta(validarLimiteMovimentacao(limiteMovimentacao));
    }

    /**
     * Valida que limite solicitado de movimentacao seja possitivo ou zero;
     * @param limiteSolicitado valor do limite solicitado;
     * @return valor do limite aprovado de movimentacao;
     */
    protected double validarLimiteMovimentacao(double limiteSolicitado){
        double limiteliberado = 0;

        if (limiteSolicitado > 0) limiteliberado = limiteSolicitado;
        else throw new IllegalArgumentException();

        return limiteliberado;
    }

    /**
     * Função usada para solicitar novamente o valor do limite de movimentação;
     * @return valor valido de limite de movimentação;
     */
    protected double solicitarNovoValor(){
        Scanner entrada = new Scanner(System.in);

        double valorLimiteMovimentacao = 0;
        boolean ERRO = true;

        while (ERRO){
            System.out.println("___ Valor de Movimentação Informado Invalido ___");
            System.out.println("___ Digite Um Novo Valor:");
            valorLimiteMovimentacao = entrada.nextDouble();
            ERRO = (valorLimiteMovimentacao < 0);
        }

        return valorLimiteMovimentacao;
    }

    /**
     * Usada para redefinir valor do limite de movimentação;
     */
    protected void redefinirValorLimite(){
        double newLimite = 0;
        try {
            Scanner entrada = new Scanner(System.in);

            System.out.println("==== REDEFINIR LIMITE MOVIMENTACAO ====");
            System.out.println("Digite o limite desejado: ");
            newLimite = validarLimiteMovimentacao(entrada.nextDouble());
        }catch (IllegalArgumentException ex){
            newLimite = solicitarNovoValor();
        }finally {
            this.setLimiteMovimentacaoConta(newLimite);
        }
    }

    //------------------------------------------- Fim Definição Conta ---------------------------------------------//



    //--------------------------------------------- Metodos Implements -------------------------------------------//

    /**
     * Calcula taxa de manutenção da conta;
     * @return Valor da taxa de manutenção;
     */
    @Override
    public double calcularTaxas() {
        double taxa = 0.0;

        if(this.getCliente() instanceof PessoaFisica){
            taxa = 10.00;
        }else if(this.getCliente() instanceof PessoaJuridica){
            taxa = 20.00;
        }

        return taxa;
    }

    /**
     * Imprime Valor da Taxa de manutenção da conta;
     */
    @Override
    public void imprimirExtratoTaxas() {
        System.out.println("TAXA DE MANUTENÇÃO: R$ " + this.calcularTaxas());
    }


    //------------------------------------------- Fim Metodos Implements -----------------------------------------//
}
