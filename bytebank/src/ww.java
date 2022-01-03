
public class ww {

	public static void main(String[] args) {
		int[] cedulas= new int[9];
		int i,quantidadeNota, valor;
		cedulas [1] = 200;
		cedulas [2] = 100;
		cedulas [3] = 50;
		cedulas [4] = 20;
		cedulas [5] = 10;
		cedulas [6] = 5;
		cedulas [7] = 2;
		cedulas [8] = 1;
		
		valor=537;
		for(i=1;i<=8;i++) {
			quantidadeNota=(int)valor/cedulas[i];
			valor=quantidadeNota*cedulas[i];
			System.out.print("R$ "+cedulas[i]+" = "+quantidadeNota+"; ");
			
		}

	}

}
