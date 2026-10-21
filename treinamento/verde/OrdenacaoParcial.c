/*Ordenacao Parcial: ordenar apenas os k menores elementos de um vetor (Selecao Parcial).
Autora: Paula Moschioni
Data: 20/10/2026 */
#include <stdio.h>
#include <stdlib.h>
typedef struct Corredores{
    int tempo;
    char nome[30];
    int h;
    int m;
    int s;
} Corredores;

void InsercaoParcial(Corredores corre[], int k, int n){
    for(int i = 1; i < n; i++){
        Corredores chave = corre[i];
        int j;

        if(i < k){
            j = i - 1;
        }
        else{
            j = k - 1;
        }

        while(j >= 0 && corre[j].tempo > chave.tempo){
            corre[j+1] = corre[j];
            j--;
        }

        corre[j+1] = chave;
    }
}

int main(){
    int n, k;
    char nomeAux;
    int h, m ,s;

    //ate EOF
    while(scanf("%d%d",&n,&k) == 2){
        //cria array de n corredores
        Corredores participantes[n];

        for(int i = 0; i < n; i++){
            //le nome
            scanf("%s", participantes[i].nome);     //guarda o nome
            //le horas + min + seg
            scanf("%d %d %d",&participantes[i].h, &participantes[i].m, &participantes[i].s);
            h = participantes[i].h;
            m = participantes[i].m;
            s = participantes[i].s;
            participantes[i].tempo = h*3600 + m*60 + s;
            printf("%s %d\n",participantes[i].nome,participantes[i].tempo);
        }

        //enviar ao metodo ordenacao os k primeiros e dps imprimir os k primeiros
        InsercaoParcial(participantes, k, n);
        for(int p = 0; p < k; p++){
            printf("%s %d %d %d\n",participantes[p].nome, participantes[p].h, participantes[p].m, participantes[p].s);
        }

    }

    return 0;
}
