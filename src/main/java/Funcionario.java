public abstract class Funcionario {


    private int matricula;
    private String nome;
    private double salario;

    private String cargo;

    public abstract double calcularComissao();
    /*public String toString(){
        return "Matricula: "+this.getMatricula()+"\n"+"Nome: "+this.getNome()+"\n"+"Salario: "+this.getSalario();
    }*/

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
