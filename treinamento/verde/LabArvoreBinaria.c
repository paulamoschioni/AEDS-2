import java.util.Scanner;
class No{
    No esq, dir;
    int elemento;

    public No(){
        this(0);
    }

    public No(int x){
        esq = dir = null;
        elemento = x;
    }
}

class ArvoreBinaria{
        public No raiz;

        public ArvoreBinaria(){
            raiz = null;
        }

        public ArvoreBinaria(int x){
            No no = new No(x);
            raiz = no;
        }

        // INSERIR: "I"
        public void inserir(int x){
            raiz = inserir(x, raiz);
        }

        public No inserir(int x, No i){
            //caso base 
            if(i == null){
                return new No(x);
            }
            if(x < i.elemento){
                i.esq = inserir(x, i.esq);
            }
            else if(x > i.elemento){
                i.dir = inserir(x, i.dir);
            }

            return i;
        }

        //PESQUISAR : "P"
        public boolean pesquisar(int x){
            return pesquisar(x,raiz);
        }
        
        public boolean pesquisar(int x, No i){
            if(i == null){
                return false;
            }
            if(i.elemento == x){
                System.out.print(i.elemento + " ");
                return true;
            }
            else if(x > i.elemento){
                System.out.print(i.elemento + " ");
                return pesquisar(x, i.dir);
            }
            else{
                System.out.print(i.elemento + " ");
                return pesquisar(x, i.esq);
            }

        }


        //CAMINHARES
        private boolean primeiro; // controla o espaco entre elementos (sem espaco sobrando no fim)

        private void imprimir(int x){
            if(!primeiro) System.out.print(" ");
            System.out.print(x);
            primeiro = false;
        }

        public void caminharPre(){
            primeiro = true;
            caminharPre(raiz);
        }

        public void caminharPre(No i){
            if(i != null){
            imprimir(i.elemento);
            caminharPre(i.esq);
            caminharPre(i.dir);
            }
        }


        public void caminharPos(){
            primeiro = true;
            caminharPos(raiz);
        }

        public void caminharPos(No i){
            if(i != null){
                caminharPos(i.esq);
                caminharPos(i.dir);
                imprimir(i.elemento);
            }
        }


        public void caminharEm(){
            primeiro = true;
            caminharEm(raiz);
        }

        public void caminharEm(No i){
            if(i != null){
                caminharEm(i.esq);
                imprimir(i.elemento);
                caminharEm(i.dir);
            }
        }
}

class LabArvoreBinaria {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //instancia arvore
        ArvoreBinaria arvore = new ArvoreBinaria(); //sem elementos por enquanto


        //entrada de dados
        String comando;
        while(sc.hasNext()){
            comando = sc.next();
            if(comando.equals("I")){
                int x = sc.nextInt();
                arvore.inserir(x);
            } 
            else if(comando.equals("POS")) {
                if(arvore.raiz == null)     System.out.println("V");
                else{
                    arvore.caminharPos();
                    System.out.println();
                }

            }
             else if(comando.equals("PRE")) {
                if(arvore.raiz == null)     System.out.println("V");
                else{
                    arvore.caminharPre();
                    System.out.println();
                }

            }
             else if(comando.equals("EM")) {
                if(arvore.raiz == null)     System.out.println("V");
                else{
                    arvore.caminharEm();
                    System.out.println();
                }

            }
             else {
                int y = sc.nextInt();
                boolean resultado = arvore.pesquisar(y);

                if(resultado) System.out.println("S");
                else System.out.println("N");

            }
        }
        sc.close();
    }
}
