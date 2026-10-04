import java.util.Scanner;
import java.io.File;                   
class mergeSort{
	public static int BuscaSequencial(Veiculo v[], int ident){
	int pos = -1;
	int tam = v.length;
	
	for(int i = 0; i < tam && v[i] != null; i++){   
		if(v[i].getId() == ident) {
		pos = i;
		}
	   }
	return pos;

	}

	public static class LeitorCsv{		//le arquivo csv
		public Veiculo[] ler(String caminhoArquivo)  throws Exception{
		Veiculo[] v = new Veiculo[500];
		Scanner sc = new Scanner(new File(caminhoArquivo));
	
		sc.nextLine(); //pular linha do cabecalho
		int qtde = 0;
			while(sc.hasNextLine()){
			String linha = sc.nextLine();
			v[qtde] = Veiculo.parseVeiculo(linha);
			qtde++;
			}

		sc.close();
		return v;	//devolve a ref do vetor chamado no main
	            }
		}

	public static class Data{
	//atributos
	private int dia;
	private int mes;
	private int ano;

	//metodos
	public static Data parseData(String s){	   //recebe no formato AAAA-MM-DD
	String[] d = s.split("-");
	Data nova = new Data();
	nova.ano = Integer.parseInt(d[0]);
	nova.mes = Integer.parseInt(d[1]);
	nova.dia = Integer.parseInt(d[2]);
	return nova;
	}
	
	public String format(){   //formata data
	return String.format("%02d/%02d/%04d",dia,mes,ano);
	}
       }

	public static class Veiculo{
	//atributos
	private int id;
	private String marca;
	private String modelo;
	private int ano;
	private String categoria;
	private String[] combustivel;
	private int cilindros;
	private double cilindrada;
	private String transmissao;
	private String tracao;
	private	double consumoCidade;
	private double consumoEstrada;
	private double co2;
	private boolean turbo;
	private Data dataRegistro;

	//metodos
	public Veiculo(int i, String ma, String mo, int a, String cat, String[] comb, int co, double ca, String t, String tr, double cC, double cE, double c2, boolean tur, Data dt){
	//construtor que inicializa atributos
	id = i;
	marca = ma;
	modelo = mo;
	ano = a;
	categoria = cat;
	combustivel = comb;
	cilindros = co;
	cilindrada = ca;
	transmissao = t;
	tracao = tr;
	consumoCidade = cC;
	consumoEstrada = cE;
	co2 = c2;
	turbo = tur;
	dataRegistro = dt;
	}

	//metodos get para todas as variaveis, retornando o respectivo atributo
	public int getId(){
	return id;
	}
	public String getMarca(){
	return marca;
	}
	public String getCategoria(){
	return categoria;
	}
	public int getAno(){
	return ano;
	}	
	public String[] getCombustivel(){
	return combustivel;
	}
	public int getCilindros(){
	return cilindros;
	}
	public double getCilindrada(){
	return cilindrada;
	}
	public String getTracao(){
	return tracao;
	}
	public String getTransmissao(){
	return transmissao;
	}
	public double getConsumoCidade(){
	return consumoCidade;
	}
	public double getConsumoEstrada(){
	return consumoEstrada;
	}
	public double getCo2(){
	return co2;
	}
	public boolean getTurbo(){
	return turbo;
	}
	public Data getDataregistro(){
	return dataRegistro;
	}

