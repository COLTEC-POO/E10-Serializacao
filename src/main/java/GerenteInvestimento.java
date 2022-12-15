public class GerenteInvestimento extends Gerente{

    public double calcularComissao(){
        return this.getSalario() * 0.4;
    }
}
