#include <stdio.h>
char* Ciframento(char frase[], int tam){
	if(tam < 0) return frase;	//caso base quando tem tamanho menor que 0
	frase[tam] += 3;
	return Ciframento(frase ,tam - 1);
	
}
char* Inicializa(char frase[]){
	int tam = 0;
	while(frase[tam] != '\0'){
 	   tam++;
 	}
	return Ciframento(frase,tam-1);
}


int main(){
	//Recebedo String
	char frase[400];
	scanf(" %[^\n]",frase);
	
	while(frase[0] != 'F' || frase[1] != 'I' || frase[2] != 'M'){
	printf("%s\n",Inicializa(frase));
	scanf(" %[^\n]",frase);

	}

return 0;
}
