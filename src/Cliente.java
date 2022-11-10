import java.io.Serializable;
import java.util.Date;

public abstract class Cliente implements Serializable {

    //----------------------------------------- Atribultos Classe ------------------------------------------------//

    private String Nome;
    private String endereco;

    //---------------------------------------- Fim Atribultos Classe ---------------------------------------------//



    //------------------------------------------ Metodo Construtor -----------------------------------------------//

    public Cliente(String nome, String endereco){
        this.setNome(nome);
        this.setEndereco(endereco);
    }

    //---------------------------------------- Fim Metodo Construtor ---------------------------------------------//



    //--------------------------------------------- Metodos Get --------------------------------------------------//

    public String getNome() {
        return Nome;
    }

    public String getEndereco() {
        return endereco;
    }

    //-------------------------------------------- Fim Metodos Get -----------------------------------------------//



    //--------------------------------------------- Metodos Set --------------------------------------------------//

    /**
     * Altera valor nome;
     * @param nome novo valor para nome;
     */
    public void setNome(String nome) {
        Nome = nome;
    }

    /**
     * Altera valor endereco;
     * @param endereco novo valor para endereco;
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    //-------------------------------------------- Fim Metodos Set -----------------------------------------------//



    //------------------------------------------ Metodos Autenticacao --------------------------------------------//

    /**
     * Aplicado em classes filhas para validar chave do cliente;
     * @param chave chave comparada com chave valida.
     * @return true = se chave certa, false = se chave incorreta;
     */
    public abstract boolean autenticarChave(String chave);

    //---------------------------------------- Fim Metodos Autenticacao ------------------------------------------//
}
