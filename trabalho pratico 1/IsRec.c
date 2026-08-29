#include <stdio.h>
int achouPtVg; ///essa e uma variavel global usada na funcao de REAIS
int SoVogais(char frase[],int pos){
	char c = frase[pos];
	if(pos < 0) return 1;
	
	int vogal = (c =='a'||c=='e'||c=='i'||c=='o'||c=='u'|| c =='A'||c=='E'||c=='I'||c=='O'||c=='U');   //compara atual char com vogais
	if(!vogal) return 0; 
	return SoVogais(frase,pos-1); 		//chamada recursiva
}

int SoConsoantes(char frase[],int pos){
	if(pos < 0) return 1;
    	char c = frase[pos];

    	int vogal = (c=='a'||c=='e'||c=='i'||c=='o'||c=='u'||c=='A'||c=='E'||c=='I'||c=='O'||c=='U');	//se for letra E vogal contabiliza aqui
    	int letra = ((c>='a'&&c<='z')||(c>='A'&&c<='Z'));  //certificando que e uma letra
   	 if(!letra || vogal) return 0;      // nao e letra, ou e vogal, retorna falso
    	return SoConsoantes(frase, pos - 1);
}

int SoInteiros(char frase[],int  pos){
	if(pos < 0) return 1;
    	char c = frase[pos];
	   	 if(pos == 0 && (c=='+' || c=='-')) return 1;  // se tiver sinal na 1a posicao
   	 if(c < '0' || c > '9') return 0;               // se o char nao estiver entre 0 e 9 eh falso
    	return SoInteiros(frase, pos - 1);
}

int SoReais(char frase[],int pos){
	if(pos < 0) return 1;
   	 char c = frase[pos];
	
    	if(c == '.' || c == ','){
        if(achouPtVg == 1) return 0;   // ja tinha achado um antes
        achouPtVg = 1;
	return SoReais(frase, pos - 1);
  	} else {
   	 if(c < '0' || c > '9') return 0;  //n eh numero
    	return SoReais(frase, pos - 1);
	}
}



int main(){
	char frase[400];
	scanf(" %[^\n]",frase);
	int vog, cons, inte, real;
	
	while(frase[0] != 'F' || frase[1] != 'I' || frase[2] != 'M'){
        int tam = 0;
        while(frase[tam] != '\0') tam++;

        achouPtVg = 0;   // reseta antes de checar essa palavra

        vog  = SoVogais(frase, tam - 1);
        cons = SoConsoantes(frase, tam - 1);
        inte = SoInteiros(frase, tam - 1);
        real = SoReais(frase, tam - 1);

        if(vog) printf("SIM "); else printf("NAO ");
        if(cons) printf("SIM "); else printf("NAO ");
        if(inte) printf("SIM "); else printf("NAO ");
        if(real) printf("SIM "); else printf("NAO ");

        scanf(" %[^\n]", frase);
        printf("\n");
    }
return 0;

}
