public class Main {

    public static void main (String[] args) {

        Cliente lucasF = new PessoaFisica("Rua da Amizade, 123", "Lucas Silva", "123.456.789-10", 30, 'M');

    Conta correnteF = new ContaCorrente(777,1111, lucasF, 100);

    try {
        correnteF.setLimite(500);
    } catch (IllegalArgumentException e){
        System.out.println(e.getMessage());
    }

    // Antes da serialização
    System.out.println(correnteF);

    // Serialização e Deserialização

    correnteF.serializeAccount();
    Conta invalidAcc = Conta.deserializeAccount(1119, 0323);

    System.out.println();
   
    if (invalidAcc != null) {
        System.out.println(invalidAcc);
    } else {
        System.out.println("Erro ao carregar o arquivo!");
    }

    System.out.println();
    System.out.println("Tentativa de abrir uma conta devidamente serializada: ");

    Conta validAcc = Conta.deserializeAccount(correnteF.getAgencia(), correnteF.getNumero());

    if (validAcc != null) {
        System.out.println(validAcc);
    } else {
        System.out.println("Erro ao carregar o arquivo!");
    }

  }
}