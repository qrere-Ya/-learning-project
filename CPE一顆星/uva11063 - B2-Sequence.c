#include <stdio.h>
int main(){
	int x,Case=0,flag=1;
	int N[1000]={0};
	while(scanf("%d",&x)!=EOF){
		for(int i=0;i<x;i++)scanf("%d",&N[i]);
		for(int i=1;i<x;i++){if(N[i]!=N[i-1]+N[i-1])flag=0;
		}Case++;
		if(flag==1)printf("Case #%d: It is a B2-Sequence.\n",Case);
		else printf("Case #%d: It is not a B2-Sequence.\n",Case);
		printf("\n");
	}
}