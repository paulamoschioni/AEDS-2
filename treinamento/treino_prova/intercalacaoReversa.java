/*Complexidade: O(n) 
Paula Moschioni 
25/10/2026            
*/
class CelulaDupla {
    int elemento;
    CelulaDupla prox, ant;
}

CelulaDupla intercalarReverso(CelulaDupla c1, CelulaDupla c2) {
    CelulaDupla i = c1.prox;
    CelulaDupla j = c2.prox;
    CelulaDupla ultimaC1, ultimaC2;
    CelulaDupla c3 = new CelulaDupla();     //cria celula cabeca chamada c3

    while(i.prox != null){  //para encontrar a ultima da C1
        ultimaC1 = i;
        i = i.prox;
    }
    ultimaC1 = i;

    while(j.prox != null){
        ultimaC2 = j;
        j = j.prox;
    }
    ultimaC2 = j;

    CelulaDupla atual = c3;

    while(ultimaC1 != c1 || ultimaC2 != c2){
        //insere na lista nova primeiro C1 e dps o C2
        if(ultimaC1 != null){
        CelulaDupla nova = new CelulaDupla();
        nova.elemento = ultimaC1.elemento;

        atual.prox = nova;
        nova.prox = null;
        nova.ant = atual;

        ultimaC1 = ultimaC1.ant;            //atualiza o elem a ser inserido
        }
        
        if(ultimaC2 != null){
            CelulaDupla nov = new CelulaDupla();
            nov.elemento = ultimaC2.elemento;

            atual.prox = nov;
            nov.prox = null;
            nov.ant = atual;

            ultimaC2 = ultimaC2.ant;
        }

        atual = atual.prox;
    }
    return c3;
}