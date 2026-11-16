public int contarPalavras(String padrao) {
    return contarPalavras(padrao, raiz);            // começa a pesquisa pela raiz
}

private int contarPalavras(String padrao, No i) {
    if (i == null) return 0;                        // letra não existe na árvore
    else if (i.letra == padrao.charAt(0)) {
        return arvoreInterna(i.raiz, padrao);       
    }
    else if (padrao.charAt(0) > i.letra) {
        return contarPalavras(padrao, i.dir);       // letra maior: vai p/ direita
    } else {
        return contarPalavras(padrao, i.esq);       // letra menor: vai p/ esquerda
    }
}

private int arvoreInterna(No2 i, String padrao) {
    if (i == null) return 0;

    int esq = arvoreInterna(i.esq, padrao);         // conta na subárvore esquerda
    int dir = arvoreInterna(i.dir, padrao);         // conta na subárvore direita

    int atual = 0;
    int tamPadrao = padrao.length();
    if (i.palavra.length() == tamPadrao) atual = 1; // este nó serve?

    return atual + esq + dir;
}