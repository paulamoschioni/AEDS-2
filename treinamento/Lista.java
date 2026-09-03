
/*Objetivo: criar um codigo de LISTA: 
METODOS: InserirInicio, InserirFim, Inserir, RemoverInicio, RemoverFim, Remover */
class Lista
{   
    private int[] array;
    private int n;

    Lista(){
        this(6);  //cria um array com 6 posicoes
    }

    Lista(int tam){
        int[] array = new int[tam];     //cria com o tamanho desejado
        n = 0;                          //lista e 0
    }


    public void InserirInicio(int chave) throws Exception {
        
        if(n >= array.length) {
            throw new Exception("Erro, lista cheia");
        }

        for (int i = n; i > 0; i--){
            array[i] = array[i-1];              //troca com o anterior
        }

        array[0] = chave;//abre espaco para o primeiro
        n++;
    }


    public void InserirFim(int chave) throws Exception{
        if(n >= array.length) {
            throw new Exception("Erro, lista cheia");
        }

        array[n] = chave;
        n++;
    }

    public void Inserir(int chave, int pos) throws Exception{
        if(n >= array.length || pos < 0 || pos > n){
            throw new Exception("Erro, lista cheia");
        }

        for(int i = n; i > pos; i++){
            array[i] = array[i-1];
        }

        array[pos] = chave;
        n++;
    }

    public int RemoveInicio() throws Exception{
        if(n == 0){
            throw new Exception("Erro, lista cheia");
        }

        int resp = array[0];  //guardando valor removido
        for(int i = 0; i < n; i++){
            array[i] = array[i+1];
        }

        n--;
        return resp;
    }

    public int RemoveFim() throws Exception{
        if(n == 0){
            throw new Exception("Erro, lista cheia");
        }
 
        int resp = array[n-1];

        array[n-1] = 0;
        n--;
        return resp;
    }

    public int Remove(int pos) throws Exception{
        if(n == 0 || pos < 0|| pos > n){
            throw new Exception("Erro, lista cheia");
        }
        int resp = array[pos];
        for(int i = ;pos i > n; i++){
            array[i] = array[i+1];
        }
        n--;
        return resp;
    }

 sc.close(); 
}