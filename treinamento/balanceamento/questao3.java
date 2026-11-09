/*
A) 6 ordens
B) 5 árvores
C)  */

void balancear() {
    if (raiz.esq != null && raiz.dir != null) {  
        System.out.println("Árvore já balanceada");
    } else if (raiz.dir != null) {
        if (raiz.dir.dir != null) {                // RETA A DIREITA
            raiz = rotacionarEsq(raiz);
        } else {                                   //JOELHO dir esq
            raiz = rotacionarDirEsq(raiz);
        }
    } else {                                       // if (raiz.esq != null)
        if (raiz.esq.dir != null) {                // JOELHO ESQ DIR
            raiz = rotacionarEsqDir(raiz);
        } else {                                   // RETA A ESQUERDA
            raiz = rotacionarDir(raiz);
        }
    }
}