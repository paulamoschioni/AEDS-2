
public public int contarPalavras(char primeiro, char ultimo) {
    return contarPalavras(primeiro, ultimo, raiz);
}

private int contarPalavras(char primeiro, char ultimo, No i) {
    if (i == null) return 0;                              // CORREÇÃO 1: letra não existe
    if (primeiro == i.letra) {
        return procuraInterno(i.raiz, ultimo);            // achou: conta na árvore de dentro
    } else if (primeiro > i.letra) {
        return contarPalavras(primeiro, ultimo, i.dir);
    } else {
        return contarPalavras(primeiro, ultimo, i.esq);
    }
}

private int procuraInterno(No2 i, char ultimo) {
    if (i == null) return 0;
    int esq = procuraInterno(i.esq, ultimo);
    int dir = procuraInterno(i.dir, ultimo);
    int atual = 0;
    int tam = i.palavra.length();                         // CORREÇÃO 2: length()
    if (tam >= 1 && i.palavra.charAt(tam - 1) == ultimo) atual = 1;   // DETALHE: >= 1
    return atual + esq + dir;
} {
    
}
