import java.util.Scanner;
/* Paula Moschioni 
15/10/2026
Código teste sobre árvores. Contém: inserir árvore, mostrar árvore, 
contar quantos nós tem na árvore e somar os elementos da árvore, pesquisar na árvore BST, contar pares*/

class No{
    No esq, dir;  //filhos
    int elemento;

    public No(int elem){
        elemento = elem;
        esq = dir = null;
    }

}

class Arvore{
    No raiz;

    public Arvore() {     // sem parâmetro: a árvore nasce sem nenhum elemento
    raiz = null;      // "não aponta para nenhum nó" → árvore vazia
    //NAO CRIA A RAIZ, pq quem cria é o inserir "porteiro"
    }

    /* ---- INSERIR ---- */
    public void inserir(int elem){
        //ATENCAO: esse porteiro nao sabe onde fica o novo nó, ele so "abre as portas"
         raiz = inserir(elem, raiz); //manda o valor do novo no e a raiz
    }   

    private No inserir(int x, No i){
        //caso base
        if(i == null){
            return new No(x);       //cria o novo nó 
        }
        // condicionais
        if(x < i.elemento){
            i.esq = inserir(x, i.esq);   // confia que o inserir(..) JÁ devolve a localizacao correta para o no
        }
        else if(x > i.elemento){
            i.dir = inserir(x, i.dir);  
        }
        else {
            System.out.println("erro!!");
        }

        return i;      
    }

    /* ---- ORDEM ---- */
    public void mostrarEmOrdem(){    //codigo que vem do main
        mostrarEmOrdem(raiz);
    }

    private void mostrarEmOrdem(No i){
        if(i != null){      //so faz alguma coisa se o no existir
             mostrarEmOrdem(i.esq);                //imprime todos da esquerda
             System.out.print(i.elemento + " ");   //imprime o EU(apenas a raiz)
             mostrarEmOrdem(i.dir);                //imprime todos da direita 
        }
    }


    /* -----PRE ORDEM----- */
    public void mostrarEmPre(){    //codigo que vem do main
        mostrarEmPre(raiz);
    }

    private void mostrarEmPre(No i){
        if(i != null){      //so faz alguma coisa se o no existir
            System.out.print(i.elemento + " ");   //imprime o EU ANTES(apenas a raiz)
             mostrarEmPre(i.esq);                //imprime todos da esquerda
             mostrarEmPre(i.dir);                //imprime todos da direita 
        }
    }
    
    /* ---- POS ORDEM ---- */
    public void mostrarEmPos(){    //codigo que vem do main
        mostrarEmPos(raiz);
    }

    private void mostrarEmPos(No i){
        if(i != null){      //so faz alguma coisa se o no existir
             mostrarEmPos(i.esq);                //imprime todos da esquerda
             mostrarEmPos(i.dir);                //imprime todos da direita 
             System.out.print(i.elemento + " ");   //imprime o EU DEPOIS(apenas a raiz)
        }
    }

    /* ---- PESQUISA ---- */
    public boolean pesquisar(int x){
        return pesquisar(raiz, x);
    }

    private boolean pesquisar(No i, int x){
        //caso base
        if(i == null){  //percorreu todos os nos e nao achou
            return false;
        }
        else if(i.elemento == x){
            return true;
        }
        else if(x > i.elemento){    //x é maior, entao vai estar a DIREITA
            return pesquisar(i.dir, x);
        }
        else{   //c é menor, entao vai estar a ESQUERDA
            return pesquisar(i.esq, x);
        }
    }

    /* ----- CONTAR NOS ----- */
    public int contarNos(){
        return contarNos(raiz);       // porteiro: começa pela raiz e devolve a resposta
    }

    private int contarNos(No i){
        //caso base 
        if(i == null){      //chegou no final, dps da folha
            return 0;
        }
        int somaEsq = contarNos(i.esq);     //confia que soma tudo da esquerda
        int somaDir = contarNos(i.dir);     // --    --   --   --  --  direita

        return 1 + somaDir + somaEsq;
    }

    /* ---- SOMAR NOS ---- */
    public int somarNos(){
        return somarNos(raiz);
    } 
    
    private int somarNos(No i){
        //caso base: acabou o lado
        if(i == null){
            return 0;
        }

        int somaEsq = somarNos(i.esq);      //confia que vai somar tudo da esquerda
        int somaDir = somarNos(i.dir);      //confia que vai somar tudo da direita

        return i.elemento + somaEsq + somaDir;
    }

    /* ---- CONTAR PARES ---- */
    public int contarPares(){
        return contarPares(raiz);
    }

    public int contarPares(No i){
        if(i == null){
            return 0;
        }

        //olha so o no atual
        int atual = 0;
        if(i.elemento % 2 == 0){
            atual = 1;
        }

        //a arvore da esquerda tem quantos pares?
        int paresEsq = contarPares(i.esq);
        
        //a arvore da direita tem quantos pares?
        int paresDir = contarPares(i.dir);

        return atual + paresDir + paresEsq;
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Digite a raiz da árvore: ");
        int raiz = sc.nextInt();
        Arvore arvore = new Arvore();

        System.out.println("Digite o numero de elementos que voce deseja inserir: ");
        int num = sc.nextInt();

        System.out.println("Digite os numeros a serem inseridos: ");
        for(int i = 0; i < num; i++){
            int elem = sc.nextInt();
            arvore.inserir(elem);   //aqui chama primeiro a raiz
        }

        System.out.println("Arvore impressa em ORDEM : ");
        arvore.mostrarEmOrdem();    //mostrar em ordem CRESCENTE 
        System.out.println();

        System.out.println("Arvore impressa em PRE-ORDEM : ");
        arvore.mostrarEmPre();
        System.out.println();

        System.out.println("Arvore impressa em POS-ORDEM : ");
        arvore.mostrarEmPos();
        System.out.println();

        System.out.println("Digite o elemento de pesquisa: ");
        int chave = sc.nextInt();
        boolean existe = arvore.pesquisar(chave);
        if(existe) System.out.println("O elemento existe na arvore");
        else System.out.println("O elemento nao existe na arvore ");

        int cont = arvore.contarNos();
        System.out.println("A arvore tem " + cont + "nos");

        int soma = arvore.somarNos();
        System.out.println("A soma dos elementos é: " + soma);

        sc.close();
    }
}