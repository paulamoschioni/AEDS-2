public int contar(char c){
    return contar(c, raiz);
}
private int contar(char c, No i){
    //caso base: letra nao existe
    if(i == null){
        return 0;
    }

    if(i.letra == c){
        Celula atual = i.primeiro;
        int num = 0;
        while(atual != null){
            num++;
            atual = atual.prox;
        }
        return num;

    } else if(c > i.letra){
        return contar(c, i.dir);
    } else {
        return contar(c, i.esq);
    }
}


public char letraMaisPop() {
    No vencedor = letraMaisPop(raiz);      

    if (vencedor == null) {                
        return ' ';                       
    }
    return vencedor.letra;                 
}

private No letraMaisPop(No i) {
    if (i == null) {                       
        return null;                      
    }

    No noEsq = letraMaisPop(i.esq);        
    No noDir = letraMaisPop(i.dir);        

    No maior = i;                          

    if (tamanho(noEsq) > tamanho(maior)) { // o campeão da esquerda tem mais palavras que eu?
        maior = noEsq;                     
    }
    if (tamanho(noDir) > tamanho(maior)) { // o campeão da direita tem mais que o campeão atual?
        maior = noDir;                     
    }

    return maior;                          // devolvo para quem me chamou (o meu pai)
}


private int tamanho(No n) {
    if (n == null) {                       
        return 0;                          
    }
    int cont = 0;
    for (Celula c = n.primeiro.prox; c != null; c = c.prox) {  
        cont++;                            
    }
    return cont;
}