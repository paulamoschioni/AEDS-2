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

int BuscaSequencial(int entrada, Veiculo dados[]){
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

typedef struct Celula{
	Veiculo veiculo;
	struct Celula *prox;
}Celula;
typedef struct{	
	struct Celula *primeira;
}ListaEncadeada;

ListaEncadeada *CriarLista(){
    ListaEncadeada *lista = malloc(sizeof(ListaEncadeada));
    lista->primeira = CriarCelula();   // celula cabeca
    return lista;
}
//metodos da lista II, IF, I*, RI, RF, R*
void II(Veiculo v, ListaEncadeada *lista){
	Celula *nova = malloc (sizeof(Celula));

	if (nova == NULL) {
    	printf("Erro: sem memoria disponivel\n");
	}
	nova->veiculo = v;				//atribui veiculo a celula nova
	nova->prox = lista->primeira->prox;		//muda o ponteiro de prox
	lista->primeira->prox = nova;			//muda o prox da celula cabeca
}

void IF(Veiculo v, ListaEncadeada *lista){
	Celula *nova = malloc (sizeof(Celula));

	if (nova == NULL) {
    	printf("Erro: sem memoria disponivel\n");
	}
	
	nova->veiculo = v;
	//acha a ultima celula atual
	Celula *atual = lista->primeira;
	while(atual->prox != NULL){
	atual = atual->prox;
	}
	atual->prox = nova;
	nova->prox = NULL;
}

void I(Veiculo v, int pos, ListaEncadeada *lista){
	Celula *nova = malloc (sizeof(Celula));

	if (nova == NULL) {
    	printf("Erro: sem memoria disponivel\n");
    	}
	
	nova->veiculo = v;
	Celula *atual = lista->primeira;   
	int j = -1;
	while (j < pos - 1) {              
		j++;
		atual = atual->prox;
	}	
	nova->prox = atual->prox;
	atual->prox = nova; 

}
Veiculo RI(ListaEncadeada *lista){
	Celula *temp;
	temp = lista->primeira->prox;    //salva o elemento a ser removido (dps da cabeca)

	if(lista->primeira->prox == NULL){	//vazia??
	printf("ERRO\n");
	}
	
	lista->primeira->prox = temp->prox;
	Veiculo dado = temp->veiculo;
	free(temp);
	return dado;
}

Veiculo RF(ListaEncadeada *lista){
	if (lista->primeira->prox == NULL) {
		printf("ERRO\n");
	}

	Celula *atual = lista->primeira;         // acha a penultima celula
	while (atual->prox->prox != NULL) {
		atual = atual->prox;
	}

	Celula *ultimo = atual->prox;             
	Veiculo dado = ultimo->veiculo;
	atual->prox = NULL;
	free(ultimo);

	return dado;
}
Veiculo R(int pos, ListaEncadeada *lista){
	if (lista->primeira->prox == NULL) {
		printf("ERRO\n");
	}

	Celula *atual = lista->primeira;
	int j = -1;
	while (j < pos - 1) {
		j++;
		atual = atual->prox;
	}

	Celula *removida = atual->prox;
	Veiculo dado = removida->veiculo;
	atual->prox = removida->prox;
	free(removida);

	return dado;
}

void mostrarLista(ListaEncadeada *lista, char *buffer){

	Celula *atual = lista->primeira;
	while(atual != NULL){   //econtra a ultima celula
	Veiculo v = atual->veiculo;
	formatVeiculo(v, buffer);
	printf("%s\n",buffer);

	atual = atual->prox;
	}

}

int main(){
	int entrada, qnt = 500;
	char caminhoArquivo[50];
    	strcpy(caminhoArquivo,"veiculos.csv");
	Veiculo *dados = lerCsv(caminhoArquivo, &qnt);
	
	int p = 0, qtde = 0;
	char *buffer = (char*)malloc(300*sizeof(char));
	scanf("%d",&entrada);
	Veiculo carro[50]; 

	while(entrada != -1){
		p = BuscaSequencial(entrada, dados);
		carro[qtde] = dados[p];
		qtde++;
	scanf("%d",&entrada); //id
	}

	ListaEncadeada *lista = CriarLista();  //criando a lista com a celula cabeca

	//envia esse array para a inserir na lista
	for(int k = 0; k < qtde; k++){
		IF(carro[k], lista);
	}
	//lendo comando+ id
	Veiculo* removidos = malloc (60* sizeof(Veiculo));
	int operacoes, pos, id, r = 0;
	char comando[3];
		scanf("%d",&operacoes);

	for(int i = 0; i < operacoes; i++){
		scanf(" %s",comando); 
		if (strcmp(comando,"I*") == 0 || (strcmp(comando,"R*") == 0)) {
			scanf(" %d",&pos);
		}

		
		Veiculo v;
		if (strcmp(comando,"II") == 0 || strcmp(comando,"IF") == 0 || strcmp(comando,"I*") == 0) {
			scanf(" %d",&entrada);
			p = BuscaSequencial(entrada, dados);
			v = dados[p];
		}

		if(strcmp(comando,"II") == 0) II(v, lista);
		else if(strcmp(comando,"IF") == 0) IF(v, lista);
		else if(strcmp(comando,"RI") == 0){
			removidos[r] = RI(lista);
			r++;	
		}
		else if(strcmp(comando,"RF") == 0) {
			removidos[r] = RF(lista);
			r++;
		}
		else if (strcmp(comando,"I*") == 0) {
			I(v,pos,lista);
		}
		else {
		removidos[r] = R(pos, lista);
		r++;
		}
		}

	
	for(int p = 0; p < r; p++){
		printf("(R)%s %s\n",removidos[p].marca ,removidos[p].modelo);
	}
	//printa lista
	mostrarLista(lista);

	free(buffer);
	free(removidos);
	free(lista);
return 0;
}
