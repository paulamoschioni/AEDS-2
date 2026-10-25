import java.util.Scanner;
class No{
    No esq, dir;
    int elemento;

    public No(int x){
        esq = dir = null;
        elemento = x;
    }
}
class Arvore {
    No raiz;

    /*---- INSERIR ---- */
    public void inserir(int x){
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i){
        if(i == null){
            return new No(x);
        }
        else if(x > i.elemento){
            i.dir = inserir(x, i.dir);
        }
        else {
            i.esq = inserir(x, i.esq);
        }
        return i;
    }

    public void caminhamentoEm(){
        caminhamentoEm(raiz);
    }

    private void caminhamentoEm(No i){
        if(i != null){
            caminhamentoEm(i.esq);
            System.out.print(i.elemento + " ");
            caminhamentoEm(i.dir);
        }
    }
    /*11 - INTERCALAR NA ARVORE AS CELULAS DE LISTA SIMPLES E LISTA DUPLA1 */
    class Celula {
    int elemento;
    Celula prox;
    public Celula(int x){ elemento = x; prox = null; }
}

class CelulaDupla {
    int elemento;
    CelulaDupla ant, prox;
    public CelulaDupla(int x){ elemento = x; ant = prox = null; }
}

   
    public No noToAB(Celula p1, CelulaDupla p2, No i){
        Celula i = p1.prox;
        CelulaDupla j = p2.prox;

        while(i != null || j != null){
            if(i != null){
                inserir(i.elemento);
                i = i.prox;
            }
            if(j != null){
                inserir(j.elemento);
            }
        }
        return raiz;
    }



}   