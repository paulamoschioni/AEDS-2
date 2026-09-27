import java.util.Scanner;

class ListaLinear{
    static class Lista{
    private int array[];
    private int n; //numero de elementos
    private int capacidade; //tam max

    //construtor
    public Lista(int cap){
        capacidade = cap;
        n = 0;
        array = new int[capacidade];
    }

    //metodos de inserir
    public void inserirInicio(int x){
        //lista cheia?
        if(n == capacidade){
            System.out.println("Erro, OVERFLOW");
            return;
        }

        //desloca
        for(int i = n; i > 0; i--){
                array[i] = array[i - 1];
        }

        array[0] = x;
        n++;
    }

    public void inserirFim(int x){
        //lista cheia?
        if(n == capacidade){
            System.out.println("Erro, OVERFLOW");
        }

        array[n] = x;
        n++;
    }

    public void inserir(int x, int pos){
        //lista cheia?
        if(n == capacidade){
            System.out.println("Erro, OVERFLOW");
        }

        //desloca
        for(int i = n; i > pos; i--){
                array[i] = array[i - 1];
        }

        array[pos] = x;
        n++;
    }

    public int removerInicio(){
        //lista vazia?
        if(n == 0){
            System.out.println("Erro, UNDERFLOW");
            return -1;
        }

        int temp = array[0];
        //desloca
        for(int i = 0; i < n - 1; i++){
                array[i] = array[i + 1];
        }
        n--;
        return temp;
    }

    public int removerFim(){
        //lista vazia?
        if(n == 0){
            System.out.println("Erro, UNDERFLOW");
            return -1;
        }

        int temp = array[n-1];
        
        n--;
        return temp;
    }

    public int remover(int pos){
        //lista vazia?
        if(n == 0){
            System.out.println("Erro, UNDERFLOW");
        }

        int temp = array[pos];
        //desloca
        for(int i = pos; i < n - 1; i++){
                array[i] = array[i + 1];
        }

        n--;
        return temp;
    }

    public void mostrar(){
        for(int i = 0; i < n; i++){
            System.out.println(array[i] + " ");
        }
        System.out.println("\n");
    }

    public boolean pesquisa(int chave){
        boolean is = false;

        for(int i = 0; i < n; i++){
            if(chave == array[i]) is = true;

        }
        return is;
    }
    }   

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //lendo entradas
        System.out.println("Digite a capacidade máxima do array: ");
        int capacidade = sc.nextInt();
         //cria lista
        Lista lista = new Lista(capacidade);

        System.out.println("Digite o numero de operacoes a serem realizadas: ");
        int operacoes = sc.nextInt();

       

        //fazendo as operacoes
        String comando;
        int posicao;
        int elemento;
        int removido;
        for(int i = 0; i < operacoes; i++){
            comando = sc.next();

            if(comando.equals("I")){
                System.out.println("Digite primeiro o elemento e depois o comando: ");
                elemento = sc.nextInt();
                posicao = sc.nextInt();
                 lista.inserir(elemento, posicao);
            }
            else if(comando.equals("II")){
                System.out.println("Digite o elemento a ser inserido: ");
                elemento = sc.nextInt();
                 lista.inserirInicio(elemento);
            }
             else if(comando.equals("IF")){
                System.out.println("Digite o elemento a ser inserido: ");
                elemento = sc.nextInt();
                lista.inserirFim(elemento);
            }
             else if(comando.equals("RI")){
                removido = lista.removerInicio();
                System.out.println("Elemento " + removido + " removido");
            }
            else if(comando.equals("RF")){
                removido = lista.removerFim();
                System.out.println("Elemento " + removido + " removido");
            }
            else if(comando.equals("R")){
                System.out.println("Digite a posicao: ");
                posicao = sc.nextInt();
                removido = lista.remover(posicao);
                System.out.println("Elemento " + removido + " removido");
            }
        }

        lista.mostrar();

        System.out.println("Digite um elemento para consulta: ");
        int elem = sc.nextInt();
        boolean resp = lista.pesquisa(elem);
        System.out.println(resp);
    }
}