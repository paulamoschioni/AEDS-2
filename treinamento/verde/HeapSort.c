#include <stdio.h>
#include <string.h>
typedef struct filmes{
    char titulo[101];
    int ano;
    int premios;
}Filmes;

//retorna < 0 se a vem antes de b no ranking, > 0 se vem depois
int compara(Filmes a, Filmes b){
    if(a.premios != b.premios) return b.premios - a.premios; //mais premios primeiro
    if(a.ano != b.ano) return a.ano - b.ano;                 //mais antigo primeiro
    return strcmp(a.titulo, b.titulo);                       //ordem alfabetica
}

void swap(Filmes filme[], int i, int j){
    Filmes tmp = filme[i];
    filme[i] = filme[j];
    filme[j] = tmp;
}

//sobe o elemento da posicao tam ate o lugar certo (heap de maximo, indice 1)
void construir(Filmes filme[], int tam){
    for(int i = tam; i > 1 && compara(filme[i], filme[i/2]) > 0; i /= 2){
        swap(filme, i, i/2);
    }
}

int getMaiorFilho(Filmes filme[], int i, int tam){
    int filho;
    if(2*i == tam || compara(filme[2*i], filme[2*i+1]) > 0){
        filho = 2*i;
    } else {
        filho = 2*i + 1;
    }
    return filho;
}

//desce a raiz ate o lugar certo, considerando o heap de 1 ate tam
void reconstruir(Filmes filme[], int tam){
    int i = 1;
    while(i <= tam/2){
        int filho = getMaiorFilho(filme, i, tam);
        if(compara(filme[i], filme[filho]) < 0){
            swap(filme, i, filho);
            i = filho;
        } else {
            i = tam;
        }
    }
}

void heapSort(Filmes filme[], int k, int n){
    //construir arvore
    for(int tam = 2; tam <= k; tam++){
        construir(filme, tam);
    }

    //trocar o primeiro pelo ultimo e ....
    for(int i = k + 1; i <= n; i++){
        if(compara(filme[i], filme[1]) < 0){
            swap(filme, i, 1);
            reconstruir(filme, k);
        }
    }

    //ordenacao
    int tam = k;
    while(tam > 1){
        swap(filme, 1, tam--);
        reconstruir(filme, tam);
    }
}

int main(){
    int n, k;

    while(scanf("%d%d",&n, &k) == 2){
        Filmes filme[n + 1]; //posicao 0 nao e usada

        for(int i = 1; i <= n; i++){
            scanf("%d",&filme[i].premios);
            //printf("%d ",filme[i].premios);

            scanf("%d",&filme[i].ano);
            //printf("%d ",filme[i].ano);

            scanf(" %[^\n]",filme[i].titulo);
            //printf("%s ",filme[i].titulo);
        }

        heapSort(filme, k, n);

        for(int i = 1; i <= k; i++){
            printf("%d %d %s\n", filme[i].premios, filme[i].ano, filme[i].titulo);
        }
    }

    return 0;
}