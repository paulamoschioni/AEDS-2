public E remocaoDoItem(E item) throws Exception {
    if (vazia()) {
        throw new Exception("Lista vazia!");
    }

    // procurar a posição do item
    int pos = 0;                              // posição 0 = 1º elemento de verdade
    Celula<E> atual = primeiro.getProximo();  // pula a célula cabeça

    // anda ENQUANTO existir célula E ela ainda não for o item procurado
    while (atual != null && !atual.getItem().equals(item)) {
        pos++;                                // conta mais uma posição
        atual = atual.getProximo();           // vai para a próxima célula
    }

    // 3) se saiu do laço porque acabou a lista → não achou
    if (atual == null) {
        throw new Exception("Item não encontrado!");
    }

    // 4) achou na posição 'pos' → reaproveita o remover que já existe
    return remover(pos);
}