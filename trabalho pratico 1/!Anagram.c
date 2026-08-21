#include <stdio.h>
#include <stdlib.h>
void Anagrama(char frase1[], char frase2[]){
	//Cotando tam das strings
	int cont1 = 0, cont2 = 0, igual;
	while(frase1[cont1] != '\0'){
	cont1++;
	}
	while(frase2[cont2] != '\0'){
	cont2++;
	}
	
	//Sao anagramas?
	if(cont1 != cont2) printf("NAO");
	else{
	 for(int i = 0; i < cont1; i++){
 	 if(frase1[i] == frase2[(cont2-1)-i]) igual++;
	 else if((32 + frase1[i]) == frase2[cont2-1-i]) igual++;
	 else if(frase1[i] == (frase2[cont2-1-i] + 32)) igual++;
	 }
	 if(igual == cont1) printf("SIM");
	 else printf("NAO");
	}

}
int main(){
	//Lendo duas strings
	char frase1[100], frase2[100]; /// Ate 100 strings
	
	scanf("%s %s",frase1,frase2);
	while(frase1[0] != 'F' || frase1[1] != 'I' || frase1[2] != 'M'){
	Anagrama(frase1,frase2);
	scanf("%s %s",frase1,frase2);
	}


return 0;
}
