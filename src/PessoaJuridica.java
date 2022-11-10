import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class PessoaJuridica extends Cliente{

    //----------------------------------------- Atribultos Classe ------------------------------------------------//

    String cnpj;
    String setor;
    Integer numFuncionarios;

    //---------------------------------------- Fim Atribultos Classe ---------------------------------------------//



    //------------------------------------------ Metodo Construtor -----------------------------------------------//

    public PessoaJuridica(String nome, String endereco, String setor, String cnpj, Integer numFuncionarios){
        super(nome, endereco);
        setCnpj(cnpj);
        setSetor(setor);
        setNumFuncionarios(numFuncionariosValidado(numFuncionarios));
    }

    //---------------------------------------- Fim Metodo Construtor ---------------------------------------------//



    //--------------------------------------------- Metodos Get --------------------------------------------------//

    public String getCnpj() {
        return cnpj;
    }

    public String getSetor() {
        return setor;
    }

    public Integer getNumFuncionarios() {
        return numFuncionarios;
    }

    //-------------------------------------------- Fim Metodos Get -----------------------------------------------//



    //--------------------------------------------- Metodos Set --------------------------------------------------//

    /**
     * Altera valor cnpj;
     * @param cnpj novo valor para cnpj;
     */
    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }

    /**
     * Altera valor numFuncionarios;
     * @param numFuncionarios novo valor para numFuncionarios;
     */
    public void setNumFuncionarios(Integer numFuncionarios) {
        this.numFuncionarios = numFuncionarios;
    }

    /**
     * Altera valor setor;
     * @param setor novo valor para setor;
     */
    public void setSetor(String setor) {
        this.setor = setor;
    }

    //-------------------------------------------- Fim Metodos Set -----------------------------------------------//



    //------------------------------------------ Metodos Autenticacao --------------------------------------------//

    /**
     * Aplicado para validar chave do cliente;
     * @param chave chave comparada com chave valida.
     * @return true = se chave certa, false = se chave incorreta;
     */
    public boolean autenticarChave(String chave){
        boolean chaveValida = Objects.equals(chave, this.getCnpj());
        return chaveValida;
    }

    /**
     * Valida se CNPJ da conta é semelhate a de outra.
     * @param obj Conta a ser comparada.
     * @return True = se semelhante, false = se for unico.
     */
    public boolean equals(Object obj) {
        boolean NOT_ERRO, retorno = false;
        PessoaJuridica contaComparada = (PessoaJuridica) obj;

        NOT_ERRO = (contaComparada != null);

        if(NOT_ERRO){
            retorno = (Objects.equals(this.getCnpj(), contaComparada.getCnpj()));
        }

        return retorno;
    }

    //---------------------------------------- Fim Metodos Autenticacao ------------------------------------------//



    //--------------------------------------------- Metodos Validação --------------------------------------------//

    /**
     *Valida que informação idade informada corretamente.
     * @param numFuncionarios Integer relativo à numFuncionarios da pessoa.
     * @return Integer relativo à numFuncionarios.
     */
    public Integer numFuncionariosValidado(Integer numFuncionarios){
        Scanner entrada = new Scanner(System.in);
        boolean NUMFUNC_INVALIDO;

        NUMFUNC_INVALIDO = (numFuncionarios < 1);

        while (NUMFUNC_INVALIDO) {
            System.out.println("\n-----!NUMERO DE FUNCIONARIOS INVALIDO!------\nDigite o numero de funcionarios: ");
            numFuncionarios = entrada.nextInt();
            NUMFUNC_INVALIDO = (numFuncionarios < 1);
        }

        return numFuncionarios;
    }

    //------------------------------------------- Fim Metodos Validação ------------------------------------------//



    //--------------------------------------------- Metodos Impressão --------------------------------------------//

    /**
     * Metodo Redefinido para retornar caracteristicas da Pessoa Fisica.
     * @return String com caracteristicas.
     */
    public String toString(){
        String text = "\n===== DADOS CLIENTE =====" +
                    "\nNOME: " + getNome() +
                    "\nCNPJ: " + this.getCnpj() +
                    "\n=========================";
        return text;
    }

    //------------------------------------------- Fim Metodos Impressão ------------------------------------------//

}
