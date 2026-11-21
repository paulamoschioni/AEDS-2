
class No{
    int elemento;
    No esq, dir;
    int altura;

    public No(int x){
        esq = dir = null;
        elemento = x;
        altura = 1;
    }

    public static int getAltura(No i){
        if(i == null) return 0;
        else return i.altura;
    }

    public void setAltura(){
        this.altura = 1 + Math.max(getAltura(this.esq), getAltura(this.dir));
    }

    public int calculaFator(){
        return getAltura(this.dir) - getAltura(this.esq);
    }
}

class Arvore{
    No raiz;

    public Arvore(){
        raiz = null;
    }

    public void inserir(int x){
        raiz = inserir(x, raiz);
    }
    private No inserir(int x, No i){
        if(i == null){
            return new No(x);
        }
        if(x > i.elemento){
            i.dir = inserir(x, i.dir);
        }
        else if(x < i.elemento){
            i.esq = inserir(x, i.esq);
        }
        else{
            System.out.println("erro");
        }

        return balancear(i);                 // AVL: na volta da recursão, atualiza altura e rebalanceia
    }


    public void remover(int x){
        raiz = remover(x, raiz);
    }
    private No remover(int x, No i){
        /*PARTE A: procurando o elemento */
        if(i == null){
            System.out.println("erro");
        }
        else if(x > i.elemento){              // FIX 2: "else if" -> se i for null, para aqui
            i.dir = remover(x, i.dir);        // FIX 1: era inserir
        }
        else if(x < i.elemento){
            i.esq = remover(x, i.esq);        // FIX 1: era inserir
        }
        /* PARTE B: achou o elemento. Agora veja se ele esta a esq, dir ou no "Meio"*/
        else if(i.dir == null){
            i = i.esq;
        }
        else if(i.esq == null){
            i = i.dir;
        } 
        else{
            i.esq = maiorEsq(i, i.esq);       // FIX 3: o retorno é a nova subárvore ESQUERDA
        }

        return i;
    }
    private No maiorEsq(No i, No j){      //ACHA O MAIOR FILHO DA ESQUERDA
        if(j.dir == null){
            i.elemento = j.elemento;
            j = j.esq;
        }
        else {
            j.dir = maiorEsq(i, j.dir); // continua descendo à direita
        }
        return j;
    }

    public void caminharCentral(){
        caminharCentral(raiz);
    }
    private void caminharCentral(No i){
        if(i != null){
            caminharCentral(i.esq);
            System.out.print(i.elemento + " ");
            caminharCentral(i.dir);
        }
    }

    public No rotacionarEsq(No no){
        No noDir = no.dir;
        No noDirEsq = no.dir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;

        no.setAltura();
        noDir.setAltura();
        return noDir;
    } 

    public No rotacionarDir(No no){
        No noEsq = no.esq;
        No noEsqDir = no.esq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        no.setAltura();
        noEsq.setAltura();
        return noEsq;
    } 

    private No balancear(No i){
    if(i == null) return null;           // nada para balancear (pode acontecer no remover)

    i.setAltura();                       // 1º: atualiza a altura (os filhos podem ter mudado)
    int fb = i.calculaFator();           // 2º: só agora calcula o fator, com a altura nova

    if(fb == 2){                         // pesada à DIREITA
        if(i.dir.calculaFator() == -1){  // filho pende para o lado contrário (zigue-zague)
            i.dir = rotacionarDir(i.dir);//   endireita o filho primeiro e GUARDA o retorno
        }
        return rotacionarEsq(i);         // gira o pai para a esquerda e devolve a nova raiz
    }
    else if(fb == -2){                   // pesada à ESQUERDA
        if(i.esq.calculaFator() == 1){   // zigue-zague para o outro lado
            i.esq = rotacionarEsq(i.esq);
        }
        return rotacionarDir(i);         // gira o pai para a direita
    }

    return i;                            // fator -1, 0 ou 1: já está equilibrada
}

}