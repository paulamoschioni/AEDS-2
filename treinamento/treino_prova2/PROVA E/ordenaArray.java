class Arvore {
    private No raiz;
    private int pos;   // NOVO: próxima posição do array a ser preenchida

    public static void ordenar(int[] array) {
        Arvore arvore = new Arvore();              // cria a árvore (método é static)
        for (int i = 0; i < array.length; i++) {
            arvore.inserir(array[i]);              //insere cada elemento
        }
        arvore.caminharEm(array);                  //caminha em ordem preenchendo o array
    }

    public void caminharEm(int[] array) {
        pos = 0;                                   //começa a escrever na posição 0
        caminharEm(raiz, array);
    }

    private void caminharEm(No i, int[] array) {
        if (i != null) {
            caminharEm(i.esq, array);              // primeiro todos os menores
            array[pos] = i.elemento;               // grava no array
            pos++;                                 // avança para a próxima posição
            caminharEm(i.dir, array);              // depois todos os maiores
        }
    }
}