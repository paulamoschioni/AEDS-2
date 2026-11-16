void removerUltimaColuna(Matriz *matriz) {
    if (matriz->colunas < 2) {                          
        printf("Erro: a matriz precisa ter pelo menos 2 colunas\n");
        return;
    }
    for (Celula *i = matriz->inicio; i != NULL; i = i->inf) {   // desce linha por linha
        Celula *j = i;
        while (j->dir->dir != NULL) {                   
            j = j->dir;                                 // ...até a penúltima coluna
        }
        Celula *lixo = j->dir;                          // a última célula da linha
        j->dir = NULL;                                  // penúltima vira a última
        free(lixo);                                     // libera a memória
    }
    matriz->colunas--;                                  // CORREÇÃO 3: atualiza o contador
}