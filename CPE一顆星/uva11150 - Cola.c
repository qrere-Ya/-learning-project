#include <stdio.h>
int main(){
	int n;
	while(scanf("%d",&n)!=EOF){
		int sum = n;
		while(n>=3){
			sum +=n/3;
			n= n/3+n%3;
		}
		if(n==2)sum++;
		printf("%d\n",sum);
	}
}