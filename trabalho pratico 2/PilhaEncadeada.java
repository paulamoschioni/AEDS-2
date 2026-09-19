import java.util.Scanner;
class PilhaEncadeada{
	public static int BuscaSequencial(Veiculo v[], int ident){
	int pos = -1;
	int tam = v.length;
	
	for(int i = 0; i < tam; i++){
		if(v[i].getId() == ident) {
		pos = i;
		}
	   }
	return pos;

	}

	public static class LeitorCsv{
		public Veiculo[] ler(String caminhoArquivo)  throws FileNotFoundException{
		Veiculo[] v = new Veiculo[500];
		Scanner sc = new Scanner(new File(caminhoArquivo));
		
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
	
	public String format(){
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

	public String format(){
	return String.format("[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %s ## %s]",id, marca, modelo, ano, categoria, String.join(";",combustivel), cilindros, cilindrada, transmissao, tracao, consumoCidade, consumoEstrada, co2,turbo,dataRegistro.format());
	}
	  }

	public class Celula{
	Celula prox;
	Veiculo veiculo;

	public Celula(){	//constrtor sem parametros para inicializar os ponteiros null
		prox = null;
		veiculo = null;
	}
	
	public Celula(Veiculo v){	//constrtor sem parametros para inicializar o veiculo
		veiculo = v;
		prox = null;
	}

	} 


	public class Pilha{
	Celula topo;

	public Pilha(){
        	topo = null;
   	}

   	public void InserirFim(Veiculo v){
		Celula nova = new Celula(v);  //cria nova
		nova.prox = topo;
		topo = nova;
	}	

	public Veiculo RemoverFim(){
		if(topo == null){
		System.out.println("erro");
		return null;
		}

		Veiculo temp = topo.veiculo;
		topo = topo.prox;
		return temp;
	}

	public void mostrar(){
		if(topo == null){
		System.out.println("erro");
		}

		Celula atual = topo;
		while(atual != null){
		System.out.println(format(atual.veiculo));
		
		atual = atual.prox;
		}
	}	
}
	
public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

  
        LeitorCsv leitor = new LeitorCsv();
        Veiculo[] v = leitor.ler("veiculos.csv");

        Pilha pilha = new Pilha();

  
        int num = sc.nextInt();
        while (num != -1) {
            int pos = BuscaSequencial(v, num);
            pilha.inserirFim(v[pos]);
            num = sc.nextInt();
        }

        
        int operacoes = sc.nextInt();
        Veiculo[] removidos = new Veiculo[operacoes];
        int r = 0;

        for (int p = 0; p < operacoes; p++) {
            String comando = sc.next();  
            if (comando.equals("R")) {
                removidos[r] = pilha.removerFim();
                r++;
            } else if (comando.equals("I")) {
                int id = sc.nextInt();
                int posi = BuscaSequencial(v, id);
                pilha.inserirFim(v[posi]);
            }
        }

        for (int k = 0; k < r; k++) {
            System.out.println("(R)" + removidos[k].marca + " " + removidos[k].modelo);
        }

     
        pilha.mostrar();

        sc.close();
    }
}
	



