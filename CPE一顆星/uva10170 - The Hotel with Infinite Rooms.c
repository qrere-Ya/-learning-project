#include <stdio.h>
int main(){
	long long S,D;
	while(scanf("%lld %lld",&S,&D)!=EOF){
		long long N = S;
		while(N<D){
			S++;
			N=N+S;
		}
		printf("%lld\n",S);
	}
}