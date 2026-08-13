	import java.util.Scanner;
	class SeqEsp{
	 public static void main(String args[]){
	 Scanner sc = new Scanner(System.in);
	 
	int cmc, fim;
	
 while (sc.hasNextInt()){
	cmc = sc.nextInt();
	fim = sc.nextInt();
	for(int i = cmc; i <= fim; i++){
	System.out.print(i);
	 }
	 //Para printar numero invertido
	for (int j = fim; j >= cmc; j--) {
    int temp = j;
    do {
        System.out.print(temp % 10);
        temp /= 10;
    } while (temp != 0);
    
} 
	System.out.print("\n");
    } 
	
 sc.close();   }
      
      } 
	