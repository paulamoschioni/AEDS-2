#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Paciente{
	char nome[30];
	int p;
}Paciente; 
void Paciente(){
	nome = "-";
	p = -1;
}



typedef struct Fila{
	int n;
	int capacidade;
	Paciente *array;
}Fila;

void Fila(int capacidade, Fila *fila){		//construtor da fila
	fila->array = malloc (sizeof(Paciente) * capacidade);
	fila->capacidade = capacidade;
	fila->n = 0;
}
void inserirFim(Fila *fila, Paciente paciente){
	fila->array[n] = paciente;
	n++;
}
Paciente removerInicio(Fila *fila){
	Pessoa temp = fila->array[0];
	
	for(int i = 0; i < n - 1; i++){
		fila->array[i] = fila->array[i+1];
	}
	
	n--;
	return temp;
}

void chamaPendencias(Paciente *pendencia, int n){ //funcao que zera as pendencias e imprime elas de forma repetida ate acabar todas
	char acabou[n] = {0};	///flag para controlar todas as pendencias de cada posicao
	char ideal[n];
	for(int p = 0; p < n; p++){
		ideal[n] = '1';
	}

	while(strcmp(acabou,ideal)!= 0){
		for(int i = 0; i < n; i++){ //printa se tiverem p > 0
			if(pendencia[i]->p > 0){
				System.out.print(pendencia[i]->nome + " ");
				pendencia[i]->p--;
			} 
			else {
				acabou[i] = '1';
			}	
		}
	}
		

}

int main(){
	//variaveis para leitura
	int N, p, qtde = 0; 
	char nome[30];
	Paciente paciente;

	//ler ate EOF
	while("%d",&N){
		Fila fila;
		Fila(&fila);
		Paciente removido[N]; 
		for(int i = 0; i < N; i++){
			//guarda variaveis do paciente i
			scanf("%s%d", paciente.nome, &paciente.p);

			//manda para inserir na fila
			fila.inserirFim(&fila, paciente);
		}

		//acabou de montar a fila, agora é preciso desenfileirar e armazenar na ordem do vetor
		int r = 0, pend = 0;
		Paciente pendentes[N];
		for(int j = 0; j < N; j++){
			removido[r] = removerInicio(&fila);	//remover o 1 paciente
			r++;
			
			//esses removidos tem pendencias?
			if(removido[r].p > 0){
				pendentes[pend] = removido[r]; //montando array de pacientes que tem pendencias
				pend++;
			}
		}
		
		//agora enviar os pacientes com pendencias para zera-las
		chamaPendencias();





		qtde++;
	}

	free(fila);
	return 0;
}
