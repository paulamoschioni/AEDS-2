#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Data{
	int ano;
	int mes;
	int dia;
} Data;

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
	int turbo;
	Data dataRegistro;

} Veiculo;

Data parseData(char *s){
	Data d;
	sscanf(s,"%d-%d-%d", &d.ano, &d.mes, &d.dia);
	return d;
}

void formatData(Data d, char *buffer){
	sprintf(buffer, "%02d/%02d/%d", d.dia, d.mes, d.ano);
}

int BuscaSequencial(int entrada, Veiculo *dados){
    int pos = -1;
    for(int i=0; i<500; i++)
		if(entrada == dados[i].id)
        {
            pos = i;
            i=500;
        }

    return pos;
}

Veiculo* lerCsv(char *caminhoArquivo, int *n){
	FILE *arq = fopen(caminhoArquivo, "r");

	Veiculo *dados = malloc((*n) * sizeof(Veiculo));
	char linha[300];
	int cont = 0;

	while (fgets(linha, sizeof(linha), arq) != NULL && cont < *n) {
    	Veiculo *v = parseVeiculo(linha);
	dados[cont] = *v;
	free(v);
	cont++;
	}

	fclose(arq);
	*n = cont;
	return dados;
}

void parseCombustivel(char *linha, char combustivel[2][50]){
    char *tok = strtok(linha, ";");
    strcpy(combustivel[0],tok);
    tok = strtok(NULL, ";");
    if(tok!=NULL)strcpy(combustivel[1], tok);
    else strcpy(combustivel[1],"");
}

Veiculo* parseVeiculo(char *s){
    char *info[15]; 
    Veiculo *v = malloc(sizeof(Veiculo));
    char *tok = strtok(s, ",");
    info[0] = tok;

    for(int i=1; i<15; i++){	//for com 15 pos, ja que sao 15 atributos
        tok = strtok(NULL, ",");
        info[i] = tok;
    }
	
	//atribuir aos atributos
	v->id = atoi(info[0]);
	strcpy(v->marca, info[1]);
	strcpy(v->modelo, info[2]);
	v->ano = atoi(info[3]);
	strcpy(v->categoria, info[4]);
	parseCombustivel(info[5], v->combustivel);
	v->cilindros = atoi(info[6]);
	v->cilindrada = atof(info[7]);
	strcpy(v->transmissao, info[8]);
	strcpy(v->tracao, info[9]);
    	v->consumoCidade = atof(info[10]);
    	v->consumoEstrada = atof(info[11]);
    	v->co2 = atof(info[12]);
    	if(strcmp(info[13], "true")==0) v->turbo =1;
    	else if(strcmp(info[13],"false")==0) v->turbo=0;
    	v->dataRegistro = parseData(info[14]);

	return v;
}

void formatVeiculo(Veiculo v, char *buffer){
    char turbo[10];
    char *bufferData = (char*)malloc(20*sizeof(char));
    char bufferCombustivel[110];

    strcpy(bufferCombustivel,v.combustivel[0]);
    if(v.combustivel[1][0]!='\0'){
        strcat(bufferCombustivel,",");
        strcat(bufferCombustivel,v.combustivel[1]);
    }

    if(v.turbo==1)strcpy(turbo,"true");
    else if(v.turbo==0) strcpy(turbo,"false");
    formatData(v.dataRegistro, bufferData);
    sprintf(buffer, "[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1lf ## %s ## %s ## %.2lf ## %.2lf ## %.1lf ## %s ## %s]\n",v.id,v.marca,v.modelo,v.ano,v.categoria,bufferCombustivel,v.cilindros,v.cilindrada,v.transmissao,v.tracao,v.consumoCidade,v.consumoEstrada,v.co2,turbo, bufferData);
    free(bufferData);
}

void BucketSort(Veiculo v[], int n){
	//qual o maior e qual o menor
	double maior = v[0].cilindrada;
	double menor = v[0].cilindrada;
	for(int i = 0; i < n; i++){				///define o intervalo maior - menor
	if(maior < v[i].cilindrada) maior = v[i].cilindrada;
	if(menor > v[i].cilindrada) menor = v[i].cilindrada;
	}
	

	double largura = (maior - menor)/(double) 10;	//largura de cada um dos 10 baldes
	Veiculo balde[10][n];				//espaco para 10 baldes com ate 50 veiculos em cada
	int quantidade[10] = {0};			//quantidade de elementos em CADA balde
	for(int j = 0; j < n; j++){
	//qual o indice do balde? 
	int indice = (int) ((v[j].cilindrada - menor)/ largura);
	if (indice == 10) indice = 9;   //se cair em um balde maior
	
	//guarda no balde	
	balde[indice][quantidade[indice]];
	quantidade[indice]++;				//incrementa elemento na pos do indice
	}
	
	for(int b = 0; b < 10; b++){
	ordenaInsertion(balde[b], quantidade[b]);	//ordena cada balde
	}

}

void ordenaInsertion(Veiculo balde[], int qtde){
	for(int i = 1; i < qtde; i++){
	Veiculo temp = balde[i];
	int j = i - 1;
		while(j >= 0 && (balde[j].cilindrada > temp.cilindrada)){
			balde[j + 1] = balde[j];
        		j--;
		}
	balde[j+1] = temp;
	}
}

int main(){
	int entrada, qnt = 500;
	char caminhoArquivo[50];
    	strcpy(caminhoArquivo,"veiculos.csv");
	Veiculo *dados = lerCsv(caminhoArquivo, &qnt);
	
	int pos;
	int qtde = 0;
	Veiculo carro[500];
	char *buffer = (char*)malloc(300*sizeof(char));
	scanf("%d",&entrada);

	while(entrada != -1){
    	pos = BuscaSequencial(entrada, dados);
		if (pos != -1) {
			carro[qtde] = dados[pos];
			qtde++;
		} else {
			printf("Veiculo nao encontrado\n");
		}
		scanf("%d",&entrada);
   	}
	BucketSort(carro, qtde);
	
	for(int k = 0; k < qtde; k++){
		formatVeiculo(carro[k], buffer);
		printf("%s\n",buffer);
	}
	free(buffer);
	return 0;
}