	public static Veiculo parseVeiculo(String s){	//cria um novo veiculo com 15 atributos e retorna ele				
	String[] x = s.split(",");		//cria vetor de 15 strings p guardar os 15 atributos. Split retorna automaticamente string[]
						//atribuindo valores a partir da string[] gerada
	//é preciso colocar os tipos dos atributos dnv, pois como o obj Veiculo nao existe ainda, o programa nao sabe qual e o tipo
	int id = Integer.parseInt(x[0]);				
	String marca = x[1];
	String modelo = x[2];
	int ano = Integer.parseInt(x[3]);
	String categoria = x[4];
	String[] combustivel = x[5].split(";");
	int cilindros = Integer.parseInt(x[6]);
	double cilindrada = Double.parseDouble(x[7]);
	String transmissao = x[8];
	String tracao = x[9];
	double consumoCidade = Double.parseDouble(x[10]);
	double consumoEstrada = Double.parseDouble(x[11]);
	double co2 = Double.parseDouble(x[12]);
	boolean turbo = Boolean.parseBoolean(x[13]);
	Data dataRegistro = Data.parseData(x[14]);

	return new Veiculo(id, marca, modelo, ano, categoria, combustivel, cilindros, cilindrada, transmissao, tracao, consumoCidade, consumoEstrada, co2,turbo,dataRegistro);  //criando um novo objeto da classe Veiculo, chamando seu construtor
	}

			
	public String format() {	// monta a string de saida do veiculo no formato pedido pelo enunciado
			String comb = ""; // junta combustiveis do vetor em uma unica string separados por v,
			for (int i = 0; i < combustivel.length; i++) {
				if (i > 0)
					comb += ",";
				comb += combustivel[i];
			}
	return "[" + getId() + " ## " + getMarca() + " ## " + modelo + " ## " + getAno() + " ## "
	+ getCategoria() + " ## [" + comb + "] ## " + getCilindros() + " ## " + getCilindrada()
	+ " ## " + getTransmissao() + " ## " + getTracao() + " ## "
	+ String.format("%.2f", getConsumoCidade()) + " ## "
	+ String.format("%.2f", getConsumoEstrada()) + " ## " + getCo2() + " ## " + getTurbo()
	+ " ## " + getDataregistro().format() + "]";
		}
	  }

	public static void mergeSort(Veiculo carro[], int esq, int dir){
		if(esq < dir){
			int meio = (esq+dir)/2;
			mergeSort(carro, esq, meio);   //envia parte da esquerda
			mergeSort(carro, meio + 1, dir);   //envia parte da direita
			intercalar(carro, esq, meio, dir);
		}
	}

	public static void intercalar(Veiculo vet[], int esq, int meio, int dir){
		//quantos numeros tem em cada metade?
		int nEsq = (meio + 1) - esq;
		int nDir = dir - meio;
		//cria vetores auxiliares
		Veiculo vetEsq[] = new Veiculo[nEsq + 1];
		Veiculo vetDir[] = new Veiculo[nDir + 1];

		//copia metade direita e esq para vetores auxiliares
		int iEsq = 0, iDir = 0;
		while(iEsq < nEsq){
			vetEsq[iEsq] = vet[esq + iEsq];
			iEsq++;
		}
		while(iDir < nDir){
			vetDir[iDir] = vet[(meio + 1) + iDir];
			iDir++;
		}
		//cria carro sentinela
		Veiculo sentinela = new Veiculo(0, "", "", 0, "", null, 0, 0, "", "", Double.MAX_VALUE, 0, 0, false, null);
		vetEsq[nEsq] = sentinela;
		vetDir[nDir] = sentinela;

		//compara e encaixa
		iEsq = 0;
		iDir = 0;
		for(int i = esq; i <= dir; i++){	//i caminha pelo tamanho do vetor original da chamada atual
			if(vetEsq[iEsq].consumoCidade < vetDir[iDir].consumoCidade ||
 			 (vetEsq[iEsq].consumoCidade == vetDir[iDir].consumoCidade &&
  			 vetEsq[iEsq].categoria.compareTo(vetDir[iDir].categoria) < 0)){
				vet[i] = vetEsq[iEsq];
				iEsq++;
			}  else  {
				vet[i] = vetDir[iDir];
				iDir++;
			}

		}

	}




	public static void main(String[] args) throws Exception{
	Scanner sc = new Scanner(System.in);
	//ler CSV
	LeitorCsv leitor = new LeitorCsv();
	Veiculo[] v = leitor.ler("/tmp/veiculos.csv");

	//Ler ids para buscar em Veiculo[]
	int num = sc.nextInt();
	int resultado;
	Veiculo[] encontrados = new Veiculo[500];	//guarda os veiculos encontrados
	int n = 0;	//quantos veiculos foram encontrados

	while(num != -1){	//le conjunto de ids para buscar
		//busca pelo id num
		resultado = BuscaSequencial(v,num);
		
		if(resultado > -1){
		encontrados[n] = v[resultado];	//guarda o veiculo em vez de imprimir
		n++;
		}	
		num = sc.nextInt();
	}

	mergeSort(encontrados, 0, n - 1);	//envia os encontrados para o merge

	for(int i = 0; i < n; i++){	//imprime os veiculos ja ordenados
		System.out.println(encontrados[i].format());
	}

	sc.close();
	}
}
