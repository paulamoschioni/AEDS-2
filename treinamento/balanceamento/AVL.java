/*Paula Moschioni
11/11/2026
Criando a base de árvore avl.
FUNÇÕES: balancear, */

class No {
    int elemento;
    No esq, dir;
    int nivel;

    No(int elemento) {
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
        this.nivel = 1;
    }

    // devolve a altura de um nó, tratando null como -1
    public static int getNivel(No no) {
        if(no == null){
            return -1;
        }
        else{
            return no.nivel;
        }
    }
    // recalcula a altura 
    public void setNivel() {
        nivel = 1 + Math.max(getNivel(dir), getNivel(esq));
    }

    // fator = altura(dir) - altura(esq)
    public static int getFator(No no) { 
        return getNivel(no.dir) - getNivel(no.esq);
    }
}

public class AVL {
    No raiz;
    
    public AVL() {
        raiz = null;
    }

    // ===================== AUXILIARES =====================
    private No balancear(No no) {
       int fator = No.getFator(no);

       if(fator == 2){
            if(No.getFator(no.dir) == -1){
                no.dir = rotacaoDireita(no.dir);
            }
        return rotacaoEsquerda(no);
       }
        else if(fator == -2){
            if(No.getFator(no.esq) == 1){
                no.esq = rotacaoEsquerda(no.esq);
            }
            return rotacaoDireita(no);
        }  else {
            no.setNivel();      //nao faz nada so atualiza o nivel
        }
    }

    // ===================== ROTAÇÕES =====================

    private No rotacaoDireita(No no) {
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        no.setNivel();
        noEsq.setNivel();
        return no;
    }

    private No rotacaoEsquerda(No no) {
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.esq = noDirEsq;

        noDir.setNivel();
        no.setNivel();
        return no;
    }

    private No rotacaoDuplaEsqDir(No no) {
        no.esq = rotacaoEsquerda(no.esq);
        return rotacaoDireita(no);
    }

    private No rotacaoDuplaDirEsq(No no) {
        // TODO: caso RL -> no.dir = rotacaoDireita(no.dir); return rotacaoEsquerda(no);
        return no;
    }

    // ===================== INSERIR =====================

    public void inserir(int x) {
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i) {
        // TODO: inserção igual à árvore binária de busca
        return balancear(i);
    }

    // ===================== REMOVER =====================

    public void remover(int x) {
        raiz = remover(x, raiz);
    }

    private No remover(int x, No i) {
        // TODO: remoção igual à árvore binária de busca (usar maiorEsq)
        return balancear(i);
    }

    private No maiorEsq(No i, No j) {
        // TODO: encontrar o maior da subárvore esquerda e trocar com i
        return j;
    }

    // ===================== PESQUISAR =====================

    public boolean pesquisar(int x) {
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(int x, No i) {
        // TODO
        return false;
    }

    // ===================== CAMINHAMENTOS =====================

    public void caminharCentral() {
        caminharCentral(raiz);
        System.out.println();
    }

    private void caminharCentral(No i) {
        // TODO: esq, raiz, dir
    }

    public void caminharPre() {
        caminharPre(raiz);
        System.out.println();
    }

    private void caminharPre(No i) {
        // TODO: raiz, esq, dir
    }

    public void caminharPos() {
        caminharPos(raiz);
        System.out.println();
    }

    private void caminharPos(No i) {
        // TODO: esq, dir, raiz
    }

    public int getAltura() {
        return altura(raiz);
    }

    // ===================== MAIN (teste) =====================

    public static void main(String[] args) {
        AVL arvore = new AVL();
        int[] valores = {10, 20, 30, 40, 50, 25};
        for (int v : valores) {
            arvore.inserir(v);
        }
        arvore.caminharCentral();
        arvore.caminharPre();
        System.out.println("Altura: " + arvore.getAltura());
    }
}