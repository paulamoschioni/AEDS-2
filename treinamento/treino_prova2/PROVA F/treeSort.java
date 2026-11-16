public static void treeSort(int array[]) {
    // passar para arvore
    Arvore arvore = new Arvore();
    for (int i = 0; i < array.length; i++) {
        arvore.insere(array[i]);
    }
    // caminhar em ordem
    arvore.caminhaEm(array);
}

int pos = 0;

public void caminhaEm(int array[]) {
    caminharEm(array, raiz);
}

private void caminharEm(int array[], No i) {
    if (i != null) {
        caminharEm(array, i.esq);
        array[pos] = i.elemento;
        pos++;
        caminharEm(array, i.dir);
    }
}