import java.util.Random;
import java.util.Scanner;
class AlteraAleat{
	private static Random gerador = new Random();
	public static char[] Altera(String frase, int posicao){
			
	char random1 =((char)('a'+(Math.abs(gerador.nextInt()) % 26)));
	char random2 =((char)('a'+(Math.abs(gerador.nextInt())% 26)));
	int tam = frase.length();
	char[] newArray = new char[tam];
	//transformar em array de char
	for(int j = 0; j < tam; j++){
	newArray[j] = frase.charAt(j);
	}

	for(int i = 0; i < tam; i++){		//percorre ate o final da frase
		if(newArray[i] == random1) newArray[i]= random2;
	 } 
	return newArray;
	}

	public static void main(String[] args){
	Scanner sc = new Scanner(System.in);
	gerador.setSeed(4);	
	//Lendo entradas
	
	String frase = sc.nextLine();
	while(frase.charAt(0) != 'F' || frase.charAt(1) != 'I' || frase.charAt(2) != 'M'){
	int tam = frase.length();
	char[] newArray = Altera(frase,0);
	for(int i = 0; i < tam; i++){
	System.out.print(newArray[i]);
	}
	System.out.println();
	frase = sc.nextLine();
	}
	sc.close();}
} 
