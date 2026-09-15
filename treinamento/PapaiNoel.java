import java.util.Scanner;
class PapaiNoel{
    public static void ordenaNomes(String nome[], int num){
        for(int i = 1; i < num; i++){
            String chave = nome[i];
            int j = i - 1;
            while(j >= 0 && (nome[j].compareTo(chave)) > 0){
                nome[j + 1] = nome[j];
                j--;
            }
         nome[j+1] = chave;
        }

        for(int p = 0; p < num; p++){
            System.out.println(nome[p]);
        }
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
    int qMais = 0, qMenos = 0;
    int num;
    num = sc.nextInt();
    char sinal;
    String[] nome = new String[num];

    for(int i = 0; i < num; i++){
        sinal = sc.next().charAt(0);
        nome[i] = sc.nextLine();
        
        if (sinal == '+'){
            qMais++;
        }
        else if (sinal == '-'){
            qMenos++;
        }
    }
    ordenaNomes(nome,num);
    System.out.println("Se comportam: "+qMais+" | Nao se comportam: " + qMenos );
    sc.close();
    }
}