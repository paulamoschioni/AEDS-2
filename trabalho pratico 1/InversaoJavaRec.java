import java.util.Scanner;
class InversaoJavaRec{
	public static void Inicializa(String frase){
	int tam = (frase.length());
	InverteRec(frase,tam-1,tam);
	}

	public static String InverteRec(String frase, int pos, int tam){
	char[] invertido = new char[tam];  
	//LOGICA: fazer o primeiro caractere virar o ultimo
	//CONDICAO DE PARADA: quando chegar no primeiro char
	if(pos == 0){
	return frase.charAt(0);
	}else{
	return InverteString(frase,pos--,tam);
	invertido[tam - pos - 1] = frase.charAt(pos);
	}
}
	public static void main(Strings args[]){
	Scanner sc = new Scanner(System.in);
	String frase = sc.nextLine();
	
	while(frase.length() != 3||frase.charAt(0) != 'F' || frase.charAt(1) != 'I' || frase.charAt(2) !='M'){
		Inicializa(frase);
		
		frase = sc.nextLine();
	}
	sc.close(); }
}
