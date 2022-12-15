public class Gerente extends Funcionario{

    public  boolean concederEmprestimo(){
        //Conceder um emprestimo
        return true;
    }
    public double calcularComissao(){
        return this.getSalario() * 0.2;
    }

}
