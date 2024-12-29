#include <stdio.h>
int main(){
	char n[1001];
	while(scanf("%s",&n)){
		if(n[0]=='0')break;
		int sum=0;
		for(int i=0;n[i]!='\0';i++){
			if(i%2==0)sum+=n[i]- '0';
			else sum-=n[i]-'0';
		}
		if(sum%11==0){
			printf("%s is a multiple of 11.\n",n);
		}else{
			printf("%s is not a multiple of 11.\n",n);
		}
	}
}
