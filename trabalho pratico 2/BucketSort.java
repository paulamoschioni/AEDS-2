import java.util.Scanner;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
class BucketSort{
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
        return v;    //devolve a ref do vetor chamado no main
                }
        }

    public static class Data{
    //atributos
    private int dia;
    private int mes;
    private int ano;
    //metodos
    public static Data parseData(String s){       //recebe no formato AAAA-MM-DD
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
    private    double consumoCidade;
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

    public static Veiculo parseVeiculo(String s){    //cria um novo veiculo com 15 atributos e retorna ele                
    String[] x = s.split(",");        //cria vetor de 15 strings p guardar os 15 atributos. Split retorna automaticamente string[]
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
public static void insertionSort(Veiculo v[], int tam){
    for(int i=1; i<tam; i++){
        Veiculo temp = v[i];
        int j= i-1;
        while(j>=0 && v[j].getCilindrada() > temp.getCilindrada()){
            v[j+1] = v[j];
            j--;
        }
        v[j+1] = temp;
    }
}

public static void bucketSort(Veiculo v[] , int n){
    Veiculo[][] baldes = new Veiculo[10][50];
    Veiculo[] saida = new Veiculo[50];
    int[] cont = new int[10];
    for(int i=0; i<n; i++){
        double valor = v[i].getCilindrada()/8.1;
        int indice = (int)(valor*10);
        baldes[indice][cont[indice]] = v[i];
        cont[indice]++;
    }
    for(int i=0; i<10; i++)
        insertionSort(baldes[i], cont[i]);
    int posi=0;
    for(int i=0; i<10; i++){
        for(int j=0; j<cont[i]; j++){
            saida[posi] = baldes[i][j];
            posi++;
        }
    }
    for(int i=0; i<n; i++)
        v[i] =saida[i];
}

    public static void main(String[] args) throws FileNotFoundException{
    Scanner sc = new Scanner(System.in);
    //ler CSV
    LeitorCsv leitor = new LeitorCsv();
    Veiculo[] v = leitor.ler("veiculos.csv"); //volta a ref de v

    Veiculo[] dados = leitor.ler(caminhoArquivo);
    Veiculo[] carro = new Veiculo[50];
    int entrada = sc.nextInt();
    int pos = 0;

    while(entrada>0){
        for(int i=0; i<500; i++){ 
            if(entrada == dados[i].getId()){
                carro[pos] = dados[i];
                pos++;
                i=500;
            }
        }
    entrada=sc.nextInt();
    }

    bucketSort(carro, pos);
    for(int k=0; k<pos; k++){
     System.out.println(carro[k].format());
    }
    sc.close();
}
} 
