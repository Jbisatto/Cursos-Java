
public class TestaFuncionario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Funcionario nico = new Funcionario();
		nico.setNome("Jefferson Bisatto");
		nico.setCpf("010.108.709.80");
		nico.setSalario(3000.00);
		
		System.out.println(nico.getNome());
		System.out.println(nico.getCpf());
		System.out.println(nico.getSalario());
		System.out.println(nico.getBonificacao());
	}

}
