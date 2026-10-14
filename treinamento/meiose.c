#include <stdio.h>
#include <stdlib.h>
/*
Paula Moschioni Infante Vieira
13/10/2026
Dada uma lista simplesmente encadeada, implemente a função void meiose(). 
Esta função deve percorrer toda a lista, dividindo cada célula em duas, 
com cada nova célula contendo a metade do valor da célula original.
*/
//CELULA
typedef struct Celula{
    struct Celula *prox;
    int valor;
} Celula;

Celula* novaCelula(int num){
    Celula *nova = (Celula*) malloc(sizeof(Celula)); // reserva espaço na memória para 1 célula
    nova->prox = NULL;                               // ainda não aponta para ninguém
    nova->valor = num;                               // guarda o número
    return nova;                                     // devolve o ENDEREÇO da célula
}


//LISTA
typedef struct Lista{
    Celula *primeiro;
     Celula *ultimo;
} Lista;

void iniciarLista(Lista *lista){
    lista->primeiro = novaCelula(0);     // célula cabeça (o valor dela não importa)
    lista->ultimo = lista->primeiro;     // lista vazia: o último é a própria cabeça
}
void inserirFim(int num, Lista *lista){ 
    Celula *new = Celula(num, new);  //criou nova celula
    lista->ultimo->prox = new;      //linkou
    lista->ultimo = new;            //atualizou ultima
}
void mostrar(Lista *lista){
    Celula *atual = lista->primeiro->prox;
    while(atual != NULL){
        printf("%d ",atual->valor);

        atual = atual->prox;
    }

}

//FUNCAO EXERCICIO
void meiose(Lista *lista){
    Celula *atual = lista->primeiro->prox;  //atual

    while (atual != NULL){  //cobre ate a ultima celula
        int metade = (atual->valor)/2;
        atual->valor = metade;

        CCelula *nova = novaCelula(metade); //cria nova celula com metade do valor

        nova->prox = atual->prox;    //ajusta ponteiros
        atual->prox = nova;

        if(atual == lista->ultimo){
            lista->ultimo = nova;
            
        }
        atual = atual->prox->prox;  //pula 2
    }
}


int main(){
    Lista lista;
    iniciarLista(&lista);

    int num;   //numero a ser inserido
    scanf("%d",&num); 

    while(num != -1){   //lendo ate -1 e inserindo na lista
        inserirFim(num, &lista);
        scanf("%d",&num);
    }

    meiose(&lista);
    mostrar(&lista);

    return 0;
}