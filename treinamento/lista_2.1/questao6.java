class ArvoreAVL {
private No raiz;

public int fatorBalanceamento(int elemento) {
    return fatorBalanceamento(elemento, raiz);
}
private int fatorBalanceamento(int x, No i){
    if(i == null){
        return Integer.MIN_VALUE;
    }
    else if(x > i.elemento){
        return fatorBalanceamento(x, i.dir);
    }
    else if(x < i.elemento){
        return fatorBalanceamento(x, i.esq);
    }
    else{
        return No.getAltura(i.dir) - No.getAltura(i.esq);
    }
}
}


class No{
    ...

    public static int getAltura(No i){
        if(i == null) return 0;
        else return i.altura;
    }

}