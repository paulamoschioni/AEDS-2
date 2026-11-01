// Função recursiva: ordena o trecho da lista que vai de "esq" até "dir".
// posEsq e posDir são as POSIÇÕES (os "crachás") dessas células.
void quicksortRec(CelulaDupla* esq, int posEsq, CelulaDupla* dir, int posDir) {
    CelulaDupla* i = esq;   int posI = posEsq;   // i vem do início do trecho
    CelulaDupla* j = dir;   int posJ = posDir;   // j vem do fim do trecho

    // --- PIVÔ: anda do esq até a posição do meio (sua ideia!) ---
    CelulaDupla* meio = esq;
    for (int k = posEsq; k < (posEsq + posDir) / 2; k++) {
        meio = meio->prox;
    }
    int pivo = meio->elemento;   // guarda o VALOR do pivô

    // --- PARTICIONAMENTO: igual ao do vetor, mas com ponteiros + posições ---
    while (posI <= posJ) {
        // i avança enquanto o elemento for menor que o pivô
        while (i->elemento < pivo) { i = i->prox; posI++; }
        // j volta enquanto o elemento for maior que o pivô
        while (j->elemento > pivo) { j = j->ant;  posJ--; }

        if (posI <= posJ) {
            // troca os VALORES das duas células (não mexe nos ponteiros)
            int tmp = i->elemento;
            i->elemento = j->elemento;
            j->elemento = tmp;
            // os dois andam um passo
            i = i->prox; posI++;
            j = j->ant;  posJ--;
        }
    }

    // --- RECURSÃO: ordena as duas metades ---
    if (posEsq < posJ) quicksortRec(esq, posEsq, j, posJ);   // metade da esquerda
    if (posI < posDir) quicksortRec(i, posI, dir, posDir);   // metade da direita
}

// Função pedida na prova
void quicksort() {
    // conta os elementos (sua ideia: anda até voltar na cabeça)
    int n = 0;
    for (CelulaDupla* atual = primeiro->prox; atual != primeiro; atual = atual->prox) {
        n++;
    }
    // só ordena se tiver 2 ou mais elementos
    if (n > 1) {
        quicksortRec(primeiro->prox, 0, primeiro->ant, n - 1);   // do 1º ao último
    }
}