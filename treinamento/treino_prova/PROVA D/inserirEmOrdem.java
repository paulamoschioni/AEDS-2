public void inserir(String palavra) {
    inserir(palavra, raiz, palavra.charAt(0));
}

private void inserir(String nome, No i, char L) {
    // caso base: achou o nó da letra
    if (L == i.letra) {
        Celula nova = new Celula();      // a classe não tem construtor com parâmetro
        nova.palavra = nome;

        if (i.primeiro == i.ultimo) {                                  // lista vazia (só a cabeça)
            i.primeiro.prox = nova;
            i.ultimo = nova;
        } else if (nome.compareTo(i.primeiro.prox.palavra) < 0) {      // início
            nova.prox = i.primeiro.prox;
            i.primeiro.prox = nova;
        } else if (nome.compareTo(i.ultimo.palavra) > 0) {             // fim
            i.ultimo.prox = nova;
            i.ultimo = nova;
        } else {                                                       // meio
            Celula atual = i.primeiro.prox;          // pula a cabeça (palavra dela é null)
            while (atual != i.ultimo) {
                // atual vem ANTES de nome E nome vem ANTES de atual.prox
                if ((nome.compareTo(atual.palavra) > 0) && (nome.compareTo(atual.prox.palavra) < 0)) {
                    nova.prox = atual.prox;   
                }
                atual = atual.prox;
            }
        }
    } else if (L > i.letra) {
        inserir(nome, i.dir, L);     // letra maior → direita
    } else {
        inserir(nome, i.esq, L);     // letra menor → esquerda
    }
}