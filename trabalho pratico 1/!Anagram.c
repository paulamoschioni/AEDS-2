#include <stdio.h>
int Anagrama(char frase1[], char frase2[]){
	//Cotando tam das strings
	int cont1 = 0, cont2 = 0, igual = 0;
	while(frase1[cont1] != '\0'){
	cont1++;
	}
	while(frase2[cont2] != '\0'){
	cont2++;
	}
	
	//Sao anagramas?
	for(int i = 0; i < cont1; i++){	///for externo que passa por todas as letras da string1
		for(int j = 0; j < cont2; j++){
			if((frase1[i] == frase2[j] || frase1[i]== frase2[j]+32|| frase1[i]==frase2[j]-32) && frase2[j] != '-') {
				igual++;	//(==, maius e minus)E(entrado 1 vez por caractere)E(nao entrar letras repetidas na msm frase)
				frase2[j] = '-';
				}
		}
	}

	if(igual == cont1) return 1;
	else return 0;
}
int main(){
	//Lendo duas strings1
	char frase1[100], frase2[100]; 
	int resultado;

	scanf("%s %s",frase1,frase2);
	while(frase1[0] != 'F' || frase1[1] != 'I' || frase1[2] != 'M'){
	resultado = Anagrama(frase1,frase2);
	if(resultado) printf("SIM\n");	
	else printf("NAO\n");
	scanf("%s %s",frase1,frase2);
	}


return 0;
}
