public class ContaPoupanca extends Conta implements ITaxas{
    

    public ContaPoupanca(Cliente cliente, Boolean tipo, Double saldo_inicial, Double limite, Integer numConta, Agencia agencia){
        try{
            validaCliente(cliente);
            validaTipo(tipo);
            validaSaldo(saldo_inicial);
            validaLimite(limite);
            validaNumConta(numConta);
            setAgencia(agencia);
            qtdContas++;
        }catch (Exception e) {
            System.out.println("Dados invalidos!");
        }
    }


    public void validaLimite(Double limite){
        if (!(limite >= 100 && limite <= 1000))
            throw new IllegalArgumentException("Valor para Limite invalido, favor colocar um numero positivo");
        setLimite(0.0);
    }

    public Double calculaTaxas() {
        
        Double taxa = 0.0;

        return taxa;
    }
}
