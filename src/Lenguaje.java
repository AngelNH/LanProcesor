import java.util.*;
import javax.swing.JOptionPane; 

public class Lenguaje {
	char[] simbolos;
	ArrayList<String> lenguaje = new ArrayList<String>();
	Deque<String> checked = new ArrayDeque<String>();
	
	public Lenguaje() {
	}
	public Lenguaje(String w){
		int i=0;
		String aux = "";
		while(!w.isEmpty()){
			i=0;
			aux="";
			while(i!=w.length() && w.charAt(i)!=','){
				aux=aux+w.charAt(i);
				i++;
			}
			this.addToL(aux);
			w=this.eraseCheckedPart(w, aux.length()+1);
		}
	}
	Boolean isOnL(String w){
		if(lenguaje.contains(w))return true;
		return false;
	}
	void addToL(String w){
		lenguaje.add(w);
	}
	int getMaxSize(){
		int max=0;
		for(int i = 0; i<lenguaje.size();i++){
			if(lenguaje.get(i).length()>max)
				max= lenguaje.get(i).length();
		}
		return max;
	}
	int getMinSize(){
		int min=this.getMaxSize();
		for(int i = 0; i<lenguaje.size();i++){
			if(lenguaje.get(i).length()<min)
				min= lenguaje.get(i).length();
		}
		return min;
		
	}
	Boolean correctSize(String w , int size){
		if(w.length()>=size)return true;
		return false;
	}
	String getWordToCheck(String wt, int size){
		String exit= "";
		if(correctSize(wt,size)){//do not exceed the size to check of the word
			for(int i= 0;i<size;i++){
				exit=exit + wt.charAt(i);
			}
		}
		return exit;
	}
	String eraseCheckedPart(String wc, int size){
		String newWord="";
		for(int i = size; i<wc.length();i++){
			newWord = newWord + wc.charAt(i);
		}
		return newWord;
	}
	int checkOrder(){
		return this.checked.size();
		/*
		int max=0;
		while(!this.checked.isEmpty()){
			if(max<this.checked.peek().length()){
				max=this.checked.pop().length();
			}else{
				this.checked.pop();
			}
		}
		return max;*/
	}
	public static void main(String []args){
		
		//Test
		String words = JOptionPane.showInputDialog("Ingrese las palabras del Lenguaje");
		Lenguaje len = new Lenguaje(words);
		int cases = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de palabras a verificar"));
		
		String w = null; 
		//while(true){
		for(int j=0;j<cases;j++){
			len.checked.clear();
			w = JOptionPane.showInputDialog("Ingrese la palabra "+(j+1)+" a verificar");
			
			/*
			len.addToL("a");
			len.addToL("aa");
			len.addToL("aba");
			
			String w = "aabaabaabaabab";
			*/
			//----------------
			boolean notInLang = true;
			int elsize=0;
			int overinf=0;
			//
			//
			while(!w.isEmpty()){
				for(int i=len.getMaxSize();i>0;i--){
					if(w.length()!=0 && overinf!=1000){
						if(len.isOnL(len.getWordToCheck(w, i))){
							//add to stack
							len.checked.push(len.getWordToCheck(w, i));
							//erase it
							w = len.eraseCheckedPart(w,i);
							//start again with max size
							i=len.getMaxSize()+1;
						}else if(i==1 && len.checked.size()!=0){
							elsize=len.checked.peek().length();
							w = len.checked.pop() + w;
							i=elsize;
							//to check if it is in an infinite loop
							overinf++;
						}
					}else if(overinf==1000){
						System.out.println("No pertenece");
						JOptionPane.showMessageDialog(null, "Su palabra numero "+(j+1)+" NO Pertenece a L* llegó hasta L"+len.checkOrder());
						notInLang=false;
						w="";
						break;
					}else{
						break;
					}
					if(w.length()<len.getMinSize() && w.length()!=0 && len.checked.isEmpty()){
						System.out.println("No pertenece");
						JOptionPane.showMessageDialog(null, "Su palabra numero "+(j+1)+" NO Pertenece a L* llegó hasta L"+len.checkOrder());
						notInLang=false;
						w="";
						break;
					}
				}
			}
			if(notInLang){
				System.out.println("Pertenece");
				JOptionPane.showMessageDialog(null, "Su palabra numero "+(j+1)+" SI Pertenece a L"+len.checkOrder()+" y Es Parte de L*");
				
			}
		}
	}
}
