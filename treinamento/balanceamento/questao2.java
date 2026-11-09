No rotacaoDir(No no){
    No NoEsq = no.Esq;
    No NoEsqDir = noEsq.dir;

    no.esq = noEsqDir;
    NoEsq.dir = no;

    return NoEsq;
}
