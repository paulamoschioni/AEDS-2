class No {
    public int elemento;
    public int contaRep;          // contador é de CADA nó
    public No esq, dir;

    public No(int x) {
        elemento = x;
        contaRep = 1;             // primeira inserção já conta
        esq = dir = null;
    }
}

class Arvore {
    private No raiz;

    public Arvore() {
        raiz = null;
    }

    public void inserir(int x) {
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i) {
        if (i == null) return new No(x);

        if (x > i.elemento) {              // maior → direita
            i.dir = inserir(x, i.dir);
        } else if (x == i.elemento) {      // igual → só conta
            i.contaRep = i.contaRep + 1;
        } else {                           // menor → esquerda
            i.esq = inserir(x, i.esq);
        }
        return i;
    }
}