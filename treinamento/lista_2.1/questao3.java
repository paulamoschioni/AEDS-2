// Ideia: o caminhar EM ORDEM (esq -> raiz -> dir) de uma ABB
// sempre gera os elementos em ordem crescente.
// Entao basta percorrer e verificar se cada elemento e maior que o anterior.

private Integer anterior; // ultimo valor visitado (null = nenhum visitado ainda)
private boolean ehABB;    // vira false se algum elemento sair de ordem

public boolean verificarABB() {
    anterior = null;
    ehABB = true;
    caminharCentral(raiz);
    return ehABB;
}

private void caminharCentral(No i) {
    if (i == null || !ehABB) { // caso base (ou ja achou erro: nao precisa continuar)
        return;
    }

    caminharCentral(i.esq); // 1. esquerda

    // 2. "visita": compara com o anterior
    if (anterior != null && i.elemento <= anterior) {
        ehABB = false;
    }
    anterior = i.elemento;

    caminharCentral(i.dir); // 3. direita
}
