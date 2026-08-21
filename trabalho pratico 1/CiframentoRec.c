#include <stdio.h>
void Ciframento(char frase[], int tam){
	for(int i = 0 ; i < tam; i++){
	printf("%c",(frase[i]+ 3));
	}
	printf("\n");
}
int main(){
	//Recebedo Strings
	int tam;
	char frase[100];
	scanf("%s",frase);
	
	while(frase[0] != 'F' || frase[1] != 'I' || frase[2] != 'M'){
	tam = 0;
	while(frase[tam] != '\0'){
 	   tam++;
 	}
	Ciframento(frase,tam);
	scanf("%s",frase);
	}

return 0;
}
