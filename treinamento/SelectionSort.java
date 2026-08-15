import java.util.Scanner;
import java.util.ArrayList;

class SelectionSort{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> vetor = new ArrayList<>();   // "vetor" que cresce sozinho
      int n = 0, menor;
    // Lendo vetor de acordo com hasNext
    while(sc.hasNextInt()){
        int num = sc.nextInt();
        vetor.add(num); 
        n++;
    }

    //Ordenando por selecao
    for (int i = 0; i < (n - 1); i++){  /*esse for é destinado a rodar todos os elementos do vetor e troca-los */
        menor = vetor.get(i);   // o elemento na posicao i esta supostamente no local correto para ele
        for (int j = (i + 1); j < n; j++){
            if(vetor.get(j) < vetor.get(menor)){ // compara os elementos do vetor com o "suposto" menor  
               menor = vetor.get(j);
            }
        }
        swap(vetor, menor, i); 
    }

    sc.close();}

    public static void swap(ArrayList<Integer> vetor, int menor, int ind) {
        int temp = vetor.get(menor);
        vetor.set(menor, vetor.get(ind));
        vetor.set(ind, temp);
    }
}


