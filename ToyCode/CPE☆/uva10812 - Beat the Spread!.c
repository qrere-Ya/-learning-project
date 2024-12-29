#include <stdio.h>
int main(){
	int Case,s,d;
	scanf("%d",&Case);
	for(int i=0;i<Case;i++){
		scanf("%d %d",&s,&d);
		int a,b;
		a = (s+d)/2;
		b = (s-d)/2;
		if((s+d)%2==1 ||(s+d)%2==1 || a<0 || b<0){
			printf("impossible\n");
		}else{
			printf("%d %d\n",a,b);
		}
	}
}