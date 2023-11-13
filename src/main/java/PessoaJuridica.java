import java.io.Serializable;

public class PessoaJuridica extends Cliente implements Serializable {

    private String cnpj;
    private int numFuncionarios;
    private String setor;

    public PessoaJuridica(String nome, String endereco, String cnpj, int numFuncionarios, String setor) {
        super(nome, endereco);
        this.cnpj = cnpj;
        this.numFuncionarios = numFuncionarios;
        this.setor = setor;
    }

    @Override
    public boolean autenticar(String chave) {
        return chave.equals(this.getCnpj());
    }


    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "nome='" + this.getNome() + '\'' +
                "endereco='" + this.getEndereco() + '\'' +
                "cnpj='" + cnpj + '\'' +
                ", numFuncionarios=" + numFuncionarios +
                ", setor='" + setor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object outro) {
        if(outro instanceof PessoaJuridica) {
            PessoaJuridica outraPj = (PessoaJuridica) outro;
            return this.getCnpj().equals(outraPj.getCnpj());
        }
        return false;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getNumFuncionarios() {
        return numFuncionarios;
    }

    public void setNumFuncionarios(int numFuncionarios) {
        this.numFuncionarios = numFuncionarios;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    @Override
    public double calcularTaxas() {
        return 20.0;
    }
}
