import java.io.Serializable;

public class PessoaFisica extends Cliente implements Serializable {

    private String cpf;
    private int idade;
    private char sexo;

    public PessoaFisica(String nome, String endereco, String cpf, int idade, char sexo) {
        super(nome, endereco);
        this.cpf = cpf;
        this.idade = idade;
        this.sexo = sexo;
    }

    @Override
    public boolean autenticar(String chave) {
        return chave.equals(this.getCpf());
    }

    @Override
    public String toString() {
        return "PessoaFisica{" +
                "nome='" + this.getNome() + '\'' +
                "endereco='" + this.getEndereco() + '\'' +
                "cpf='" + cpf + '\'' +
                ", idade=" + idade +
                ", sexo=" + sexo +
                '}';
    }

    @Override
    public boolean equals(Object outro) {
        if (outro instanceof PessoaFisica) {
            PessoaFisica outraPf = (PessoaFisica) outro;
            return this.getCpf().equals(outraPf.getCpf());
        }
        return false;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public double calcularTaxas() {
        return 10.0;
    }
}
