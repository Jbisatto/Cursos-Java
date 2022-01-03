public class Administrador extends Funcionario implements Autenticavel {

    private int senha;

    public double getBonificacao(){
        // implementação da bonificação do administrador omitida
		System.out.println("Chamando bonificação Administrador");
		return super.getSalario();
    }

    @Override
    public void setSenha(int senha){
        this.senha = senha;
    }

    @Override
    public boolean autentica(int senha){

        if(this.senha == senha){
            return true;
        } else {
            return false;
        }
    }

}