import java.util.Scanner;
/*
Paula Moschioni Infante Vieira
10/10/2026
*/
class FilaCircular{
        int primeiro, ultimo;
        int capacidade;
        int[] array;

        public FilaCircular(int cap){
            capacidade = cap;
            array = new int[capacidade + 1];
            primeiro = ultimo = 0;
        }

        public void enfileirar(int elem){
            //overflow?
            if(((ultimo + 1) % array.length) == primeiro){
                System.out.println("overflow");
            }

            array[ultimo] = elem;
            ultimo = (ultimo + 1) % array.length;
        }

        public int desenfileirar(){
            //underflow?
            if(primeiro == ultimo){
                System.out.print("erro");
            }

            int temp = array[primeiro];
            primeiro = (primeiro + 1) % array.length;

            return temp;
        }

        public void mostrar(){
            for(int i = primeiro; i != ultimo; i = ((i+1) % array.length)){
                System.out.println(array[i]);
            }
        }

        public static boolean pesquisa(int chave){
            boolean found = false;

            for(int j = primeiro; j != ultimo; j = ((j+1)% array.length)){
                if(array[j] == chave) found = true;
            }

            return found;
        }




}

