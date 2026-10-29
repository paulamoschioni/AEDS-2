Celula* inicializaCelula(int x){
    Celula* nova = (Celula*) malloc(sizeof(Celula));
    nova->elemento = x;
    nova->prox = NULL;
    return nova;
}

void Meiose(){
    Celula* atual = inicio;
    while (atual != NULL){                  
        int metade = atual->elemento / 2;   
        atual->elemento = metade;

        Celula* nova = inicializaCelula(metade);
        nova->prox = atual->prox;           
        atual->prox = nova;

        if (atual == fim) fim = nova;

        atual = atual->prox->prox;          
    }
}