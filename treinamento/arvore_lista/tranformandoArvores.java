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

    /* 07- IMAGEM ESPELHADA DA ARVORE */
    public void espelhaArvore(){
        espelhaArvore(raiz);
    } 

    public void espelhaArvore(No i){
        if(i == null){
            return;
        }
        //chama esquerda e direita
        espelhaArvore(i.esq); 
        espelhaArvore(i.dir);

        //troca
        No temp = i.esq;
        i.esq = i.dir;
        i.dir = temp;
    }

    /* 08- É BST? */
public boolean bst(){
    return bst(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE);
}

public boolean bst(No i, int min, int max){
    if(i == null){
        return true;
    }

    // chama esq e dir, passando a faixa atualizada
    boolean esq = bst(i.esq, min, i.elemento);   // à esquerda: tudo precisa ser MENOR que eu
    boolean dir = bst(i.dir, i.elemento, max);   // à direita: tudo precisa ser MAIOR que eu

    // condição do próprio nó: estou dentro da faixa que recebi?
    boolean dentro = false;
    if(i.elemento > min && i.elemento < max){
        dentro = true;
    }

    return esq && dir && dentro;
}

    
}

class transformandoArvores{
    /*06-  COMPARA SE AS ARVORES SÃO IGUAIS */
    public boolean comparaArvores(Arvore arvore1, Arvore arvore2){      //arvores criadas no MAIN e enviadas
        return comparaArvores(arvore1, arvore1.raiz, arvore2, arvore2.raiz);
    }
    public boolean comparaArvores(Arvore arvore1, No i1, Arvore arvore2, No i2){
        if(i1 == null && i2 == null){       //se as duas partes estao vazias
            return true;
        }
        if(i1 == null || i2 == null){   // pelo menos um é null...
             return false;
        }

        //chama tudo da esq e da direita da ARVORE1 E da ARVORE2
        boolean esq= comparaArvores(arvore1, i1.esq, arvore2, i2.esq);
        boolean dir = comparaArvores(arvore1, i1.dir, arvore2, i2.dir);

        //temos em maos cada no desejado, agora ver se ele entra na condicao
        boolean tem = false;
        if(i1.elemento == i2.elemento){
            tem = true;
        }
    
        return tem && dir && esq;       //retorna true apenas se TEM(raiz) E DIR(parte da direita das 2 arvores) E ESQ(parte da esquerda das duas arvores) forem iguais
    }


}