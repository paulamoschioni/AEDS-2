#include <stdio.h>
#include <stblib.h>
#include <string.h>
Veiculo* lerCsv(char *caminhoArquivo, int *n){
	FILE *arq = fopen = (caminhoArquivo, "w");
	
	char linha[300];
	while (fgets(linha, sizeof(linha), arq) != NULL) {
    	Veiculo *v = parseVeiculo(linha);
	

	}

	fclose(arq);
	return v;
}

typedef struct Data{
	int ano;
	int mes;
	int dia;
} Data;


Data parseData(char *s){
	Data d;
	sscanf(s,"%d/%d/%d", &d.dia, &d.mes, &d.ano);
	return d;
}

void formatData(Data d, char *buffer){
	sprintf(buffer, "%02d/%02d/%d", d.dia, d.mes, d.ano);
}


typedef struct Veiculo{
	int id;
	char marca[50];
	char modelo[50];
	int ano;
	char categoria[60];
	char combustivel[2][30];
	int cilindros;
	double cilindrada;
	char transmissao[50];
	char tracao[60];
	double consumoCidade;
	double consumoEstrada;
	double co2;
	bool turbo;
	Data dataRegistro;
	
} Veiculo; 

Veiculo* parseVeiculo(char *s) {
	//cria novo veiculo
	Veiculo *v = malloc (sizeof(Veiculo));
	//separar palavras e coloca-las em um vetor de 15 pos
	char info[15][40], intermed[40];
 	int posToken = 0;
	int i = 0, j = 0, m = 0;;

	while(s[i] != '\n'){		//s acaba com \n
		if(s[i] == ','){
		for(int p = posToken; p < i; p++){
			intermed[m] = s[p];
			m++;
		}
		intermed[m] = '\0';
		strcpy(info[j], intermed);
		j++;
		posToken = i + 1;	//ATUALIZA para a posicao seguinte do ;
		m = 0;
		}
	i++;
	}
	
	// quando esta no pedaco 14, antes de \n
	m = 0;
	for(int r = posToken; r < i; r++){
	 intermed[m] = s[p];
   	 m++;
	}
	intermed[m] = '\0';
	strcpy(info[j], intermed);
	
	//atribuir aos atributos
	v.id = atoi(info[0]);
	strcpy(v.marca, info[1]);
	strcpy(v.modelo, info[2]);
	v.ano = atoi(info[3]);
	strcpy(v.categoria, info[4]);
	//combustivel
	v.cilindros = atoi(info[6]);
	v.cilindrada = atod(info[0]);
	strcpy(v.transmissao, info[8]);
	strcpy(v.tracao, info[9]);
	
	
		



	v.dataRegistro = parseData(info[14]);
	
	return v;
}

void formatVeiculo(Veiculo v, char *buffer){
	char primeiraS[30], segundaS[30];
	strcpy(primeiraS, v.combustivel[1]);
	strcpy(segundaS, v.combustivel[2]);
	printf("[%d ## %s ## %s ## %d ## %s ## [%s %s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %s]",v.id, v.marca, v.modelo, v.ano, v.categoria,primeiraS, segundaS , v.cilindros, v.cilindrada, v.transmissao, v.tracao, v.consumoCidade, v.consumoEstrada, v.co2, v.turbo, v.dataRegistro.format());
}

int main(){
	
}
