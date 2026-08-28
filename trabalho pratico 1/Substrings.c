#include <stdio.h>
#include <stdlib.h>
/*Autora: Paula Moschioni
 Crie um metodo que retorna o tamanho da maior substring sem caracteres repetidos*/
int SubstringLonga(char frase[], int tam){
	int maior = 0, inicio = 0, tamanhoAtual;
	char caractereAtual;
	//laco que percorre caractere por caractere
	for(int fim = 0; fim < tam; fim++){
		int posRepetida = -1;    //-1 seria equivalente a FALSE
	    
		for(int p = inicio; p < fim; p++){
			if(frase[p] == frase[fim]) {
			posRepetida = p;      //guarda onde achou repeticao
			}
		}
	//se achou repeticao, inicio pula 1 posicao
		if(posRepetida != -1){
		inicio = posRepetida+1;
		}
		
		int tamanhoAtual = fim - inicio + 1;
		if(tamanhoAtual > maior) {	// o tamanho da substring atual e maior doq alguma anteriormente encontrada?
			maior = tamanho Atual
		}
	}
	return maior;
}

int main(){
	//Recebendo varias strings
	char frase[100];
	scanf("%s",frase);

	while(frase[0] != 'F' || frase[1] != 'I' || frase[2] != 'M'){
	int tam = 0;
 	 while(frase[tam] != '\0') {
	 tam++;
	 }

	printf("%d",SubstringLonga(frase, tam));
	scanf("%s",frase);
	}
return 0;
}
