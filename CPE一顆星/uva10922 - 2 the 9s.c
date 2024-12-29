#include <stdio.h>
int main(){
	char N[1001];
	while(scanf("%s",N)==1){
		if(N[0]=='0')break;
		int i,sum=0;
		for(i=0;N[i];i++)sum+=N[i]-'0';
		printf("%s is ",N);
		if(sum%9!=0)puts("not a multiple of 9.");
		else{
			int degree=1;
			while(sum>=10){
				sprintf(N,"%d",sum);
				sum=0;
				for(i=0;N[i];i++)sum+=N[i]-'0';
				degree++;
			}
			printf("a multiple of 9 and has 9-degree %d.\n", degree);
		}
	}
}