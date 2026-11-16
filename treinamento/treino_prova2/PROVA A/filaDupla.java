public class filaDupla {
    
    public void enfileirarInicio(E item) {
        //inserir o item na AUXILIAR
        filaAux.enfileirar(item);

        //inserir tudo da filaPrincipal no fim da fila AUX 
    while(!filaPricipal.vazia()){
        filaAux.enfileirar(filaPricipal.desenfileirar());
    }

        //voltar inserindo tudo da aux na principal
        while(!filaAux.vazia()){
            filaPrincipal.enfileirar(filaAux.desefileirar());
        }
    }

    public E desenfileirarFim() {
        if (vazia()) {      //fila principal esta vazia??
        return null;                      
    }

    //colocar os itens da fila pricipal a PILHA
    while(!filaPrincipal.vazia()){
        pilha.empilheirar(filaPrincipal.desenfileirar());
    }

    //retira o ultimo elemento da fila
    E resp = pilha.desempilhar;

    //passar da pilha para fila aux, pq fica invertido
    while(!pilha.vazia()){
        filaAux.enfileirar(pilha.desempilhar());
    }

    //passar para a pilha, aindaa invertido
    while(!FilaAux.vazia()){
        pilha.empilhar(filaAux.desefileirar());
    }

    //agora sim passa a ordem correta p a principal
    while(!pilha.vazia()){
        filaPrincipal.enfileirar(pilha.desempilhar());
    }

    return resp;
    }
}
