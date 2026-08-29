import java.util.Scanner;
public class SomaRec {
    public static int Soma(int n){
	 if (n < 10) {
            return n;                    // caso base:sobrou apenas um digito
        } else {
            return (n % 10) + Soma(n / 10);  
//envia para a prox chamada um numero com uma casa decimal a menos e retorna o resto dessa divisao
        }
    }

    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int numero = sc.nextInt();
            System.out.println(Soma(numero));
        }
        sc.close();
    }
}
