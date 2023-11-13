import java.io.Serializable;
import java.util.Date;

public abstract class Cliente implements ITaxas, Serializable {

    private String nome;
    private String endereco;
    private Date dataCriacao;

    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataCriacao = new Date();
    }

    public abstract boolean autenticar(String chave);

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }
}
