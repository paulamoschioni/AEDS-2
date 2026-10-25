
/*Complexidade = O(n)*/
typedef struct Celula {
int elemento;
struct Celula* prox;
} Celula;

Celula* inicializaCelula(int elem){
   Celula* nova = malloc sizeof(Celula);
   nova->elemento = elem;
   nova->prox = NULL;

   return nova;
}

Celula* inicio;
Celula* fim;

void Meiose(){
   Celula* atual = inicio;
   while(atual != NULL){
      int elemOriginal = atual->elemento;
      
      //cria nova celula com metade do elemento
      int metade = elemOriginal/2;
      Celula* nova = inicializaCelula(metade);

      //linka celulas 
      nova->prox = atual->prox;
      atual->prox = nova;
      if(atual == fim) fim = nova;

      //atualiza a ultima celula para dps da nova criada
      atual->elemento = metade;
      atual = atual->prox->prox;
   }
}
  

