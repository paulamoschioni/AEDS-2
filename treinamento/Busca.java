import java.util.Scanner;
class Busca{
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
    // Ler numeros
    int tamVet, num;
    tamVet = sc.nextInt();
    int[] vet = new int[tamVet];
    // Guardando os numeros no vetor
    for(int i = 0; i < tamVet; i++){
    num = sc.nextInt();
    vet[i] = num;
    }
    // Realizando a busca
    int menor = vet[0], maior = vet[0];
    
    for(int j = 0; j < tamVet; j++){
            if(vet[j]< menor) menor = vet[j];
            if(vet[j] > maior) maior = vet[j];
        

    }
    System.out.println("Menor: "+ menor + "| Maior: "+ maior);

 sc.close(); } 
}