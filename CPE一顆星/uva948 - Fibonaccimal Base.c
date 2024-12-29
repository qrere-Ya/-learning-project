#include <stdio.h>
int main(){
	int F[39]={1,2};
	int i,n,a;
	for(i=2;i<39;i++)F[i]= F[i-1]+F[i-2];
	scanf("%d",&n);
	while(n--){
		scanf("%d",&a);
		printf("%d = ",a);
		int flag=0;
		for(i=38;i>=0;i--){
			if(a/F[i]==1){
				printf("1");
				a %=F[i],flag=1;
			}else if(flag)printf("0");
		}
		printf(" (fib)\n");
	}
}
