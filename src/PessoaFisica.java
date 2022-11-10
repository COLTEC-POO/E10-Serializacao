import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class PessoaFisica extends Cliente {

    //----------------------------------------- Atribultos Classe ------------------------------------------------//

    private String Cpf;
    private char Sexo;
    private Integer idade;

    //---------------------------------------- Fim Atribultos Classe ---------------------------------------------//



    //------------------------------------------ Metodo Construtor -----------------------------------------------//

    public PessoaFisica(String nome, String endereco, String cpf, Integer idade, char sexo){
        super(nome, endereco);
        setCpf(cpf);
        setIdade(idadeValidada(idade));
        setSexo(sexoValidado(sexo));
    }

    //---------------------------------------- Fim Metodo Construtor ---------------------------------------------//



    //--------------------------------------------- Metodos Get --------------------------------------------------//

    public String getCpf() {
        return Cpf;
    }

    public char getSexo() {
        return Sexo;
    }

    public Integer getIdade() {
        return idade;
    }

    //-------------------------------------------- Fim Metodos Get -----------------------------------------------//



    //--------------------------------------------- Metodos Set --------------------------------------------------//

    /**
     * Altera valor cpf;
     * @param cpf novo valor para cpf;
     */
    public void setCpf(String cpf){
        this.Cpf = cpf;
    }

    /**
     * Altera valor idade;
     * @param idade novo valor para idade;
     */
    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    /**
     * Altera valor sexo;
     * @param sexo novo valor para sexo;
     */
    public void setSexo(char sexo) {
        this.Sexo = sexo;
    }

    //-------------------------------------------- Fim Metodos Set -----------------------------------------------//



    //------------------------------------------ Metodos Autenticacao --------------------------------------------//

    /**
     * Aplicado para validar chave do cliente;
     * @param chave chave comparada com chave valida.
     * @return true = se chave certa, false = se chave incorreta;
     */
    public boolean autenticarChave(String chave){
        boolean chaveValida = Objects.equals(chave, this.getCpf());
        return chaveValida;
    }

    /**
     * Valida se CPF da conta é semelhate a de outra.
     * @param obj Conta a ser comparada.
     * @return True = se semelhante, false = se for unico.
     */
    public boolean equals(Object obj) {
        boolean NOT_ERRO, retorno = false;
        PessoaFisica contaComparada = (PessoaFisica) obj;

        NOT_ERRO = (contaComparada != null);

        if(NOT_ERRO){
            retorno = (Objects.equals(this.getCpf(), contaComparada.getCpf()));
        }

        return retorno;
    }

    //---------------------------------------- Fim Metodos Autenticacao ------------------------------------------//



    //--------------------------------------------- Metodos Validação --------------------------------------------//

    /**
     * Valida que informação idade informada corretamente.
     * @param idade Integer relativo à idade da pessoa.
     * @return Integer relativo à idade.
     */
    public Integer idadeValidada(Integer idade){
        Scanner entrada = new Scanner(System.in);
        boolean IDADEINVALIDA;

        IDADEINVALIDA = (idade < 0 || idade > 150);

        while (IDADEINVALIDA) {
            System.out.println("\n-----!IDADE INVALIDA TENTE NOVAMENTE!------\nDigite a idade: ");
            idade = entrada.nextInt();
            IDADEINVALIDA = (idade < 0 || idade > 150);
        }

        return idade;
    }

    /**
     * Valida que informação sexo foi informada corretamente.
     * @param sexo Caracter representando sexo F/M
     * @return Caracter validado.
     */
    public char sexoValidado(char sexo){
        Scanner entrada = new Scanner(System.in);
        boolean SEXOINVALIDO;

        sexo = Character.toUpperCase(sexo);

        SEXOINVALIDO = sexo != 'F' && sexo != 'M';

        while (SEXOINVALIDO) {
            System.out.println("\n-----!SEXO INVALIDO TENTE NOVAMENTE!------\nDigite o sexo [M/F]: ");
            sexo = entrada.next().charAt(0);
            SEXOINVALIDO = sexo != 'F' && sexo != 'M';
            entrada.nextLine();
        }

        return sexo;
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
                    "\nCPF: " + this.getCpf() +
                    "\n=========================";
        return text;
    }

    //------------------------------------------- Fim Metodos Impressão ------------------------------------------//

}
