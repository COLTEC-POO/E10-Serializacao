import java.io.Serializable;
import java.util.Date;

public abstract class Cliente implements Serializable {

    String endereco;
    String nome;
    Date data;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
// construtor

    public Cliente() {
        this.endereco = endereco;
        this.nome = nome;
        this.data = new Date();
    }

    public void imprimir(){
        System.out.println("Cliente inválido");
    }

    public abstract boolean autenticar(String chave);
}