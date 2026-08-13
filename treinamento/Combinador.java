import java.util.Scanner;

class Combinador{
    public static void main(String[] args){
    Scanner sc = new Scanner(System.in);

    //Lendo strings para aceitar qualquer tamanho
    String uma = sc.nextLine();
    String duas = sc.nextLine();
    //Convertendo para array de char
    char[] primeira = uma.toCharArray();
    char[] segunda = duas.toCharArray();
    
    int tamComb, p=0, s=0;
    int tam1 = uma.length();
    int tam2 = duas.length();
    int t1= tam1, t2=tam2;
    tamComb = tam1+tam2;
    char[] combinacao = new char[tamComb];
    
    //Combinando as strings
    for(int j = 0; j < tamComb; j++){  // posicoes de combinacao
        //se for par, entra primeira string, impar a segunda 
        if(j % 2 == 0){
            if(t1 > 0) {  // Se ainda ha letras na primeira string
            combinacao[j] = primeira[p];
            p++;
            t1--;
            } else if(t1 == 0 && tam1 < tam2) { //Acabou a string 1 agr so resta a 2
            combinacao[j] = segunda[s];
            t2--;
            s++;
            }
        }
        else if(j % 2 != 0){   // posicoes IMPAR de combinacao
           if(t2 > 0) {  // Se ainda ha letras na segunda string
            combinacao[j] = segunda[s];
            t2--;
            s++;
           } else if (t2 == 0 && tam1 > tam2){ // SO entra se a segunda < primeira
            combinacao[j] = primeira[p];
            t1--;
            p++;
           }
        }
    } 
    for (int k = 0; k < tamComb; k++){
        System.out.print(combinacao[k]);
    }
     sc.close(); }

}