import java.util.Scanner;
class InversaoJavaRec{
    public static String Inicializa(String frase) {
        int tam = frase.length();
        String invertida = InverteRec(frase, tam - 1);
	return invertida;
    }

	 public static String InverteRec(String frase, int pos) {
        // para quando chegar no primeiro caractere
        if (pos == 0) {
            return "" + frase.charAt(0);
        } else {
            // pega o caractere da posicao atual e agrupa antes do resto invertido
            return frase.charAt(pos) + InverteRec(frase, (pos - 1));
        }
    }

	public static void main(String args[]){
	Scanner sc = new Scanner(System.in);
	String frase = sc.nextLine();
	
	while(frase.length() != 3||frase.charAt(0) != 'F' || frase.charAt(1) != 'I' || frase.charAt(2) !='M'){
		System.out.println(Inicializa(frase));
		frase = sc.nextLine();
	}
	sc.close(); }
}
