
public class TestaConexao {
	public static void main(String[] args) {
	
		try (Conexao con = new Conexao()){
			con.leDados();
		}catch(IllegalStateException ex){
			System.out.println("Deu erro na conexao");
			
		}
		
		
		
		
		
//		Conexao conexao= null;
//		try {
//			conexao = new Conexao();
//			conexao.leDados();
//		}catch(IllegalStateException ex) {
//			System.out.println("Deu erro na conexao");
//		}finally {
//			conexao.close();
//		}
	}
}
