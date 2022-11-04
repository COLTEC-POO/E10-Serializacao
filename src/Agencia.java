import java.io.Serializable;

public class Agencia implements Serializable{

    private Integer numero;

    /*public void setNumero(Integer numero){
        this.numero = numero;
    }*/

    public Integer getNumero(){
        return this.numero;
    }
    public String getAgencia(){
        return "" + this.numero;
    }

    public Agencia(Integer numero){
        this.numero = numero;
    }

    public String toString(){
        return "" + this.numero;
    }
}
