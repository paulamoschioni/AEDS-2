void removerUltimaColuna(Matriz *matriz){
    // matriz vazia: nada a remover
    if(matriz == NULL || matriz->inicio == NULL || matriz->colunas == 0){
        return;
    }

    // só 1 coluna: não existe penúltima, a matriz inteira some
    if(matriz->colunas == 1){
        Celula *atual = matriz->inicio;
        while(atual != NULL){
            Celula *tmp = atual;
            atual = atual->inf;   // guarda o próximo ANTES de liberar
            free(tmp);
        }
        matriz->inicio = NULL;
        matriz->colunas = 0;
        matriz->linhas = 0;
        return;
    }

    // caso normal: anda até a penúltima coluna
    Celula *atual = matriz->inicio;
    for(int i = 0; i < (matriz->colunas - 2); i++){
        atual = atual->dir;
    } // parou na penúltima célula da primeira linha

    for(int j = 0; j < (matriz->linhas); j++){
        free(atual->dir);
        atual->dir = NULL;
        // atualiza atual
        atual = atual->inf;
    }

    matriz->colunas--;
}