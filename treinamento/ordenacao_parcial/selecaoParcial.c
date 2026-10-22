#include <stdio.h>
/* PAULA MOSCHIONI
21/10/2026
*/
void swap(int i, int chave, int v[]){
    int temp = v[i];
    v[i] = v[chave];
    v[chave] = temp;
}

void selecaoParcial(int k, int n, int v[]){    
    for(int i = 0; i < k; i++){      //cada volta escolhe o SUPOSTO MENOR. e escolhe apenas k deles
        int chave = i;
        
        for(int j = i + 1; j < n; j++){         //CAMINHA pelo vetor e acha o REAL menor da posicao i
                if(v[j] < v[chave]){
                    chave = j;
                }
        }
        swap(i, chave, v);
    }
}
