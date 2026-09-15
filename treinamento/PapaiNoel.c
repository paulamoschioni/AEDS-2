#include <stdio.h>
#include <stdlib.h> 
#include <string.h>
 void swap(char *menor, char *i){
    char temp[21];
    strcpy(temp, menor);
    strcpy(menor,i);
    strcpy(i, temp);
    
}
void ordenaNomes(char nome[][21], int num){
    for(int i = 0; i < num - 1; i++){
        int menor = i;
        for(int j = i + 1; j < num; ++j){
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
    char nomeTemp[num][21];
    for(int i = 0; i < num; i++){
        scanf(" %c", &sinal);
        scanf(" %20s",nomeTemp[i]);
        
        if (sinal == '+'){
            qMais++;
        }
        else if (sinal == '-'){
            qMenos++;
        }
    }
    ordenaNomes(nomeTemp, num);
printf("Se comportaram: %d | Nao se comportaram: %d", qMais, qMenos);
    return 0;
}