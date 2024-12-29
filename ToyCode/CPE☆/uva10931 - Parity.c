#include <stdio.h>
int main(){
	char a[100000];
	long long I,B,P;
	while(scanf("%lld",&I)==1&&I!=0){
		B=0;
		P=0;
		printf("The parity of ");
		while(I!=0){
			a[P]=I%2+'0';
			if(a[P]=='1')B++;
			I/=2;
			P++;
		}
		while(P--)printf("%c",a[P]);
		printf(" is %lld (mod 2).\n",B);
	}
}