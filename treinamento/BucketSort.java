import java.util.Scanner;
import java.io.File;                   
class BucketSort{
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


	public static void insercaoBalde(Veiculo[] balde, int n){	//insercao dentro de 1 balde
		for(int i = 1; i < n; i++){
			Veiculo tmp = balde[i];
			int j = i - 1;
			while(j >= 0 && balde[j].getCilindrada() > tmp.getCilindrada()){
				balde[j + 1] = balde[j];
				j--;
			}
			balde[j + 1] = tmp;
		}
	}

	public static void bucketSort(Veiculo[] array, int n){
		int numBaldes = 10;
		Veiculo[][] baldes = new Veiculo[numBaldes][n];	//cada balde cabe no maximo n veiculos
		int[] qtd = new int[numBaldes];			//quantos veiculos tem em cada balde

		//cada veiculo vai pro balde de acordo com a cilindrada normalizada
		for(int i = 0; i < n; i++){
			double normalizado = array[i].getCilindrada() / 8.1;	//fica entre 0 e 1
			int b = (int)(normalizado * numBaldes);			//vira indice de 0 a 9
			if(b >= numBaldes) b = numBaldes - 1;			
			if(b < 0) b = 0;

			baldes[b][qtd[b]] = array[i];
			qtd[b]++;
		}

		
		for(int b = 0; b < numBaldes; b++){//ordenar cada balde com insercao
			insercaoBalde(baldes[b], qtd[b]);
		}

		
		int k = 0;
		for(int b = 0; b < numBaldes; b++){// copia os baldes de volta pro array, do balde 0 ao 9
			for(int i = 0; i < qtd[b]; i++){
				array[k] = baldes[b][i];
				k++;
			}
		}
	}



	public static void main(String[] args) throws Exception{
	Scanner sc = new Scanner(System.in);
	//ler CSV
	LeitorCsv leitor = new LeitorCsv();
	Veiculo[] v = leitor.ler("/tmp/veiculos.csv"); //volta a ref de v

	//vetor separado so com os veiculos pedidos na entrada
	Veiculo[] selecionados = new Veiculo[500];
	int n = 0;

	int num = sc.nextInt();
	int resultado;

	while(num != -1){
		resultado = BuscaSequencial(v,num);
		if(resultado > -1){
		selecionados[n] = v[resultado];
		n++;
		}
		num = sc.nextInt();
	}

	bucketSort(selecionados, n);

	for(int i = 0; i < n; i++){
		System.out.println(selecionados[i].format());
	}

	sc.close();
	}
}
