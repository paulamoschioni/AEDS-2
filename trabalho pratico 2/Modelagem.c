#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int BuscaSequencial(int entrada, Veiculo dados){
    int pos;
    for(int i=0; i<500; i++)
		if(entrada == dados[i].id)
        {
            pos = i;
            i=500;
        }

    return pos;
}

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
	sscanf(s,"%d-%d-%d", &d.ano, &d.mes, &d.dia);
	return d;
}

void formatData(Data d, char *buffer){
	sprintf(buffer, "%02d/%02d/%d", d.dia, d.mes, d.ano);
}


typedef struct Veiculo{
	int id;
	char marca[100];
	char modelo[100];
	int ano;
	char categoria[100];
	char combustivel[2][50];
	int cilindros;
	double cilindrada;
	char transmissao[100];
	char tracao[60];
	double consumoCidade;
	double consumoEstrada;
	double co2;
	bool turbo;
	Data dataRegistro;

} Veiculo;

void parseCombustivel(char *linha, char combustivel[2][100]){
    char *tok = strtok(linha, ";");
    strcpy(combustivel[0],tok);
    tok = strtok(NULL, ";");
    if(tok!=NULL)strcpy(combustivel[1], tok);
    else strcpy(combustivel[1],"");
}

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
	v->id = atoi(info[0]);
	strcpy(v->marca, info[1]);
	strcpy(v->modelo, info[2]);
	v->ano = atoi(info[3]);
	strcpy(v->categoria, info[4]);
	//combustivel
	v->cilindros = atoi(info[6]);
	v->cilindrada = atod(info[0]);
	strcpy(v->transmissao, info[8]);
	strcpy(v->tracao, info[9]);
    v->consumoCidade = atof(info[10]);
    v>consumoEstrada = atof(info[11]);
    v->co2 = atof(info[12]);
    if(strcmp(info[13], "true")==0) v->turbo =1;
    else if(strcmp(info[13],"false")==0) v->turbo=0;
    v->dataRegistro = parseData(info[14]);

	return v;
	free(v);
}

void formatVeiculo(Veiculo v, char *buffer){
    char turbo[10];
    char *bufferData = (char*)malloc(20*sizeof(char));
    strcpy(buffer,v.combustivel[0]);
    if(v.combustivel[1][0]!='\0'){
        strcat(buffer,",");
        strcat(buffer,v.combustivel[1]);
    }

    if(v.turbo==1)strcpy(turbo,"true");
    else if(v.turbo==0) strcpy(turbo,"false");
    formatData(v.dataRegistro, bufferData);
    printf("[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1lf ## %s ## %s ## %.2lf ## %.2lf ## %.1lf ## %s ## %s]\n",v.id,v.marca,v.modelo,v.ano,v.categoria,buffer,v.cilindros,v.cilindrada,v.transmissao,v.tracao,v.consumoCidade,v.consumoEstrada,v.co2,turbo, bufferData);
    free(bufferData);
}

int main(){
int entrada, qnt = 500;
	char caminhoArquivo[50];
    strcpy(caminhoArquivo,"veiculos.csv");
	Veiculo *dados = lerCsv(caminhoArquivo, &qnt);

	int pos;
	scanf("%d",&entrada);

	while(entrada != -1){
        pos = BuscaSequencial(entrada, dados);
        char *buffer = (char*)malloc(200*sizeof(char));
            formatVeiculo(dados[i], buffer);
	scanf("%d",&entrada);
    }

free(buffer);
}
