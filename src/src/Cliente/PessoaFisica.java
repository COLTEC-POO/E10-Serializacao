package Cliente;

// Classe interna PessoaFisica que representa um cliente pessoa física
public class PessoaFisica extends Cliente {
    public String cpf;
    public int idade;
    public char sexo;

    // Construtor de PessoaFisica
    public PessoaFisica(String nome, String endereco, String cpf, int idade, char sexo) {
        // Chama o construtor da superClasse Cliente
        super(nome, endereco);

        this.cpf = cpf;
        this.idade = idade;
        this.sexo = sexo;
    }

    // Sobrescreve o método para retornar o tipo
    @Override
    public String getTipo() {
        return "Pessoa Fisica";
    }

    // Sobrescreve a função abstrata acima
    @Override
    public boolean autenticar(String chave) {

        return this.cpf.equals(chave);
    }

    // Sobrescreve a função toString() para exibir informações de PessoaFisica
    @Override
    public String toString() {
        return "Pessoa Fisica: \n" +
                "Nome: " + nome + " \n" +
                "Endereço: " + endereco + " \n" +
                "Data: " + data + " \n" +
                "CPF: " + cpf + "\n" +
                "Idade: " + idade + "\n" +
                "Sexo: " + sexo;
    }

    // Sobrescreve a função equals para comparar objetos PessoaFisica com base no CPF
    @Override
    public boolean equals(Object outroCliente) {
        if (outroCliente instanceof PessoaFisica) {
            PessoaFisica pessoaFisica = (PessoaFisica) outroCliente;

            return this.cpf.equals(pessoaFisica.cpf);
        }
        return false;
    }
}
