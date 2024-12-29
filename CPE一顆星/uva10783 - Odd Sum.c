#include <stdio.h>
int main(){
	int x,a,b;
	scanf("%d",&x);
	for(int i=0;i<x;i++){
		printf("Case %d:",i+1);
		scanf("%d %d",&a,&b);
		int sum=0;
		for(int j=a;j<=b;j++){
			if(j%2!=0)sum+=j;
		}
		printf(" %d\n",sum);
	}
}