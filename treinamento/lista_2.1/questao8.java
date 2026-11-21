class No{
    ...
    
    public static int getAltura(No i){
        if(i == null) return 0;
        else return this.altura;
    }
    public void setAltura(){
        this.altura = 1 + Math.max(this.getAltura(this.dir), this.getAltura(this.esq));
    }
    public int calculaFator(No i){
        return No.getAltura(i.dir) - No.getAltura(i.esq);
    }
}
class Arvore{
    public void inserir(int x){
        raiz = inserir(x ,raiz);
    }
    private No inserir(int x, No i){
        if(i == null){
            return new No(x);
        }
        else if(x > i.elemento){
            i.dir = inserir(x, i.dir);
        }
        else if(x < i.elemento){
            i.esq = inserir(x, i.esq);
        }

        balancear(i);
        return i;
    }

    public No balancear(No i){
        if(i == null) return null;

        i.setAltura();
        int Fb = i.calculaFator(i);

        if(Fb == 2){
            if()
        }
    }
}