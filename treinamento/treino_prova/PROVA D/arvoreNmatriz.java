// método público: cria a cabeça UMA vez só
public Celula toLista(No raiz, CelulaMat inicio, int L) {
    Celula cabeca = new Celula();
    toLista(raiz, inicio, L, cabeca);   // a recursão preenche essa mesma lista
    return cabeca;
}

// auxiliar recursivo: mesmo nome, parâmetros diferentes (sobrecarga)
private void toLista(No i, CelulaMat inicio, int L, Celula cabeca) {
    if (i != null) {
        toLista(i.esq, inicio, L, cabeca);                  // tudo da esquerda
        if (temMatriz(i, inicio, L) == false) insere(i, cabeca);
        toLista(i.dir, inicio, L, cabeca);                  // tudo da direita
    }
}

private boolean temMatriz(No no, CelulaMat inicio, int L) {
    for (CelulaMat i = inicio; i != null; i = i.inf) {      // desce as linhas
        for (CelulaMat j = i; j != null; j = j.dir) {       // anda na linha
            if (j.elemento == no.elemento) return true;     // achou: sai na hora
        }
    }
    return false;                                           // percorreu tudo e não achou
}

} else {
    Celula atual = primeiro.prox;
    boolean jaFoi = false;
    while (atual.prox != null && jaFoi == false) {          // para quando inserir
        if (atual.elemento < i.elemento && atual.prox.elemento > i.elemento) {
            nova.prox = atual.prox;
            atual.prox = nova;
            jaFoi = true;
        }
        atual = atual.prox;
    }
    if (jaFoi == false) atual.prox = nova;   // fora do laço: atual é o último → insere no fim
}