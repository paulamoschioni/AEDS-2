import java.util.Random;
import java.util.Scanner;
class AlteraAleat{
	private static Random gerador = new Random(); //classe para random
	public static char[] Altera(String frase, int posicao){
			
	char random1 =((char)('a'+(Math.abs(gerador.nextInt()) % 26)));  //gerando 2 aleatorios que serao dividos pelo numero de letras do alfabeto
	char random2 =((char)('a'+(Math.abs(gerador.nextInt())% 26)));
	int tam = frase.length();
	char[] newArray = new char[tam];
	//preenchendo com string original
	for(int j = 0; j < tam; j++){//percorre ate o final da frase
       	  newArray[j] = frase.charAt(j);
	   if(newArray[j]== random1) { //se for igual, copia o random
		newArray[j]= random2;
 		} 
	}
	return newArray;
	}

	public static void main(String[] args){
	Scanner sc = new Scanner(System.in);
	gerador.setSeed(4);	
	//Lendo entradas
	
	String frase = sc.nextLine();
	while(frase.length() != 3||frase.charAt(0) != 'F' || frase.charAt(1) != 'I' || frase.charAt(2) != 'M'){//como na entrada, possui um teste tem 													   branco, se o tamanho for diferente de 3, entra.
	int tam = frase.length();
	char[] resultado= Altera(frase,0);
	for(int i = 0; i < tam; i++){
	System.out.print(resultado[i]);
	}
	System.out.println();
	frase = sc.nextLine();
	}
	sc.close();}
} 
