class Arvore {
private No raiz;

//setNivel: calcula a altura
public static int setNivel(No i){
    if(i == null){
        return -1;
    }
    
    int hEsq = setNivel(i.esq);
    int hDir = setNivel(i.dir);
    int maior;
    if(hDir > hEsq) maior = hDir;
    else maior = hEsq;

    return maior + 1; // soma o proprio no
}

//get fator: calculo temporario
public int calculaFator(No i){
    int nivelEsq = setNivel(i.esq);
    int nivelDir = setNivel(i.dir);

    return nivelDir - nivelEsq;
}

public int contarNosNaoBalanceados() {
    return contarNosNaoBalanceados(raiz);
}
private int contarNosNaoBalanceados(No i) {
    if(i == null){
        return 0;
    }

    int FbsEsq = contarNosNaoBalanceados(i.esq);
    int FbsDir = contarNosNaoBalanceados(i.dir);
    int atual = 0;

    int fb = calculaFator(i);
    if(fb > 1 || fb < -1){
        atual = 1;
    }

    return atual + FbsDir + FbsEsq;
}
}
class No {
public int elemento;
public No esq , dir;
}