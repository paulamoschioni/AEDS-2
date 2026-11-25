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
void swap(Veiculo v[], int i, int menor){		//troca os elementos
	Veiculo temp = v[i];
	v[i] = v[menor];
	v[menor] = temp;
}

int compara(Veiculo *a, Veiculo *b){	// <0 se a vem antes, >0 se vem depois
	if(a->consumoEstrada < b->consumoEstrada) return -1;	//1o criterio: consumoEstrada
	if(a->consumoEstrada > b->consumoEstrada) return 1;
	return strcmp(a->marca, b->marca);	//empate = marca
}

void quickSortParcial(Veiculo carro[], int esq, int dir, int k){	//ordena so os k primeiros
	int j = dir, i = esq;
	Veiculo pivo = carro[(esq + dir)/2];	//pivo = elemento do meio
	while(j >= i){		//enquanto os ponteiros nao se cruzarem
		while(compara(&carro[i], &pivo) < 0){
		  i++;  //avancar
		}
		while(compara(&carro[j], &pivo) > 0){
		  j--;  //regredir
		}
		if(j >= i){
			swap(carro, i, j);   //troca
			i++;
			j--;
		}
	}

	if(esq < j){  //metade esquerda sempre pode conter alguem dos k primeiros
		quickSortParcial(carro, esq, j, k);
	}
	if(i < k && dir > i){ //metade direita so se ainda comecar antes da posicao k
		quickSortParcial(carro, i, dir, k);
	}
}


int main(){
	int entrada, qnt = 500;
	char caminhoArquivo[50];
	sprintf(caminhoArquivo, "%s", "/tmp/veiculos.csv");
	Veiculo *dados = lerCsv(caminhoArquivo, &qnt);

	Veiculo *selecionados = malloc(qnt * sizeof(Veiculo));	//vetor separado so com os veiculos pesquisados
	int qtdSelecionados = 0;	//quantidade de veiculos guardados em selecionados
	int K = 10;			//quantidade de elementos da ordenacao parcial

	int pos;
	scanf("%d", &entrada);
	while(entrada != -1){
	pos = BuscaSequencial(entrada, dados, qnt);
	if(pos != -1){
	selecionados[qtdSelecionados] = dados[pos];	//copia o veiculo encontrado
	qtdSelecionados++;
	}
		scanf("%d", &entrada);
	}

	if(qtdSelecionados > 1){
		quickSortParcial(selecionados, 0, qtdSelecionados - 1, K);	//ordena so os K primeiros
	}

	int limite = (K < qtdSelecionados) ? K : qtdSelecionados;	//imprime so os K primeiros
	char *buffer = (char*)malloc(300*sizeof(char));
	for(int i = 0; i < limite; i++){
	formatVeiculo(selecionados[i], buffer);
	printf("%s", buffer);
	}

	free(buffer);
	free(selecionados);
	free(dados);
	return 0;
}