
    public void inserir(String palavra) {
    inserir(palavra, raiz);
}

private void inserir(String nome, No i) {
    if (i.letra == nome.charAt(0)) {                 // achou o nó da letra
        Celula novo = new Celula(nome);
        if (i.primeiro == null) {                    //lista vazia
            i.primeiro = novo;
        } else {
            Celula atual = i.primeiro;
            while (atual.prox != null) {             //para no último
                atual = atual.prox;
            }
            atual.prox = novo;                       // pendura no fim
        }
    } else if (nome.charAt(0) > i.letra) {           // letra maior → direita
        inserir(nome, i.dir);
    } else {                                         // letra menor → esquerda
        inserir(nome, i.esq);
    }
}

