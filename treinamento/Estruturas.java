/*Dada uma sequencia de operações que retornam valores, você vai adivinhar a estrutura de dados. É uma pilha (último-dentro, primeiro-fora), uma fila (primeiro-dentro, primeiro-fora), uma fila de prioridade (sempre tire os elementos grandes por primeiro) ou qualquer outra coisa que você dificilmente consegue imaginar!*/
import java.util.Scanner;
class Estruturas{
    static boolean isStack(int entrada[], int saida[], int num){
        int igual = 0;
        for(int i = 0; i < num; i++){
            if (entrada[num - i - 1] == saida[i])   igual++;
        }

        if (igual == num) return true;
        else return false;
    }
    
 static boolean isQueue(int entrada[], int saida[], int num){
        int igual = 0;
        for(int i = 0; i < num; i++){
            if (entrada[i] == saida[i])   igual++;
        }

        if (igual == num) return true;
        else return false;
    }
    
    static boolean  isPQueue(int entrada[], int saida[], int num){
        int igual = 0;

        //ordenar de forma crescente
        for(int i = 0; i < num - 1; i++){
            int menor = i;
            for(int j = i + 1; j < num; j++){
                if(entrada[j] < entrada[menor]){
                    menor = j;
                }
            }
            int temp = entrada[menor];
            entrada[menor] = entrada[i];
            entrada[i] = temp;
        }

        //comparar
          for(int p = 0; p < num; p++){
            if (entrada[p] == saida[num - p - 1])   igual++;
        }

        if (igual == num) return true;
        else return false;
    }


    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);

        int comando, numero;
        
        while(sc.hasNextInt()){
            int operacoes = sc.nextInt();
            int[] saida = new int[operacoes/2];
            int[] entrada = new int[operacoes/2];
            int s = 0, e = 0;
        for(int i = 0; i < operacoes; i++){
            comando = sc.nextInt();     //1- inserir 2-remover
            numero = sc.nextInt();      // qual numero
            if(comando == 1) {
            entrada[e] = numero;
            e++;
            }
            else if (comando == 2) {
            saida[s] = numero;
            s++;
            }
        }

        boolean stack = false; //pilha
        boolean queue = false; //fila
        boolean pQueue = false; // fila de prioridade
        boolean ns = false;
        int maisDum = 0;
        stack = isStack(entrada, saida, operacoes/2);
        queue = isQueue(entrada, saida, operacoes/2);
        pQueue = isPQueue(entrada, saida, operacoes/2);

        //comparando resultados
        if(stack) maisDum++;
        if(queue) maisDum++;
        if(pQueue) maisDum++;

        if(maisDum == 0) System.out.println("impossible");
        else if(maisDum > 1) System.out.println("not sure");
        else {
        if(stack) System.out.println("stack");
        if(queue) System.out.println("queue");
        if(pQueue) System.out.println("priority queue");
        }

        
    }
    sc.close(); } 
}