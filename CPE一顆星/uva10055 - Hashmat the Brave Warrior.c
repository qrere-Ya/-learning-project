#include <stdio.h>
#include <stdlib.h>
int main(){
	long long x,y;
	while(scanf("%lld %lld",&x,&y)!=EOF){
		printf("%lld\n",abs(x-y));
	}	
}