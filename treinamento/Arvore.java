import java.util.Scanner;
/* Paula Moschioni 
15/10/2026
Código teste sobre árvores. Contém: inserir árvore, mostrar árvore, 
contar quantos nós tem na árvore e somar os elementos da árvore*/

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
        
        sc.close();
    }
}