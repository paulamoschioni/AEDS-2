import java.util.Scanner;
import java.io.File;                   


class quickListaDupla{
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

	public class Celula{
	Celula prox;
	Celula ant;
	Veiculo veiculo;

	public Celula(){	//constrtor sem parametros para inicializar os ponteiros null
		prox = null;
		ant = null;
		veiculo = null;
	}
	
	public Celula(Veiculo v){	//constrtor sem parametros para inicializar o veiculo
		veiculo = v;
		prox = null;
		ant = null;
	}

	} 


	public class ListaDupla{
	Celula primeira;
	Celula ultima;

	public ListaDupla(){
        	primeira = null;
		ultima = null;
   	}

   	public void IF(Veiculo v){
		Celula nova = new Celula(v);  
		//ajusta ponteiros da nova celula
		nova.ant = ultima;

		//ajusta ponteiros da ultima celula
		ultima.prox = nova;

		//ajusta ultima
		ultima = nova;	
	}	

	public void mostraLista(){
		Celula atual = primeira.prox;
		while(atual != null){
		atual.veiculo.format();
		
		atual = atual.prox;
		}
	}	
}
	static void swap(Celula a, Celula b){
		Veiculo temp = a.veiculo;
		a.veiculo = b.veiculo;
		b.veiculo = temp;
	}

	static void quickSort(Celula esq, Celula dir, int Nesq, int Ndir){
		Celula cI = esq, cJ = dir;
		int i = Nesq, j = Ndir;
		
		//achar nó do pivo do meio
		Celula meio = esq;
		for(int k = 0; k < ((Nesq+Ndir)/2); k++){
			meio = meio.prox;
		}
		double pivo = meio.veiculo.consumoEstrada;  

		while(i <= j){	//enquanto ponteiros nao se cruzaram ainda
			while(cI.veiculo.consumoEstrada < pivo){	//avanca i para frente
				i++;
				cI = cI.prox;
			}
			while(cJ.veiculo.consumoEstrada > pivo){  //enquanto ponteiros forem maiores, retrocede
				j--;
				cJ = cJ.ant;
			}

			if(i <= j){  //enquanto nao se cruzaram, 
				swap(cI,cJ);
				cI = cI.prox;
				i++;
				cJ =cJ.ant;
				j--;
			}
		}
			//chamadas recursivas
			if(Nesq < j){
				quickSort(esq,cJ,Nesq,j);
			}
			if(Ndir > i){
				quickSort(cI, dir,i,Ndir);
			}
	}


	public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

  
        LeitorCsv leitor = new LeitorCsv();
        Veiculo[] v = leitor.ler("veiculos.csv");

        ListaDupla lista = new ListaDupla();
		
		int tamanhoLista = 0;
        int num = sc.nextInt();
        while (num != -1) {
            int pos = BuscaSequencial(v, num);
            lista.IF(v[pos]);				//ja insere direto na lista
            num = sc.nextInt();
			tamanhoLista++;
        }

	//chama quicksort
		quickSort(lista.primeira, lista.ultima, 0, tamanhoLista - 1);

    	//printa o restante da lista 
        lista.mostraLista();
        sc.close();
    }
}
	




