import java.util.Scanner;
/* Paula Moschioni
21/10/2026
Código teste sobre matriz de árvores. Contém: */
class No{
    No dir, esq;
    int elemento;

    public No(int x){   //construtor de No
        dir = esq = null;
        elemento = x;
    }
}

class Arvore{
    No raiz;

    public Arvore(){    //vazio para chamar no main
        this(0);
    }
    public Arvore(int x){   //para construir a raiz pelo main
        No novo = new No(x);
        raiz = novo;
    }

    /*---- INSERIR ---- */
    public void inserir(int x){
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i){
        //caso base
        if(i == null){
            return new No(x);
        }
        else if(x > i.elemento){    //vai para a direita
            i.dir = inserir(x, i.dir);
        }
        else if(x < i.elemento){    //vai para a esquerda
            i.esq = inserir(x, i.esq);
        }
        else{
            System.out.println("erro");
        }

        return i;
    }

    /*---- CAMINHAR EM ORDEM  ---- */
    public void caminharEm(){
        caminharEm(raiz);
    }
    private void caminharEm(No i){
        if(i != null){
            caminharEm(i.esq);
            System.out.print(i.elemento+ " ");
            caminharEm(i.dir);
        }
    }

}
class CelulaM{
    int elemento;
    CelulaM dir, esq, inf, sup;

}

class Matriz{
    //...

    public void enviaElementos(Arvore arvore){
        CelulaM primeiraCol = inicio;

        for(int i = 0; i < coluna; i++){     //caminha dde coluna a coluna
            CelulaM atual = primeiraCol;
            //caminha pela linha da coluna i
            while(atual != null){
                int envia = atual.elemento;

                arvore.inserir(envia);

                //atualiza atual
                atual = atual.inf;
            }

            //atualiza a proxima primeira
            primeiraCol = primeiraCol.dir;
        }
    }

    public void ordenarComArvore() {
        Arvore arvore = new Arvore();   // árvore vazia
        enviaElementos(arvore);         // insere tudo (a árvore se organiza sozinha)
        arvore.caminharEm();            // lê em ordem → imprime crescente
    }

}

public class matrizArvore {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        // Lendo entradas


        sc.close();
    }
}
