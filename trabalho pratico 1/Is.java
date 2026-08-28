import java.util.Scanner;
class Is{
	public static boolean Vogal(String frase){	//e so composto por vogais?
	int tam = frase.length();
	boolean soVogais = true;
	for(int j = 0; j < tam; j++){
	if(frase.charAt(j) != 'a'&& frase.charAt(j) != 'e'&&frase.charAt(j) != 'i'&& frase.charAt(j) != 'o'&&frase.charAt(j) != 'u') soVogais = false;
	}
	return soVogais;
	}
	public static boolean Consoantes(String frase){	//composto soemnte por consoantes?
	int tam = frase.length();
	int soLetras = 0;
	boolean soConsoantes= true;
	for(int j = 0; j < tam; j++){
	if((frase.charAt(j) == 'a'|| frase.charAt(j) == 'e'||frase.charAt(j) == 'i'||frase.charAt(j)== 'o'||frase.charAt(j) == 'u'))soConsoantes = false;
	 if ((frase.charAt(j)>= 'a' && frase.charAt(j)<= 'z')||(frase.charAt(j) >= 'A' && frase.charAt(j) <='Z')) soLetras++;
	}
	if(soLetras != tam) soConsoantes = false;
	return soConsoantes;
	}

	public static boolean Inteiros(String frase){	//composto somente por numeros inteiros: se nao tiver . ou , e é composto apenas por Numeros         entre 0 e 9
	boolean soInteiros = true;
	int tam = frase.length();
	int achouNum = 0;
	for(int i = 0; i < tam; i++){
	  if(i == 0){
		 if(frase.charAt(0) == '-' || frase.charAt(0) == '+') achouNum++; // se o numero vier acompanhado do sinal, tbm pode ser numero
	}
	  if(frase.charAt(i) >= '0' && frase.charAt(i) <= '9') achouNum++; //se o numero de inteiros encontrados nao bater com o tam, nao é inteiro
	  if(frase.charAt(i) == '.' || frase.charAt(i) == ',' ) soInteiros = false;
	}
	if(achouNum != tam) soInteiros = false;  //caso teha letras ou outros caracteres, isso acontece
	return soInteiros;
	}

	public static boolean Reais(String frase){ // um numero real é qndo tem apenas um . ou uma ,
	int tam = frase.length();	
	boolean reais = true;
	int achouNum = 0, pontoOuVirg = 0;
	for(int i = 0; i < tam; i++){
	  if(i == 0){
	  if(frase.charAt(0) == '-' || frase.charAt(0) == '+') achouNum++; 
	  }
	  if(frase.charAt(i) >= '0' && frase.charAt(i) <= '9') achouNum++; 
	  if(frase.charAt(i) == '.' || frase.charAt(i) == ',') {
	  pontoOuVirg++;
	  achouNum++; }
	}
	if(pontoOuVirg > 1 || achouNum != tam ) reais = false;
	return reais;
	}


	public static void main(String[] args){
	Scanner sc = new Scanner(System.in);
	String frase = sc.nextLine();
	boolean vog, cons, inte, real;

	while(frase.charAt(0) != 'F' || frase.charAt(1) != 'I' || frase.charAt(2) !='M'){
	vog = Vogal(frase);
	cons = Consoantes(frase);
	inte = Inteiros(frase);
	real = Reais(frase);

	if(vog == true) System.out.print("SIM "); 
	else System.out.print("NAO "); 
	if(cons == true) System.out.print("SIM "); 
	else System.out.print("NAO "); 
	if(inte == true) System.out.print("SIM "); 
	else System.out.print("NAO "); 
	if(real == true) System.out.print("SIM "); 
	else System.out.print("NAO "); 
	System.out.println(); 

	frase = sc.nextLine();
	}
	sc.close();}
}
