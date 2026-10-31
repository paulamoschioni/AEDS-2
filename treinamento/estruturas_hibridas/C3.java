public int contar(char c){
    return contar(c, raiz);
}
private int contar(char c, No i){
    //caso base: letra nao existe
    if(i == null){
        return 0;
    }

    if(i.letra == c){
        Celula atual = i.primeiro;
        int num = 0;
        while(atual != null){
            num++;
            atual = atual.prox;
        }
        return num;

    } else if(c > i.letra){
        return contar(c, i.dir);
    } else {
        return contar(c, i.esq);
    }
}


public char letraMaisPop(char c){
    return letraMaisPop(c, raiz);
}
private char letraMaisPop(char c, No i){
    if(i == null){
        
    }
}