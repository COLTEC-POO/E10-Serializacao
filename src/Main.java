public class Main {
    public static void main(String[] args){

        ContaCorrente conta1 = new ContaCorrente(1, "200", 250, "karen");



        try {
            conta1.sacar(-1);
            conta1.sacar(-2);
            conta1.depositar(-3);
        } catch(ValorNegativoException e) {
            System.out.println(e.getMessage());
        }





    }
}
