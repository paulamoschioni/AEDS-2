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

 void OrdenaCounting(Veiculo v[], int n){

	//porcurar maior elemento
	int maior = v[0].cilindros;
	for(int i = 0; i < n; i++){
	if(v[i].cilindros > maior){
		maior = v[i].cilindros;
	}
	}

	//cria vetor de contagem e adiciona numeros dos cilindros no count
	int count[maior+1];
	for(int l = 0; l <= maior; l++){
	count[l] = 0;
	}

	for(int j = 0; j < n; j++){
	count[v[j].cilindros]++;
	}

	// faz vetor acumulativo
	for(int a = 1; a <= maior; a++){
	count[a] += count[a-1];
	}

	//distribuir nas posicoes (LEMBRA DO -1)
	Veiculo ordenado[n];
	for(int h = (n-1); h >= 0; h--){
	ordenado[count[v[h].cilindros] - 1] = v[h];    //percorre o VETOR original de tras para frente e acessa, pelo valor que tem dentro, no vetor cout
	count[v[h].cilindros]--;			//subtrai um de count
	}

	//copia ordenado para vetor v original
	for(int p = 0; p < n; p++){
	v[p] = ordenado[p];
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
	OrdenaCouting(carro, qtde);

    for(int k = 0; k < qtde; k++){
		formatVeiculo(carro[k], buffer);
		printf("%s\n",buffer);
	}

	free(buffer);
	return 0;
}
