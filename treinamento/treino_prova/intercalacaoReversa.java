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
    CelulaDupla c3 = new CelulaDupla();

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

    while(j != null && i != null){
        1
    }
}