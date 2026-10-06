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


typedef struct Celula{	//celula da lista
	Veiculo elemento;
	struct Celula *prox;
} Celula;


typedef struct Lista{  //struct de uma lista 
	Celula *primeiro;
	Celula *ultimo;
	int n;
} Lista;

Celula* novaCelula(Veiculo v){	//cria nova celula com o veiculo v
	Celula *nova = (Celula*)malloc(sizeof(Celula));
	nova->elemento = v;
	nova->prox = NULL;
	return nova;
}


void iniciarLista(Lista *l){//lista vazia
	Veiculo vazio;
	vazio.id = -1;
	l->primeiro = novaCelula(vazio);
	l->ultimo = l->primeiro;
	l->n = 0;
}

void inserirFim(Lista *l, Veiculo v){//insere um veiculo depois da ultima celula 
	l->ultimo->prox = novaCelula(v);
	l->ultimo = l->ultimo->prox;
	l->n++;
}

void mostrar(Lista *l){  //percorre todas as celulas e immprime
	char *buffer = (char*)malloc(300*sizeof(char));
	for(Celula *i = l->primeiro->prox; i != NULL; i = i->prox){
		formatVeiculo(i->elemento, buffer);
		printf("%s", buffer);
	}
	free(buffer);
}


void liberarLista(Lista *l){  //libera todas as celulas 
	Celula *i = l->primeiro;
	while(i != NULL){
		Celula *tmp = i;
		i = i->prox;
		free(tmp);
	}
}



void selecao(Lista *lista, int n){  //ordena por selecao
	Celula *celulaI= lista->primeiro.prox;
	int iteracaoI = 0;

	while(celulaI->prox!= NULL && iteracaoI < (n - 1)){  //vai ate a penultima celula
		int menor = iteracaoI;  //posicao do menor modelo encontrado ate agora
		Celula *atual = celulaI;

			Celula *celulaJ = celulaI->prox;	//celula j
			int iteracaoJ = iteracaoI + 1;		
			while(celulaJ != NULL){ //vai ate a ultima celula
				int cmp = caseSense(atual->elemento.modelo, celulaJ->elemento.modelo);

				if(cmp < 0 || (cmp == 0 && celulaJ->elemento.id < atual->elemento.id)){
					menor = iteracaoJ;
					atual = celulaJ;
				}
				iteracaoJ++;	
				celulaJ = celulaJ->prox;
			}

		Veiculo temp = atual->elemento;		
		atual->elemento = celula->elemento;
		celulaI->elemento = temp;
		

		celulaI = celulaI->prox;
		iteracaoI++;
	}
}


int main(){
	int entrada, qnt = 500;
	char caminhoArquivo[50];
	sprintf(caminhoArquivo, "%s", "/tmp/veiculos.csv");
	Veiculo *dados = lerCsv(caminhoArquivo, &qnt);

	Lista lista;
	iniciarLista(&lista);

	Veiculo *selecionados = malloc(qnt * sizeof(Veiculo));	//vetor separado so com os veiculos pesquisados
	int k = 0;		//quantidade de veiculos guardados em selecionados

	//ids ate -1
	int entrada, qtde = 0;

	while(scanf("%d", &entrada) == 1 && entrada != -1){
		int pos = BuscaSequencial(entrada, dados, qnt);
		if(pos != -1) inserirFim(&lista, dados[pos]);
		qtde++;
	}

	selecao(&lista, qtde);	//ordena so os k veiculos guardados
	
	mostrar(&lista);

	liberarLista(&lista);
	free(dados);
	return 0;
}
