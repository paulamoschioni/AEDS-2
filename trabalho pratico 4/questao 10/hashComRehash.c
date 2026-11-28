#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define TAM 83

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


int caseSense(char *a, char *b){ //compara letras maiusculas e minusculas
	int i = 0;
	while(a[i] != '\0' && b[i] != '\0'){
		char ca = a[i];
		char cb = b[i];
		if(ca >= 'A' && ca <= 'Z') ca = ca + 32;	//converte maiuscula em minuscula
		if(cb >= 'A' && cb <= 'Z') cb = cb + 32;
		if(ca != cb) return ca - cb;
		i++;
	}
	return a[i] - b[i];		//uma acabou antes da outra 
}

typedef struct Hash{
    int m;                     // tamanho da tabela
    Veiculo *tabela[TAM];      // vetor de PONTEIROS: NULL = vazio
}Hash;

void inicializa(Hash *h){
    h->m = TAM;                          // tamanho = constante
    for(int i = 0; i < h->m; i++){       // percorre da posição 0 até m-1
        h->tabela[i] = NULL;             // marca cada posição como vazia
    }
}

int transforma(char *modelo){
    int soma = 0;
    for(int i = 0; i < strlen(modelo); i++){
        soma += modelo[i];
    }
    return soma;
}

int hash(Hash *h, char *modelo){	//calcula opcao de primeira via
    return transforma(modelo) % h->m;
}

int rehash(Hash *h, char *modelo){	//calcula opcao de segunda via
    return (transforma(modelo)+1) % h->m;
}

void inserir(Veiculo v, Hash *h){
    int pos = hash(h, v.modelo);

    if(h->tabela[pos] == NULL){	//tentar apenas com hash
		Veiculo *veic = malloc(sizeof(Veiculo));
		*veic = v;
		h->tabela[pos] = veic;
	}
	else{
		pos = rehash(h, v.modelo);

		if(h->tabela[pos] == NULL){     //vai de rehash e ve se da para colocar
			Veiculo *veicu = malloc(sizeof(Veiculo));
			*veicu = v;
			h->tabela[pos] = veicu;
		} 
		else{						//colidiu de novo: nao insere
			printf("%s\n", v.modelo);
		}
	}
}

int pesquisar(Hash *h, char *modelo){	//devolve a posicao do modelo na tabela ou -1
	int pos = hash(h, modelo);
	if(h->tabela[pos] != NULL && strcmp(h->tabela[pos]->modelo, modelo) == 0){
		return pos;
	}

	pos = rehash(h, modelo);
	if(h->tabela[pos] != NULL && strcmp(h->tabela[pos]->modelo, modelo) == 0){
		return pos;
	}

	return -1;
}

void liberarHash(Hash *h){	//frees
	for(int i = 0; i < h->m; i++){
		if(h->tabela[i] != NULL) free(h->tabela[i]);
	}
}


int main(){
	int entrada, qnt = 500;
	char caminhoArquivo[50];
	sprintf(caminhoArquivo, "%s", "/tmp/veiculos.csv");
	Veiculo *dados = lerCsv(caminhoArquivo, &qnt);

	Hash h;
	inicializa(&h);

	int pos;
	scanf("%d", &entrada);
	while(entrada != -1){
		pos = BuscaSequencial(entrada, dados, qnt);
		if(pos != -1){
			inserir(dados[pos], &h);
		}
		scanf("%d", &entrada);
	}


	char modelo[100];
	char *buffer = (char*)malloc(300*sizeof(char));
	scanf(" %[^\n]", modelo);	//le a linha toda ate o enter
	while(strcmp(modelo, "FIM") != 0){
		pos = pesquisar(&h, modelo);
		if(pos == -1){
			printf("-1\n");
		}
		else{
			formatVeiculo(*h.tabela[pos], buffer);
			printf("%d %s", pos, buffer);	//buffer ja termina com \n
		}
		scanf(" %[^\n]", modelo);
	}

	free(buffer);
	liberarHash(&h);
	free(dados);
	return 0;
}