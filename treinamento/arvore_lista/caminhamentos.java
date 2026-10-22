import java.util.Scanner;
class No{
    No esq, dir;
    int elemento;

    public No(int x){
        esq = dir = null;
        elemento = x;
    }
}
class Arvore {
    No raiz;

    /*---- INSERIR ---- */
    public void inserir(int x){
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i){
        if(i == null){
            return new No(x);
        }
        else if(x > i.elemento){
            i.dir = inserir(x, i.dir);
        }
        else {
            i.esq = inserir(x, i.esq);
        }
        return i;
    }

    /* 01- ALTURA */
    public int altura(){
        return altura(raiz);
    }

    public int altura(No i){
        if(i == null){
            return -1;
        }
        int alturaEsq = altura(i.esq);
        int alturaDir = altura(i.dir);

        int maior;
        if(alturaDir > alturaEsq) maior = alturaDir;
        else maior = alturaEsq;
        return maior + 1;
    }

    /* 02- SOMA DOS ELEMENTOS DA ÁRVORE */
    public int somaElementos(){
        return somaElementos(raiz);
    }

    public int somaElementos(No i){
        //caso base
        if(i == null){
            return 0;
        }
        int somaEsq = somaElementos(i.esq);    //soma todos os elementos DA ESQUERDA   
        int somaDir = somaElementos(i.dir);    //soma todos da direita

        return i.elemento + somaEsq + somaDir;
    }

    /* 03- SOMA DOS ELEMENTOS PARES */
    public int somaPares(){
        return somaPares(raiz);
    }
    public int somaPares(No i){
        if(i == null){
            return 0;
        }
        int somaEsq, somaDir;
        somaDir = somaPares(i.dir);     //so guarda
        somaEsq = somaPares(i.esq);     //so guarda

        //executa na volta de CADA nó
        if(i.elemento % 2 == 0){
            return i.elemento + somaDir + somaEsq;      //entra na contagem
        }
        else{   
            return somaDir + somaEsq;                   //só repassa o resultado                                                
        }
    }
    
    /*04- METODO QUE RETORNA TRUE SE ALGUM FOR DIVISÍVEL POR 11 */
    public boolean divisivelPor11(){
        return divisivelPor11(raiz);
    }

    public boolean divisivelPor11(No i){
        if(i == null){
            return false;
        }
        boolean dEsq = divisivelPor11(i.esq);
        boolean dDir = divisivelPor11(i.dir);

        boolean tem = false;
        if(i.elemento % 11 == 0){
            tem = true;
        }

        return tem;        
    }

    /*05-NÚMERO DE NÓS FOLHAS */
    public int contaFolhas(){
        return contaFolhas(raiz);
    }
    public int contaFolhas(No i){
        //caso base 
        if(i == null){
            return 0;
        }
        //chama todos esq e dir
        int somaEsq = contaFolhas(i.esq);
        int somaDir = contaFolhas(i.dir);

        if(i.esq == null && i.dir == null){
            return 1 + somaEsq + somaDir;
        }
        else {
            return somaEsq + somaDir;
        }
    }

}
