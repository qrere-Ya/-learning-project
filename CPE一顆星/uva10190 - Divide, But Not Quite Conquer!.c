#include <stdio.h>
int main(){
	int n,m,sum;
	while(scanf("%d %d",&n,&m)==2){
		if(m < 2 || n == 0 || n < m){
			printf("Boring!\n");
			continue;
		}
		int ans[100];
		int k=0;
		int isBoring = 1;
		while(n>1){
			if(n%m!=0){
				printf("Boring!\n");
				isBoring =0;
				break;	
			}
			ans[k++]=n;
			n/=m;
		}
		if(!isBoring)continue;
		ans[k++]=1;
		for(int i=0;i<k;i++){
			if (i > 0) printf(" ");
			printf("%d",ans[i]);
		}
		printf("\n");
	}
}