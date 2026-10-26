class abb{
    /* 09- K-ENÉSIMO MENOR DA LISTA */
    public int kMenor(int k){
        return kMenor(k, raiz);
    }
    public int kMenor(int k, No i){
        if(i == null){
            return 0;
        }

        //chama tudo da esquerda
        int esq = kMenor(k, i.esq);

        //pega o k menor
        int alvo = 0;
        if(k == 1){
            alvo = i.elemento;
        } else {
            
        }
        return alvo;
    }

    /* 10- MENOR ANCESTRAL COMUM DE A E B */
    // PORTEIRO: quem usa a árvore chama este método (mesmo kit de sempre)
    public int menorAncestral(int a, int b) {
        return menorAncestral(a, b, raiz);       // começa a descida pela raiz
    }
    private int menorAncestral(int a, int b, No i) {

        // Situação 1: os DOIS são menores que o nó atual
        if(a < i.elemento && b < i.elemento) {
            return menorAncestral(a, b, i.esq);   
        }

     // Situação 2: os DOIS são maiores que o nó atual
        if (a > i.elemento && b > i.elemento) {   
            return menorAncestral(a, b, i.dir);   
        }

        // Situação 3: eles se separaram (ou um deles É este nó)
        return i.elemento;                             
    }

}

