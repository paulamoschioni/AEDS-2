#include <stdio.h>
#include <string.h>
int main(){
//Lendo strings ate FIM
char string[700][100]; //matriz de strings
int qStrings = 0;

scanf(" %[^\n]",string[qStrings]);
while(string[qStrings][0] != 'F' || string[qStrings][1] != 'I' || string[qStrings][2] != 'M'){
qStrings++;
scanf(" %[^\n]",string[qStrings]);
}


//Definindo se sao parametros
int cont, tam, iguais;
char cmc, fim;
for(int i = 0; i < qStrings; i++){ // pega todas as strings
   cont = 0;
   iguais = 0;
   tam = (strlen(string[i]) - 1);
   cmc = string[i][0];
   fim = string[i][tam-1];

     for(int j = 0; j <= tam; j++){ // compara cada letra da string da linha
     if(string[i][j] == string[i][tam - j]) iguais++;
     }

   if(iguais == (tam+1)) printf("SIM\n");
   else printf("NAO\n");
}
return 0;
}