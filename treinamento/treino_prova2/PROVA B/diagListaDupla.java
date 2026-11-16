public class diagListaDupla {
    CelulaDupla diagUnificada() {
    CelulaMatriz atualM = inicio;
    CelulaDupla inicioLD = null;   // inicializado
    CelulaDupla back = null;       // declarado FORA dos laços → sobrevive entre listas

    while (atualM != null) {
        Celula atualL = atualM.inicio.prox;   

        while (atualL != null) {
            CelulaDupla novaLD = new CelulaDupla(atualL.elemento);

            if (inicioLD == null) {           
                inicioLD = novaLD;
            } else {
                back.prox = novaLD;           
                novaLD.ant = back;            
            }
            back = novaLD;                    

            atualL = atualL.prox;             
        }

        atualM = atualM.inf;                  
        if (atualM != null) atualM = atualM.dir;  
    }
    return inicioLD;
}
}
