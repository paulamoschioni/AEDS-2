import java.util.Scanner;
class BuscaBinaria{
    public static void main(String[] args){
      java.util.Scanner sc = new Scanner(System.in);
        // Lendo entradas
        System.out.println("Digite o tamanho do vetor: ");
        int tam = sc.nextInt();
        System.out.println("Digite o elemento chave: ");
        int chave = sc.nextInt();

        //Preenchendo vetor
         System.out.println("Digite o vetor de forma ORDENADA: ");
        int[] vetor = new int[tam];
        for(int i = 0; i < tam; i++){
        int num = sc.nextInt();
        vetor[i] = num;
        }
        
        // Buscando o elemento
        boolean achou = false;
        int cmc = 0, fim = (tam - 1), meio, pos;

        while(fim >= cmc){
            meio = (cmc + fim)/2;
            if (vetor[meio] == chave){
                pos = meio;
                System.out.println("Achou na posicao: "+ pos);
                achou = true;
                break;
            }  else if (vetor[meio] > chave) { // Busca a esquerda
                fim = meio;
            }  else if (vetor[meio] < chave) { // Busca a direita
                cmc = meio;
            }
      } 
      if (achou ==  false){
        System.out.println("O elemento nao esta presente,");
      }
sc.close();
   }
}
    