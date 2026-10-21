
import java.util.Scanner;
/*Paula Moschioni 
20/10/2026
Lista de matrizes com estruturas mistas. 
1- diagonal da matriz vira uma lista dupla 
*/


class Celula{
    Celula dir, esq, sup, inf;
    int elemento;
    
    public Celula (){
        this(0);
    }

    public Celula(int elem){
        elemento = elem;
        dir = esq = sup = inf = null;       //comeca zerando todos os ponteiros
    }
}

class CelulaLista{
    CelulaLista ant, prox;
    int elemento;

    public CelulaLista(){
        this(0);   //para criar celula cabeca
    }

    public CelulaLista(int x){
        ant = prox = null;
        elemento = x;
    }
}

class ListaDupla{
    CelulaLista primeiro, ultimo;

    public ListaDupla(){
        //cria lista com celula cabeca
        CelulaLista nova = new CelulaLista();
        primeiro = nova;
        ultimo = nova;
    }

    public void inserirFim(int x){
        CelulaLista nova = new CelulaLista(x);
        ultimo.prox = nova;
        nova.ant = ultimo;
        ultimo = nova;
    }
    public void mostrarLista(){
        CelulaLista atual = primeiro.prox;
        while(atual != null){
            System.out.print(atual.elemento + " ");
            atual = atual.prox;
        }
        
        System.out.println();
    }
}

class Matriz{
     Celula inicio;
     int linha, coluna; //quantas linhas e quantas colunas

    public Matriz(int lin, int col){
        linha = lin;
        coluna = col;
        //construir primeira linha
        Celula primeira = new Celula();
        inicio = primeira;
        Celula anterior = primeira;

        for(int i = 1; i < col; i++){
            //criar nova celula da linha
            Celula nova = new Celula();

            nova.esq = anterior;
            anterior.dir = nova;
            
            anterior = nova;
        }

        //construir demais linhas
        Celula anter, cima = inicio;
        for(int j = 0; j < linha - 1; j++){
            Celula inicioLinha = new Celula();  //cria a primeira da linha sempre
            inicioLinha.sup = cima;             //linka em cima
            cima.inf = inicioLinha;             //linka cima
            anter = inicioLinha;                //define anterior
            cima = cima.dir;                    //cima vira a prox celula;

            for(int k = 1; k < coluna; k++){
                Celula nova = new Celula();     //cria celula

                //linka linha atual
                nova.esq = anter;               
                nova.sup = cima;
                anter.dir = nova;

                //linka linha de cima 
                cima.inf = nova;

                cima = cima.dir;
                anter = nova;
            }

            //atualiza ponteiros
            cima = inicioLinha;   //atualiza o ponteiro de cima para a primeira celula dessa linha e passa para a proxima
        }
    }

    public void mostrar() {
    Celula inicioLinha = inicio;                 // ← lembra onde a linha atual começa

        // imprime linha por linha
        for (int i = 0; i < linha; i++) {
            System.out.println("Linha " + i);
            Celula atual = inicioLinha;              // ← atual começa no início DESTA linha

            for (int j = 0; j < coluna; j++) {
                System.out.print(atual.elemento + " ");  // imprime a célula
                atual = atual.dir;                       // anda uma casa para a direita
            }
        System.out.println();                    // ← pula linha no fim de cada linha da matriz

        inicioLinha = inicioLinha.inf;           // ← desce para o início da próxima linha
    }
    }  

    public void diagonalPrincipal(ListaDupla lista){
        Celula atual = inicio;

        while(atual != null){
            //insere as celulas da diagonal na lista
            lista.inserirFim(atual.elemento);

            //atualiza inf e dir
            atual = atual.inf;
            if(atual != null){
                atual = atual.dir;
            }
        }
    }
}

class diagonalListaDupla{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

    
        Matriz matriz = new Matriz(4, 4);       //cria matriz 4x4 zerada
        int x;
        //preenche a matriz linha por linha
        Celula inicioLinha = matriz.inicio;
        System.out.println("Digite os elementos da matriz (4x4): ");
        for(int i = 0; i < matriz.linha; i++){
            Celula atual = inicioLinha;
            for(int j = 0; j < matriz.coluna; j++){
                x = sc.nextInt();               //le o elemento
                atual.elemento = x;             //coloca na celula
                atual = atual.dir;              //anda para a direita
            }
            inicioLinha = inicioLinha.inf;      //desce para a proxima linha
        }

        ListaDupla lista = new ListaDupla();    //cria lista com cabeca
        matriz.diagonalPrincipal(lista);
        
        System.out.println("Imprimindo diagonal LISTA DUPLA");
        lista.mostrarLista();

        sc.close();
    }
}