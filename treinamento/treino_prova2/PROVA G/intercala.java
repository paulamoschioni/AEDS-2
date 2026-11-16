public static int[] vetorOrdenado(int[] vetA, int[] vetB) {
    int tamA = vetA.length;
    int tamB = vetB.length;
    int[] novo = new int[tamA + tamB];

    int i = tamA - 1;                      // último índice do A
    int j = tamB - 1;                      // último índice do B
    int k = tamA + tamB - 1;               // última posição do novo

    while (i >= 0 || j >= 0) {             // ainda tem elemento em pelo menos um
        if (i >= 0 && j >= 0) {            // os dois têm elementos: compara
            if (vetA[i] > vetB[j]) {
                novo[k] = vetA[i];         // A tem o maior: vai pro fim do novo
                i--;                       // só o A anda
            } else {
                novo[k] = vetB[j];         // B tem o maior (ou empate)
                j--;                       // só o B anda
            }
        } else if (i >= 0) {               // B acabou: copia o resto do A
            novo[k] = vetA[i];
            i--;
        } else {                           // A acabou: copia o resto do B
            novo[k] = vetB[j];
            j--;
        }
        k--;                               // o novo anda em toda volta
    }
    return novo;
}
