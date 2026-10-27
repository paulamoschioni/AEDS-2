public class invertePilha {
    // Inverte as setas a partir de 'atual'.
// Devolve a célula que ficou no FIM da parte invertida (o próprio 'atual').
private Celula<E> transformaPilha(Celula<E> atual) {
    if (atual.getProximo() == null) {      
        topo = atual;                      
        return atual;
    }
    Celula<E> novaP = transformaPilha(atual.getProximo()); 
    novaP.setProximo(atual);                
    atual.setProximo(null);                
    return atual;                          
}

public void imprimirReverso() {
    if (topo == null) return;               // pilha vazia: nada a fazer

    transformaPilha(topo);                  // 1) inverte

    for (Celula<E> i = topo; i != null; i = i.getProximo()) {
        System.out.print(i.getItem() + " "); // 2) imprime do (novo) topo ao fundo
    }

    transformaPilha(topo);                  // 3) desinverte: a pilha volta ao normal
}
}
