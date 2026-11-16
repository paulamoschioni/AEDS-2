Celula* cabeca;                                     // global (o malloc fica dentro da função)

void insere(int x) {
    Celula* nova = (Celula*) malloc(sizeof(Celula));   
    nova->numero = x;
    Celula* i = cabeca;
    while (i->prox != NULL && i->prox->numero > x) {
        i = i->prox;
    }
    nova->prox = i->prox;
    i->prox = nova;
}

int temNaMatriz(int num, CelulaMatriz* inicio) {
    int resp = 0;
    for (CelulaMatriz* i = inicio; resp == 0 && i != NULL; i = i->inf) {  
        CelulaMatriz* j = i;
        while (resp == 0 && j != NULL) {                                  // anda na linha
            if (j->numero == num) resp = 1;                               // sem insere aqui
            j = j->prox;
        }
    }
    return resp;
}

void caminhar(No* i, CelulaMatriz* inicio) {
    if (i == NULL) return;
    caminhar(i->esq, inicio);                       // CORREÇÃO 1: esq/dir
    caminhar(i->dir, inicio);
    if (temNaMatriz(i->numero, inicio) == 1) {
        insere(i->numero);
    }
}

// 4º: a função pedida (CORREÇÃO 2: cria a cabeça uma vez só)
Celula* encontrarRepetidos(No* raiz, CelulaMatriz* inicio) {
    cabeca = (Celula*) malloc(sizeof(Celula));
    cabeca->prox = NULL;
    caminhar(raiz, inicio);
    return cabeca;
}