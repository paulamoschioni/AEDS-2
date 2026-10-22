#include <stdio.h>
/* PAULA MOSCHIONI
21/10/2026
*/
void swap(int i, int chave, int v[]){
    int temp = v[i];
    v[i] = v[chave];
    v[chave] = temp;
}

void insercaoParcial(int k, int n, int v[]){    
    for(int i = 1; i < n; i++){     
        int chave = v[i];
        int j;

        if(i < k){
            j = i - 1;
        }
        else{
            j = k + 1;
        }
    
        while(j >= 0 && v[j] > chave){
            
        }
    }

}