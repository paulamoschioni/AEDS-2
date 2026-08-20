#include <stdio.h>
#include <stdlib.h>
void InverteString(char* frase, int tam){
	for(int i = (tam - 1); i >= 0; i--){
	printf("%c",frase[i]);
	}
}

int main(){
	char frase[100][300]; // vetor de 100 frases
	int qStr = 0;
	
	// Recebendo strings ate a palavra FIM
	scanf(" %[^\n]",frase[0]);

	while(frase[qStr][0] != 'F' || frase[qStr][1] != 'I' || frase[qStr][2] != 'M'){
	qStr++;
	scanf(" %[^\n]",frase[qStr]);
	}
	
	// Aciona o metodo inverte
	for(int i = 0; i < qStr; i++){
	int tam = 0;
	while(frase[i][tam] != '\0'){
	tam++;
	}

	InverteString(frase[i],tam);
	printf("\n");
	}
	
return 0;
}
