#include <stdio.h>
int SomaDigitos(int numero){
	if(numero == 0){ // condicao de parada
	return 0;
	}
	else if(numero > 0){
	return (numero%10) + SomaDigitos(numero/10);
	}
}


int main(){
	int numeros; 
	int resultado;
	while(scanf("%d",&numeros) == 1){
	resultado = SomaDigitos(numeros);
	printf("%d\n",resultado);
	}
return 0;
}
