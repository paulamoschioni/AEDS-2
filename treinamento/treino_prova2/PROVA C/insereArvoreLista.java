class Dicionario {
    private No raiz;

    public void inserir(String palavra) {
        raiz = inserir(palavra, raiz, palavra.charAt(0));
    }
    private No inserir(String nome, No i, char l){
        //caso base: tem que criar o nó com a letra l
        if(i == null){
            i = new No();
            i.letra = l;
            Celula nova = new Celula();
            nova.palavra = nome;
            i.primeiro = nova;
            i.ultimo = nova;

        }  else if(l == i.letra) {
            indiceInsercao(i, nome);

        } else if(l > i.letra){
            i.dir = inserir(nome, i.dir, l);

        } else {
            i.esq = inserir(nome, i.esq, l);

        }
        return i;
    }

    public void indiceInsercao(No i, String nome){
        //tem 3 casos: inicio, meio e fim
        Celula nova = new Celula(nome);
        //INICIO:
        if((i.primeiro.palavra).compareTo(nome) > 0){
            nova.prox = i.primeiro;
            i.primeiro = nova;
        } else if((i.ultimo.palavra).compareTo(nome) < 0){
        //FINAL 
            i.ultimo.prox = nova;
            i.ultimo = nova;
        } else {
        //MEIO
            Celula atual = i.primeiro;
            while(atual != i.ultimo){
                if(((atual.palavra).compareTo(nome) < 0) && ((atual.prox.palavra).compareTo(nome) > 0)){
                    nova.prox = atual.prox;
                    atual.prox = nova;
                }
                atual = atual.prox;
            }
        }
    }
}