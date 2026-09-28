import java.util.Scanner;
class navDiretorio{
	public static class Celula{
		private Celula prox;
		private String nome;

	public Celula(String string){
		prox = null;
		nome = string;
	}

	}

	public static class Pilha{
		private Celula topo;
	
	public Pilha(String s){
		Celula nova = new Celula(s);	//cria nova celula
		topo = nova;
	}
	
	public Pilha(){
		topo = null;
	}

	public void inserirFim(String s){
		Celula nova = new Celula(s);  //cria nova
		
		//inserir topo
		nova.prox = topo; 
		topo = nova;
		
	}
	
	public void removerFim(){
		topo = topo.prox;
	}
	
	public void mostrar(){			//sem parametroa, para chamar no main.
		//se a pilha esta vazia, imprime apenas '/'
		if(topo == null){
			System.out.println("/");
		} else {
			mostrarRec(topo);	//chama o metodo recursivo de fato	
		}
	}

	public void mostrarRec(Celula atual){
		if(atual != null){
			mostrarRec(atual.prox);
			System.out.print(atual.nome);
		}
	}
}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		//lendo: 
		int N;
		String comando; //le o comando
		String entra;	//le o caminho da pasta
		
		while(sc.hasNextInt()){ //ate acabar os numeros de caso
			N = sc.nextInt();
			Pilha pilha = new Pilha();
			for(int i = 0; i < N; i++){
				comando = sc.next();

				if(comando.equals("ENTRA")){ //se for de entrar, insere no fim
					entra = sc.next();
					String mandar = "/" + entra; //contatenando com '/'
					//mandando o caminho correto
					pilha.inserirFim(mandar);

				} else if(comando.equals("SAI")) {
					//primeiro retira a ultima frase e ACRESCENTA '/'
					pilha.removerFim();
					//printa pilha
					pilha.mostrar();
					System.out.print("/");
					System.out.print("\n");

				} else if(comando.equals("CAMINHO")) {
					pilha.mostrar();					
				}
			}
		}
	sc.close(); }

