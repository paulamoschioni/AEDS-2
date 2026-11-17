class Arvore{
    No raiz;

    public Arvore(){
        raiz = null;
    }

    public int contarFolhas(){
        return contarFolhas(raiz);
    }
    private int contarFolhas(No i){
        if(i == null){
            return 0;
        }
        int folhasEsq = contarFolhas(i.esq);
        int folhasDir = contarFolhas(i.dir);

        int atual = 0;
        if(i.esq == null && i.dir == null){
            atual = 1;
        }

        return atual + folhasDir + folhasEsq;
    }

    public int contarNosComUmFilho(){
        return contarNosComUmFilho(raiz);
    }
    private int contarNosComUmFilho(No i){
        if(i == null){
            return 0;
        }
        
        int alvosEsq = contarNosComUmFilho(i.esq);
        int alvosDir = contarNosComUmFilho(i.dir);

        int atual = 0;
        if(i.esq == null && i.dir != null || i.esq != null && i.dir == null){
            atual = 1;
        }

        return atual + alvosDir + alvosEsq;
    }

    public int contarNosComDoisFilhos(){
        return contarNosComDoisFilhos(raiz);
    }
    private int contarNosComDoisFilhos(No i){
        if(i == null){
            return 0;
        }
        
        int alvosEsq = contarNosComDoisFilhos(i.esq);
        int alvosDir = contarNosComDoisFilhos(i.dir);

        int atual = 0;
        if(i.esq != null && i.dir != null){
            atual = 1;
        }

        return atual + alvosDir + alvosEsq;
    }

    public boolean pesquisar(int x){
        return pesquisar(x, raiz);
    }
    private boolean pesquisar(int x, No i){
        if(i == null){
            return false;
        }
        else if(x == i.elemento){
            return true;
        }
        else if(x > i.elemento){
            return pesquisar(x, i.dir);
        }
        else{
            return pesquisar(x, i.esq);
        }
    }
}
