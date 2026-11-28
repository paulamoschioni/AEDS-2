import java.util.Scanner;
import java.io.File;

class hashIndireta{
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
	return "[" + getId() + " ## " + getMarca() + " ## " + getModelo() + " ## " + getAno() + " ## "
	+ getCategoria() + " ## [" + comb + "] ## " + getCilindros() + " ## " + getCilindrada()
	+ " ## " + getTransmissao() + " ## " + getTracao() + " ## "
	+ String.format("%.2f", getConsumoCidade()) + " ## "
	+ String.format("%.2f", getConsumoEstrada()) + " ## " + getCo2() + " ## " + getTurbo()
	+ " ## " + getDataregistro().format() + "]";
		}
    }

	public static class Celula{
		Veiculo v;
		Celula prox;		//aponta pra proxima celula da lista

		public Celula(){	//construtor da celula cabeca (sentinela)
			this(null);
		}

		public Celula(Veiculo veic){
			v = veic;
			prox = null;
		}
	}

	public static class Lista{	//lista simplesmente encadeada com celula cabeca
	Celula primeiro;	
	Celula ultimo;		

	public Lista(){
		primeiro = new Celula();
		ultimo = primeiro;	//lista vazia: primeiro e ultimo apontam pra cabeca
	}

	public boolean vazia(){
		return primeiro == ultimo;
	}

	public void inserirFim(Veiculo v){	//insere no fim em O(1) usando o ponteiro ultimo
		ultimo.prox = new Celula(v);
		ultimo = ultimo.prox;
	}

	public void inserirInicio(Veiculo v){	//insere logo depois da cabeca
		Celula tmp = new Celula(v);
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if(primeiro == ultimo){		//se a lista estava vazia, o novo tambem e o ultimo
			ultimo = tmp;
		}
	}

	public Veiculo pesquisar(String modelo){	//percorre a lista procurando o modelo
		for(Celula i = primeiro.prox; i != null; i = i.prox){
			if(i.v.getModelo().equals(modelo)){
				return i.v;
			}
		}
		return null;
	}

	public Veiculo remover(String modelo){	//remove a celula com o modelo e devolve o veiculo
		Celula ant = primeiro;
		while(ant.prox != null && !ant.prox.v.getModelo().equals(modelo)){
			ant = ant.prox;
		}
		if(ant.prox == null){		//nao achou
			return null;
		}
		Celula tmp = ant.prox;
		ant.prox = tmp.prox;
		if(tmp == ultimo){		//removeu o ultimo: atualiza o ponteiro
			ultimo = ant;
		}
		tmp.prox = null;
		return tmp.v;
	}

	public int tamanho(){
		int tam = 0;
		for(Celula i = primeiro.prox; i != null; i = i.prox){
			tam++;
		}
		return tam;
	}
	}

	public static class Hash{	
	Lista[] tabela;
	int m;		//tamanho da tabela

	public Hash(int tam){
		m = tam;
		tabela = new Lista[m];
		for(int i = 0; i < m; i++){
			tabela[i] = new Lista();	//cada posicao comeca com uma lista vazia
		}
	}

	public int transforma(String modelo){	//soma dos codigos ASCII do modelo
		int soma = 0;
		for(int i = 0; i < modelo.length(); i++){
			soma += modelo.charAt(i);
		}
		return soma;
	}

	public int hash(String modelo){		
		return transforma(modelo) % m;
	}

	public void inserir(Veiculo v){		//colisao nao e problema: so entra no fim da lista
		int pos = hash(v.getModelo());
		tabela[pos].inserirFim(v);
	}

	public int pesquisar(String modelo){	//devolve a posicao da tabela onde esta o modelo, ou -1
		int pos = hash(modelo);
		if(tabela[pos].pesquisar(modelo) != null){
			return pos;
		}
		return -1;
	}

	}

	public static void main(String[] args) throws Exception{
	Scanner sc = new Scanner(System.in);

	LeitorCsv leitor = new LeitorCsv();
	Veiculo[] v = leitor.ler("/tmp/veiculos.csv");

	Hash hash = new Hash(31);

	int num = sc.nextInt();
	while(num != -1){
		int pos = BuscaSequencial(v, num);
		if(pos > -1){
		hash.inserir(v[pos]);
		}
		num = sc.nextInt();
	}
	sc.nextLine();	//descarta o "enter" que sobrou depois do -1

	
	String modelo = sc.nextLine();
	while(!modelo.equals("FIM")){
		int pos = hash.pesquisar(modelo);
		if(pos == -1){
		System.out.println(-1);
		}
		else{
		System.out.println(pos + " " + hash.tabela[pos].pesquisar(modelo).format());
		}
		modelo = sc.nextLine();
	}

	sc.close();
	}
}
