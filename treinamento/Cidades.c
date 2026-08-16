/*Sua tarefa é desenvolver um programa que leia os nomes de várias cidades brasileiras e informe a quantidade de caracteres presente no nome de cada uma delas.
Autora: Paula Moschioni
Data: 05/08/2026 */

#include <stdio.h>
int main(){
//Lendo varias strings
char string[100][100]; // quero qStrings de ate 100 chars
int qStrings = 0; // contado de strings

scanf(" %[^\n]",string[qStrings]);
while(string[qStrings][0] != 'F' || string[qStrings][1] != 'I' || string[qStrings][2] != 'M'){ //ler ate achar a string FIM
qStrings++; 
scanf(" %[^\n]",string[qStrings]);
} // o contador aqui nao conta a palavra FIM

//Contando cada caractere na string
int cont = 0, j = 0;
for(int i = 0; i < qStrings; i++){
while(string[i][j] != '\0'){ // Laco interno para contar cada letra da str i
    cont++;
    j++;
}
printf("%d\n",cont);
cont = 0;
j = 0;
}

return 0;
}


