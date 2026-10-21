package matriz_lista;
import java.util.Scanner;
/*Paula Moschioni 
19/10/2026
Material de estudos de matrizes. Os metodos contidos sobre matriz são: construtor, mostrar, mostrar diagonal principal, mostrar diagonal secundária, imprimir matriz inversa, 
get na posicao (i,j), imprimir em formato de cobrinha, imprimir matriz transposta, 
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
    
    public int get(int i, int j){
        //esta fora da matriz?
        if(i < 0 || j < 0 || j > coluna || i > linha){
            System.out.println("erro! posicao fora da matriz");
        }

        //andar ate encontrar a celula
        Celula c = inicio;
        for(int l = 0; l < i; l++){ //caminha pelas linhas
            c = c.inf;
        }
        for(int k = 0; k < j; k++){ //caminha pelas colunas
            c = c.dir;
        }
        return c.elemento;
    }

    public void imprimeDPrincipal(){
        //matriz é quadrada??
        if(linha != coluna){
            System.out.println("Matriz nao é quadrada");
        }

        Celula atual = inicio;
        while(atual != null){
            System.out.print(atual.elemento + "");

            //atualiza os ponteiros
            atual = atual.inf;                       // ← desce um degrau
            if (atual != null) {                     // ← só anda se não caiu fora da matriz
                atual = atual.dir;
            }
       }
       System.out.println();
    }

    public void imprimeDSecundaria(){
        //matriz é quadrada??
        if(linha != coluna){
            System.out.println("Matriz nao é quadrada");
        }

        Celula comecoReal = inicio;
        while(comecoReal.dir != null){      //achou a ultima celula da primeira linha
            comecoReal = comecoReal.dir;
        }

        Celula atual = comecoReal;
        while(atual != null){
            System.out.print(atual.elemento + " ");

            atual= atual.inf;
            if(atual != null){
                atual = atual.esq;
            }
        }
        System.out.println();
    }

    public void cobrinha(){
        Celula atual = inicio;

        for(int i = 0; i < linha; i++){
            if(i % 2 == 0){
                while(atual.dir != null){
                    System.out.print(atual.elemento + " ");
                    atual = atual.dir;
                }
                System.out.print(atual.elemento + " "); //imprimir o ultimo elemento
            } else {
                while(atual.esq != null){
                    System.out.print(atual.elemento + " ");
                    atual = atual.esq;
                }
                System.out.print(atual.elemento + " "); //imprimir o ultimo elemento
            }

            //atualiza para a prox linha
            if(atual != null){
                atual = atual.inf;
            }
        }   
    }

    public void imprimirMatrizTransposta(){
        Celula atual;
        Celula proxInicio = inicio;
        for(int i = 0; i < coluna; i++){
            atual = proxInicio;
            
            while(atual != null){
                System.out.print(atual.elemento + " ");
                atual = atual.inf;
            }
            
            proxInicio = proxInicio.dir;
        }
    }

    }


class matrizEncadeada{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);


        sc.close();
    }
}
