import java.util.ArrayList;
import java.util.Map;

public class AFD {
	int[][] d; 
	ArrayList<String> abecedario ;
	int inicial;
	int estados;
	int [] finales;
	public AFD(int estados, int inicial) {
		abecedario = new ArrayList<String>();
		this.estados=estados;
		this.inicial=inicial;
	}
	   public AFD(int[][] d, ArrayList<String> abecedario, int inicial, int estados, int[] finales) {
	        this.d = d;
	        this.abecedario = abecedario;
	        this.inicial = inicial;
	        this.estados = estados;
	        this.finales = finales;
	    }

	public boolean checkWord(String w){
		//int x= this.inicial;
		int next = this.inicial;
		String letter = "";
		for(int i=0;i<w.length();i++){//		i is to check letters
			letter=""+ w.charAt(i);
			next= this.d[next][this.abecedario.indexOf(letter)];
		}
		for(int i=0;i<finales.length;i++){
			if(next==finales[i]){
				return true;
			}
		}
		return false;
	}
	/*
	public static void main(String[] args) {
		//states = 3
		AFD m = new AFD(3,0);
		m.abecedario.add("a");
		m.abecedario.add("b");
		m.d = new int[m.estados][m.abecedario.size()];//initialize the array
		m.finales = new int[2];
		m.finales[0]=0;
		m.finales[1]=2;
		
		//you start adding the sequences
		//define the AFD
		//example
		m.d[0][m.abecedario.indexOf("a")]= 1;
		m.d[0][m.abecedario.indexOf("b")]= 0;
		m.d[1][m.abecedario.indexOf("a")]= 2;
		m.d[1][m.abecedario.indexOf("b")]= 0;
		m.d[2][m.abecedario.indexOf("a")]= 2;
		m.d[2][m.abecedario.indexOf("b")]= 2;
		//PROVE IT
		System.out.println("d(q0,a)= q"+m.d[0][m.abecedario.indexOf("a")]);
		String w="aabababa";
		System.out.println("Word: "+w);
		System.out.println(m.checkWord(w));
		
		
	}
*/
}
