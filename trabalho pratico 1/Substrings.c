#include <stdio.h>
/*Autora: Paula Moschioni
 Crie um metodo que retorna o tamanho da maior substring sem caracteres repetidos*/
int SubstringLonga(char frase[], int tam){
// Preenchendo o vetro que informa a maior qtde de numeros das n substrings
	int contString = 0, acuTemp;
	int* qcStr = malloc tam* sizeof(int);
	/// char* substring = malloc tam* sizeof(char);

	for(int i = 0 ; i < tam; i++){
	if(i == 0) qcStr[contString] = 1;   ///indicando o primeiro char como uma substring
	else{
	acuTemp = 0;
	    for(int j = (i-1); j >= 0; j--){
	        if(frase[i] != frase[j]) acuTemp++;
	    }
	if(acuTemp == (i+1)) qcStr[contString]++;
	else{
	contString++;
	qcStr[contString] = 1;
	}

	} 
     }
// Comparando vetor para ver a maior sequencia
 int maior = qcStr[0];
	for(int p = 0; p <= contString; p++) {
	if(maior < qcStr[p]) maior = qcStr[p]; 	
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
