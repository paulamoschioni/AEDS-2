import java.util.Scanner;
class Fila{
	int n;
	int capacidade;
	String[] array;

        public Fila(int capacidade){
            this.capacidade = capacidade;
            array = new String[capacidade];
            n = 0;
        }

        public void inserirFim(String nome){
            array[n] = nome; //adiciona a pessoa
            n++;
            
        }
        public void removerInicio(){
            for(int i = 1; i < n - 1; i++){  //desloca td para esquerda
                array[i - 1] = array[i];
                n--;
            }
        }
        public void mostrar(Fila fila){
            for(int i = 0; i < n; i++){
            System.out.println(array[i]);
            }
            
        }
    }


    class Main{
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            
            //criando variavies para leitura
            String nome, comando;
            int C; //C lugares
            int N;
                
            //cria for eof
            while(sc.hasNextInt()){
            
                N = sc.nextInt();
                Fila fila = new Fila(N);	
                for(int i = 0; i < N; i++){
                    comando = sc.next();  //le o comando
                    
                    if(comando.equals("CHEGA")){
                        nome = sc.next();  //le o nome de quem chegou
                        fila.inserirFim(nome);
                    } else {

                        C = sc.nextInt();  //le qtde de pessoas e qtde de remocoes
                        for(int c = 0; c < C; c++){
                            fila.removerInicio();
                        }

                        fila.mostrar(fila); 
                    }
                }
            }	
        sc.close();}

    }
