#include <stdio.h>
#include <stdlib.h> 
#include <string.h>
 void swap(char *menor, char *j){
    char temp[20];
    strcpy(temp, menor);
    strcpy(menor,j);
    strcpy(j, temp);
    
}
void ordenaNomes(char nome[][20], int num){
    for(int i = 0; i < num - 1; i++){
        int menor = i;
        for(int j = i + 1; j < num; j++){
            if(strcmp(nome[j],nome[menor]) < 0 ){
                menor = j;
            }
        }
        swap(nome[menor], nome[i]);

    }

    for(int j = 0; j < num; j++){
            printf("%s\n",nome[j]);
        }
    
}

int main(){
    int qMais = 0, qMenos = 0;
    int num;
    char sinal;
    scanf("%d",&num);
    char nomeTemp[num][20];
    for(int i = 0; i < num; i++){
        scanf(" %c", &sinal);
        scanf(" %[^\n]",nomeTemp[i]);
        
        if (sinal == '+'){
            qMais++;
        }
        else if (sinal == '-'){
            qMenos++;
        }
    }
    ordenaNomes(nomeTemp, num);
printf("\nSe comportaram: %d|Nao se comportaram: %d", qMais, qMenos);
    return 0;
}