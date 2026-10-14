/* Funcao que constroi uma pilha partir de uma fila(utilizacao de fila circular com estrutura flexível)
Paula Moschioni
14/10/2026
 */
class PilhaComFila {
    private Fila fila = new Fila();   // os dados moram dentro da fila
    private int n = 0;                // quantos elementos a pilha tem

    public void inserir(int x) {
        fila.inserir(x);              // entra pelo fim da fila
        n++;                          // um elemento a mais
    }

    public int remover() throws Exception {
        if (n == 0) {
            throw new Exception("Erro: pilha vazia!");
        }
        for (int i = 0; i < n - 1; i++) {   // gira n − 1 vezes...
            fila.inserir(fila.remover());   // ...tira da frente e põe no fim (um giro)
        }
        n--;                                // um elemento a menos
        return fila.remover();              // agora o último que entrou está na frente
    }
}