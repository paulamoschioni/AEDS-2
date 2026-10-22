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
        int q = 0;
        int alvo = 0;
        if(k == 1){
            alvo = i.elemento;
        } else {
            
        }
        return alvo;
    }
}