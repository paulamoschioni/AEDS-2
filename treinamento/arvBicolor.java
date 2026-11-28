class No{
    int elemento;
    No esq, dir;
    boolean cor;

    public No(int x, boolean c){
        elemento = x;
        esq = dir = null;
        cor = c;
    }
}

class ArvoreBicolor{
    No raiz;

    public ArvoreBicolor(){
        raiz = null;
    }

    //INSERIR
    public void inserir(int x){
        if(raiz == null) raiz = new No(x, false);
        else inserir(x, null, null, null, raiz);
    }

    public void inserir(int x, No bisavo, No avo, No pai, No i){
        if(i == null){
            if(x > i.elemento) i = pai.dir = new No(x, true);
            else i = pai.dir = new No(x, true);
            
            if(pai.cor) balancear(bisavo, avo, pai, i);
        } 
        else {
            if(isQuatroNo(i)){
                fragmentar(i);
                if(i == raiz) i.cor = false;
                else if(pai.cor) balancear(bisavo, avo, pai, i);
            }
            if(x < i.elemento) inserir(x, avo, pai, i, i.esq);
            else if(x > i.elemento) inserir(x, avo, pai, i, i.dir);
            else System.out.println("ERRO");
        }
    }

    //PESQUISAR
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

    // DETECTAR 4-NO
    public boolean isQuatroNo(No i){
        return i != null && i.esq != null && i.dir != null && i.esq.cor && i.dir.cor;
    }

    // FRAGMENTAR: chama qndo é um 4 nó
    public void fragmentar(No i){
        i.cor = true;
        i.dir.cor = false;
        i.esq.cor = false;

        if(i == raiz) i.cor = false;
    }

    // BALANCEAR
    public void balancear(No bisavo, No avo, No pai, No i){
        if(pai.elemento > avo.elemento){
            if(pai.elemento > i.elemento){
                avo = rotacaoDirEsq(avo);
            } else {
                avo = rotacaoEsq(avo);
            }
        } 
        else{
            if(pai.elemento > i.elemento){
                avo = rotacaoDir(avo);
            } else {
                avo = rotacaoEsqDir(avo);
            }
        }
        
        if(bisavo == null) raiz = avo;
        else if(avo.elemento < bisavo.elemento) bisavo.esq = avo;
        else bisavo.dir = avo;
        avo.cor = false;
        avo.esq.cor = avo.dir.cor = true;
    }

    // ROTACOES 
    private No rotacaoEsq(No no){
        No noDir = no.dir;
        No noDirEsq = no.dir.esq;

        noDir.esq = no;
        return noDir;
    }

    private No rotacaoDir(No no){
        No noEsq = no.esq;
        No noEsqDir = no.esq.dir;

        noEsq = no;
        return noEsq;
    }

    private No rotacaoDirEsq(No no){
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private No rotacaoEsqDir(No no){
        no.dir = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }


    //INSERIR
}
