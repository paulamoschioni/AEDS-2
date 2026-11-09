/*Codigo de rotacionar arvore*/
No rotacaoEsq(No no){   //rotacao AVÔ para esquerda
    No NoDir = no.dir;      //salva o pai
    No noDirEsq = NoDir.esq;    //salva o b

    NoDir.esq = no;
    no.dir = noDirEsq;

    return noDir;
}


No rotacaoDir(No no){   //rotaciona AVÔ para direita
    No NoEsq = no.esq;
    No NoEsqDir = NoEsq.dir;

    no.esq = noEsqDir;  //avo tem novo filho
    NoEsq.dir = no;

    return noEsq;
}


No rotacaoEsqDir(No no){
    no.esq = rotacaoEsq(no.esq);        //tem que salvar a nova arvore a esquerda

    return rotacaoDir(no);              //depois rotaciona o AVO a direita
}


No rotacaoDirEsq(No no){
    no.dir = rotacaoDir(no.dir);

    return rotacaoEsq(no);
}