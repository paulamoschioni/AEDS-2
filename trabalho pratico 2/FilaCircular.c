#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Data{//struct que guarda uma data (ano, mes e dia) */
	int ano;
	int mes;
	int dia;
} Data;


typedef struct Veiculo{//struct que guarda os 15 atributos de um veiculo 
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



Data parseData(char *s){//recebe string no formato AAAA-MM-DD e devolve uma Data preenchida 
	Data d;
	sscanf(s,"%d-%d-%d", &d.ano, &d.mes, &d.dia);
	return d;
}


void formatData(Data d, char *buffer){//escreve a data no buffer
	sprintf(buffer, "%02d/%02d/%d", d.dia, d.mes, d.ano);
}


void parseCombustivel(char *linha, char combustivel[2][50]){//separa combustivel por ; e guarda ate 2 combustiveis. Se so tiver 1, o 2 fica vazio 
    char *tok = strtok(linha, ";");
    sprintf(combustivel[0], "%s", tok);                     
    tok = strtok(NULL, ";");
    if(tok!=NULL) sprintf(combustivel[1], "%s", tok);      
    else combustivel[1][0] = '\0';                          
}

/** Recebe uma linha do csv, separa os 15 campos pela ',' e devolve um novo Veiculo alocado */
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
	sprintf(v->marca, "%s", info[1]);           
	sprintf(v->modelo, "%s", info[2]);          
	v->ano = atoi(info[3]);
	sprintf(v->categoria, "%s", info[4]);       
	parseCombustivel(info[5], v->combustivel);
	v->cilindros = atoi(info[6]);
	v->cilindrada = atof(info[7]);
	sprintf(v->transmissao, "%s", info[8]);     
	sprintf(v->tracao, "%s", info[9]);          
    v->consumoCidade = atof(info[10]);
    v->consumoEstrada = atof(info[11]);
    v->co2 = atof(info[12]);
    if(strcmp(info[13], "true")==0) v->turbo =1;
    else if(strcmp(info[13],"false")==0) v->turbo=0;
    v->dataRegistro = parseData(info[14]);

	return v;
}

/** Monta no buffer a string de saida do veiculo, no formato pedido pelo enunciado */
void formatVeiculo(Veiculo v, char *buffer){
    char turbo[10];
    char *bufferData = (char*)malloc(20*sizeof(char));
    char bufferCombustivel[110];

    if(v.combustivel[1][0]!='\0'){                                                         
    sprintf(bufferCombustivel, "%s,%s", v.combustivel[0], v.combustivel[1]);
	}  else  {
    sprintf(bufferCombustivel, "%s", v.combustivel[0]);
	}

    if(v.turbo==1) sprintf(turbo, "%s", "true");            
    else if(v.turbo==0) sprintf(turbo, "%s", "false");      
    formatData(v.dataRegistro, bufferData);
    sprintf(buffer, "[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1lf ## %s ## %s ## %.2lf ## %.2lf ## %.1lf ## %s ## %s]\n",v.id,v.marca,v.modelo,v.ano,v.categoria,bufferCombustivel,v.cilindros,v.cilindrada,v.transmissao,v.tracao,v.consumoCidade,v.consumoEstrada,v.co2,turbo, bufferData);
    free(bufferData);
}




Veiculo* lerCsv(char *caminhoArquivo, int *n){//le o csv, cria um Veiculo para cada linha e devolve o vetor. Em *n volta a quantidade de veiculos lidos
	FILE *arq = fopen(caminhoArquivo, "r");
	if(arq == NULL){ //conferir se o arquivo abriu
	printf("Erro ao abrir o arquivo\n");
	exit(1);
	}

	Veiculo *dados = malloc((*n) * sizeof(Veiculo));
	char linha[300];
	int cont = 0;

	fgets(linha, sizeof(linha), arq);                   

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




int BuscaSequencial(int entrada, Veiculo *dados, int n){//percorre os n veiculos do vetor e devolve a posicao do que tem o id procurado, ou -1 se nao encontrar 
    int pos = -1;
    for(int i=0; i<n; i++)                                  
		if(entrada == dados[i].id)
        {
        pos = i;
        i=n;                                            
        }

    return pos;
}

typedef struct FilaCircular{// Fila circular com alocacao sequencial: array de Veiculos com indices de primeiro e ultimo
	int ultimo;
	int primeiro;
	Veiculo *array;
}Fila;


void FilaCircular(Fila *fila, int tam){ //aloca o array da fila com tam posicoes e deixa a fila vazia 
	fila->array = malloc(tam * sizeof(Veiculo));		
	fila->primeiro = 0;
	fila->ultimo = 0;
}


Veiculo R(Fila *fila, int tam){		//remove e devolve o primeiro veiculo da fila
	Veiculo temp;
	//fila vazia
	if(fila->primeiro == fila->ultimo){
		temp.id = -1;					
		return temp;
	}

	//guardando removido
	temp = fila->array[fila->primeiro];

	//retirando primeiro
	fila->primeiro = (fila->primeiro + 1) % tam;
	return temp;
}


void I(Fila *fila, int tam, Veiculo veiculo){//insere um veiculo no fim da fila. Se a fila cheia, remove o primeiro antes e mostra quem saiu 
	//fila esta cheia?
	if(((fila->ultimo + 1) % tam) == fila->primeiro){
		Veiculo removido = R(fila, tam);		
		printf("(R)%s %s\n", removido.marca, removido.modelo);
	}

	//adicionar na ultima posicao e atualizar
	fila->array[fila->ultimo] = veiculo;
	fila->ultimo = (fila->ultimo + 1) % tam;
}


void mostrar(Fila *fila, int tam){		//mostra todos os veiculos da fila, do primeiro ao ultimo 	
	char *buffer = (char*)malloc(300*sizeof(char));		
	int atual = fila->primeiro;
	while(atual != fila->ultimo){
		formatVeiculo(fila->array[atual], buffer);
		printf("%s", buffer);				
		atual = (atual + 1) % tam;			
	}
	free(buffer);
}

int main(){
	int qnt = 500;
	char caminhoArquivo[50];
	sprintf(caminhoArquivo, "%s", "/tmp/veiculos.csv");	
	Veiculo *dados = lerCsv(caminhoArquivo, &qnt);		

	int tam = 6;		//6 posicoes (uma fica sempre vazia)
	Fila fila;
	FilaCircular(&fila, tam);

	//1a parte: ids ate -1
	int entrada;
	while(scanf("%d", &entrada) == 1 && entrada != -1){	
		int pos = BuscaSequencial(entrada, dados, qnt);	
		if(pos != -1) I(&fila, tam, dados[pos]);
	}

	//N operacoes
	int N;
	scanf("%d", &N);
	char linha[50];
	for(int i = 0; i < N; i++){
		scanf(" %[^\r\n]", linha);				
		char *tok = strtok(linha, " ");

		if(strcmp(tok, "R") == 0){
			Veiculo removido = R(&fila, tam);
			if(removido.id != -1)				
				printf("(R)%s %s\n", removido.marca, removido.modelo);
		}
		else if(strcmp(tok, "I") == 0){
			tok = strtok(NULL, " ");
			entrada = atoi(tok);
			int pos = BuscaSequencial(entrada, dados, qnt);	
			if(pos != -1) I(&fila, tam, dados[pos]);
		}
	}

	mostrar(&fila, tam);

	free(fila.array);						
	free(dados);
	return 0;
}									