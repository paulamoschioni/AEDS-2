
import java.util.Scanner;
import java.io.File;

class heapSort{
	public static int BuscaSequencial(Veiculo v[], int ident){ //busca por id no array de veiculos
	int pos = -1;
	int tam = v.length;

	for(int i = 0; i < tam && v[i] != null; i++){
		if(v[i].getId() == ident) {
		pos = i;
		}
	   }
	return pos;

	}

	public static void swap(int a, int b, Veiculo v[]){
		Veiculo temp = v[a];
		v[a] = v[b];
		v[b] = temp;
	}

	// retorna > 0 se a vem DEPOIS de b, < 0 se vem ANTES, 0 se iguais
	public static int compara(Veiculo a, Veiculo b){
		if(a.co2 != b.co2){								//1o criterio: co2
			return Double.compare(a.co2, b.co2);
		}
		return a.getTransmissao().compareTo(b.getTransmissao());	//desempate: transmissao
	}

	//heap comeca na posicao 0
	public static void construir(int ultimo, Veiculo array[]){
		int i = ultimo;
		while(i > 0 && compara(array[i], array[(i-1)/2]) > 0){  //NAO é a raiz E é maior que o pai
			swap(i, (i-1)/2, array);
			i = (i-1)/2; //proximo pai
		}
	}

	public static int getMaiorFilho(int i, int tamHeap, Veiculo array[]){ 
		int posicaoFilhoMaior = 2*i + 1;	//comeca pelo da esquerda

		//so troca pro da direita se ele existir e for maior
		if(2*i + 2 < tamHeap && compara(array[2*i + 2], array[2*i + 1]) > 0){
			posicaoFilhoMaior = 2*i + 2;
		}

		return posicaoFilhoMaior;
	}

	public static void reconstroi(int tamHeap, Veiculo array[]){
		int i = 0;   //comeca da raiz, onde esta o possivel "intruso"
		while(2*i + 1 < tamHeap){	//enquanto i tiver pelo menos 1 filho
			int filho = getMaiorFilho(i, tamHeap, array);   //pega o maior filho 
			if(compara(array[filho], array[i]) > 0){	//arvore esta invertida?
				swap(i, filho,array);				//se sim, pai desce e filho sobe
				i = filho;
			} else {
				i = tamHeap;						//se nao, BREAK
			}
		}
	}

	public static void heapSort(int n, Veiculo v[]){
		//construir heap:
		for(int i = 1; i < n; i++){	//comeca do segundo (posicao 1) e vai ate o ultimo
			construir(i, v); //aqui verifica se tem que trocar
		}

		for(int tam = n - 1; tam > 0; tam--){
			swap(0, tam, v);	//trocar primeiro com ultimo
			reconstroi(tam, v);	
		}
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

	//metodos get para todas as variaveis, retornando o atributo
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

	public String getModelo(){
	return modelo;
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




	public static void main(String[] args) throws Exception{
	Scanner sc = new Scanner(System.in);
	//ler CSV
	LeitorCsv leitor = new LeitorCsv();
	Veiculo[] v = leitor.ler("/tmp/veiculos.csv");

	//guarda os VEICULOS encontrados, nas posicoes 0 ate i-1
	Veiculo[] resultado = new Veiculo[500];
	int i = 0;	//quantos veiculos ja foram guardados

	//Ler ids para buscar em Veiculo[]
	int num = sc.nextInt();
	int pos;

	while(num != -1){	//le conjunto de ids para buscar
		//busca pelo id num
		pos = BuscaSequencial(v,num);
		
		if(pos > -1){
		resultado[i] = v[pos];
		i++;
		}	
		num = sc.nextInt();
	}

	heapSort(i, resultado);	//ordena as posicoes 0 ate i-1 por co2
	for(int k = 0; k < i; k++){
		System.out.println(resultado[k].format());
	}

	sc.close();
	}
}