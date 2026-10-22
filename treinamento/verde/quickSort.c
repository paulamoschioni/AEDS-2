#include <stdio.h>
#include <string.h>
typedef struct Aluno{
    int nota;
    int faltas;
    char nome[51];
}Aluno;
void swap(int i, int j, Aluno aluno[]){
    Aluno temp = aluno[i];
    aluno[i] = aluno[j];
    aluno[j] = temp;
}

//retorna < 0 se a vem antes de b, > 0 se vem depois, 0 se iguais
//criterio: maior nota, depois menos faltas, depois nome em ordem alfabetica
int compara(Aluno a, Aluno b){
    if(a.nota != b.nota) return b.nota - a.nota;
    if(a.faltas != b.faltas) return a.faltas - b.faltas;
    return strcmp(a.nome, b.nome);
}

void quickSort(Aluno aluno[], int esq, int dir, int k){
    int i = esq;
    int j = dir;
    Aluno pivo = aluno[(esq+dir)/2];

    while(i <= j){
        while(compara(aluno[i], pivo) < 0) i++;
        while(compara(aluno[j], pivo) > 0) j--;

        if(i <= j){
            swap(i, j, aluno);
            i++;
            j--;
        }
    }
    //chamadas recs
    if(j > esq){
        quickSort(aluno, esq, j, k);
    }
    if(i < k && i < dir){
        quickSort(aluno, i, dir, k);
    }
}

int main(){
     int k, n;

     //ler ate acabar
     while( (scanf("%d %d",&n, &k)) == 2){
        Aluno aluno[n];

        for(int i = 0; i < n; i++){
            scanf("%d",&aluno[i].nota);
            scanf("%d",&aluno[i].faltas);
            scanf(" %[^\n]",aluno[i].nome);
        }

        if(k > n) k = n;
        if(n > 0) quickSort(aluno, 0, n-1, k);

        for(int i = 0; i < k; i++){
            printf("%d %d %s\n", aluno[i].nota, aluno[i].faltas, aluno[i].nome);
        }
     }

    return 0;
}
