
import java.util.Date;
public abstract class Cliente {
    protected String nome;
    protected String endereco;
    protected Date dataCriacao;

    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataCriacao = new Date();
    }

    public void imprimir() {
        System.out.println("Cliente Inválido");
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }
    public abstract boolean autenticar(String chave);
    public abstract String getTipo();

}
