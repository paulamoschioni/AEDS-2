#include <stdio.h>
typedef struct {
    char nome[50];
    int h, m, s;
    int total;      // tempo em segundos, pra comparar
} Corredor;

void calculaTotal(Corredor cor[], int qtde){
	//transfomar tudo para segundo
	int acumula = 0;
	acumula = (cor[qtde].h * 3600) + (cor[qtde].m * 60) + cor[qtde].s;
	
	cor[qtde].total = acumula;
}

void ordenaInsertion(Corredor c[], int n){
	//ordenar por c[j].qtde
	for(int i = 1; i < n; i++){
	 	Corredor chave = c[i];
		int j = i - 1;
		while(j >= 0 && (c[j].total > chave.total)){
			c[j+1] = c[j];
			j--;
		}
		c[j+1] = chave;
	}
}

int main(){
	//cria array de corredores
	Corredor cor[200];
	
	//variaveis
	int qtde = 0;
	
	//roda ate EOF para formar vetor de corredores completo
	while((scanf("%s%d%d%d",cor[qtde].nome, &cor[qtde].h, &cor[qtde].m , &cor[qtde].s)) == 4){
		//calcula total de cada qtde
		calculaTotal(cor, qtde);
	qtde++;
	}

	//ordenar por total insertion 
	ordenaInsertion(cor, qtde);
	//aqui, como é estável, ja conserva a ordem alfabetica, ent é so imprimir
	for(int i = 0; i < qtde; i++){
		printf("%s %d %d %d\n", cor[i].nome, cor[i].h, cor[i].m, cor[i].s);
	}

	return 0;
}
