/*PAULA MOSCHIONI 
21/10/2026
Matriz de lista: cada celula da matriz é uma lista. Remova os impares
 */
class Lista {
    No primeiro, ultimo;   // primeiro = célula cabeça

    // remove todos os ímpares desta lista
    void removerImpares() {
        No anterior = primeiro;                    // dedo começa na CABEÇA (um antes do 1º elemento)

        while (anterior.prox != null) {            // enquanto existe alguém depois do dedo
            if (anterior.prox.elemento % 2 != 0) { // o próximo é ímpar?
                No tmp = anterior.prox;            // segura quem vai sair
                anterior.prox = tmp.prox;          // "pula" ele: liga o anterior no seguinte
                if (tmp == ultimo) {               // se quem saiu era o último...
                    ultimo = anterior;             // ...o anterior vira o último
                }
                tmp.prox = null;                   // desliga o nó removido
                // NÃO anda: o novo anterior.prox ainda não foi testado
            } else {
                anterior = anterior.prox;          // é par: fica na lista, o dedo anda
            }
        }
    }
}

class Matriz {
    CelulaM inicio;
    int linha, coluna;

    void removerImpares() {
        CelulaM primeiraCelula = inicio;           // primeira célula da linha (começa em (0,0))

        for (int i = 0; i < linha; i++) {
            CelulaM atual = primeiraCelula;        // sempre começa na primeira da linha i

            while (atual != null) {
                atual.lista.removerImpares();      // a própria lista cuida de si mesma
                atual = atual.dir;
            }

            primeiraCelula = primeiraCelula.inf;   // desce para a próxima linha
        }
    }
}