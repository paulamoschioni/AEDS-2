CelulaLista maiorPilha() {
    if (inicio == null) return null;              // lista vazia: não há pilha

    CelulaLista maior = inicio;                   // começa apontando p/ início
    int tamMaior = contaPilha(inicio.topo);       // tamanho da maior até agora

    for (CelulaLista atual = inicio.prox; atual != null; atual = atual.prox) {
        int tam = contaPilha(atual.topo);         // conta a pilha desta célula
        if (tam > tamMaior) {                     // achou uma pilha maior?
            maior = atual;                        //   guarda a célula
            tamMaior = tam;                       //   e o tamanho dela
        }
    }
    return maior;                                 
}
