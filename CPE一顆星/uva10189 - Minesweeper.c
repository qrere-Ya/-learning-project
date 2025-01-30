#include <stdio.h>
int main(){
	int n,m,Case=1;
	while(scanf("%d%d",&n,&m)!=EOF){
		if(n==0 && m==0)break;
		int i,j,k,l,field[101][101]={0};
		char c;
		getchar();
		if(Case>1)printf("\n");
		printf("Field #%d:\n",Case++);
		for(i=1;i<=n;i++){
			for(j=1;j<=m;j++){
				c = getchar();
				if(c=='*'){
					field[i][j]=-101;
					for(k=-1;k<=1;k++)
						for(l=-1;l<=1;l++)field[i+k][j+l]++;
				}
			}
			getchar();
		}
		for(i=1;i<=n;i++){
			for(j=1;j<=m;j++){
				if(field[i][j]<0)printf("*");
				else printf("%d",field[i][j]);
			}
			printf("\n");
		}
	}
}

/*-------另一總寫法-------*\
#include <stdio.h>
int main(){
	int n,m,Case=1;
	while(scanf("%d%d",&n,&m)!=EOF){
		if(n==0 && m==0)break;
		int i,j,k,l,field[101][101]={0};
		char c;
		getchar();
		if(Case>1)printf("\n");
		printf("Field #%d:\n",Case++);
		for(i=1;i<=n;i++){
			for(j=1;j<=m;j++){
				c = getchar();
				if(c=='*'){
					field[i][j]=-1;
					for(k=-1;k<=1;k++)
						for(l=-1;l<=1;l++)
						if (i + k >= 1 && i + k <= n && j + l >= 1 && j + l <= m && field[i + k][j + l] != -1){
							field[i+k][j+l]++;
						}
				}
			}
			getchar();
		}
		for(i=1;i<=n;i++){
			for(j=1;j<=m;j++){
				if(field[i][j]==-1)printf("*");
				else printf("%d",field[i][j]);
			}
			printf("\n");
		}
	}
}
/*-------另一總寫法-------*\
