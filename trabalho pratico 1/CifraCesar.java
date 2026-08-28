import java.util.Scanner;
class CifraCesar{
	public static char[] Soma3(String frase){
	int tam = frase.length();
	char[] newFrase = new char[tam];    ///novo vetor para armazenar a soma de cada caractere
	for(int i = 0; i < tam; i++){
	newFrase[i]=  (char)(frase.charAt(i)+3);  ///adiciona mais 3 em cada char da atual string
	}
	return newFrase;
	}

	public static void main(String[] args){
	Scanner sc = new Scanner(System.in);
	String frase = sc.nextLine();

	while(frase.charAt(0)!= 'F' || frase.charAt(1)!= 'I' || frase.charAt(2) != 'M'){
	System.out.println(Soma3(frase));
	frase = sc.nextLine();
	
}
sc.close();
  }
}
