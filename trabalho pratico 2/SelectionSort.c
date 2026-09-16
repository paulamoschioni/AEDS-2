#include <stdio.h> 
#include <stdlib.h>
#include <string.h>
void swap(Veiculo v, int i, int menor){
	Veiculo temp = v[i];
	v[i] = v[menor];
	v[menor] = temp;
}

void OrdenaSelecao(Veiculo v[],int n){
	for(int i = 0; i < n - 1; i++){
	int menor = i;
		for(int j = i + 1; j < n; j++){
		if( strcmp(v[j].modelo, v[menor].modelo) < 0 ){	
		menor = j;
		}
		}
	swap(v, i, menor);
	}
	
	char* buffer = (char*) malloc (sizeof(char) * 100);
	for(int p = 0; p < n; p++){
	formatVeiculo(v[p],buffer);
	printf("%s",buffer);
	}

	free(buffer);
}





int main(){
	


	Veiculo carro[50];
	int num, pos = 0, qtde = 0; 
	scanf("%d",&num);

	while(num != -1){
		pos = BuscaSequencial(v,num);
		carro[qtde] = v[pos];
		

	qtde++;
	scanf("%d",&num);
	}
	
	//envia subconjunto carro
	OrdenaSelecao(carro, qtde);
	



return  0;
}
