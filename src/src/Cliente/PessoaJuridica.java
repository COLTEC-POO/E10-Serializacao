package Cliente;

// Classe interna PessoaJuridica que representa um cliente pessoa jurídica
public class PessoaJuridica extends Cliente {
    public String cnpj;
    public String setor;
    public int numFunc;

    // Construtor de PessoaJuridica
    public PessoaJuridica(String nome, String endereco, String setor, String cnpj, int numFunc) {
        // Chama o construtor da superClasse Cliente
        super(nome, endereco);

        this.cnpj = cnpj;
        this.numFunc = numFunc;
        this.setor = setor;
    }

    // Sobrescreve o método para retornar o tipo
    @Override
    public String getTipo() {
        return "Pessoa Juridica";
    }

    // Sobrescreve a função abstrata de autenticar
    @Override
    public boolean autenticar(String chave) {
        return this.cnpj.equals(chave);
    }

    // Sobrescreve a função toString() para exibir informações de PessoaJuridica
    @Override
    public String toString() {
        return "Pessoa Juridica: \n" +
                "Nome: " + nome + " \n" +
                "Endereço: " + endereco + " \n" +
                "Data: " + data + " \n" +
                "CNPJ: " + cnpj + "\n" +
                "NumFuncionarios: " + numFunc + "\n" +
                "Setor: " + setor;
    }

    // Sobrescreve a função equals para comparar objetos PessoaJuridica com base no CNPJ
    @Override
    public boolean equals(Object outroCliente) {
        if (outroCliente instanceof PessoaJuridica) {
            PessoaJuridica pessoaJuridica = (PessoaJuridica) outroCliente;

            return this.cnpj.equals(pessoaJuridica.cnpj);
        }
        return false;
    }
}