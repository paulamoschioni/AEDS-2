#include <stdio.h> 
/*Autora: Paula Moschioni 
Data: 17/07
Objetivo é desenvolver o códiog bubble sort*/
int main(){
//Recebendo vetor de n elementos
int n, num;
scanf("%d",&n);
int array[n], bubble;
for(int p = 0; p < n; p++){
    scanf("%d",&array[p]);
}
//Ordenando
for(int i = 0; i < (n - 1) ; i++){  // numero de passadas que é n-2, pq a primeira posicao no final esta ordenada
    for(int j = (n-1); j > i; j--){  // j vai ate i, pq a cada passada externa, o menor elemento vai para a posicao certa
        if(array[j] < array[j - 1]){  //compara as posicoes da bubble e do anterior
            int temp = array[j-1];  
            array[j-1] = array[j];
            array[j] = temp;
        }
    }
}

for(int k = 0; k < n; k++){
printf("%d ",array[k]);
}
    return 0;
}