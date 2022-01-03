
public class Fluxo {

	public static void main(String[] args) throws Throwable{
		System.out.println("Ini do main");
		try {
		metodo1();
		} catch(Throwable ex){
			System.out.println("Excessao try catch");
			System.out.println("A execao foi "+ex.getMessage());
			                
			
		}
		System.out.println("Fim do main");

	}

	private static void metodo1()throws Exception{
		System.out.println("Ini do metodo1");
		metodo2();
		System.out.println("Fim do metodo1");
	}

	private static void metodo2() throws Exception{
		System.out.println("Ini do metodo2");
		throw new Exception("Deu muito errado Jefferson");
//		for (int i = 1; i <= 5; i++) {
//			System.out.println(i);
//		}
//		System.out.println("Fim do metodo2");
	}
}