public class Relatorio {
    public void imprimirComissoes(Funcionario[] funcionarios){

        for(Funcionario fAtual : funcionarios){
            System.out.println("Comissão de "+fAtual.getNome()+": "+fAtual.calcularComissao());
        }
    }
}
