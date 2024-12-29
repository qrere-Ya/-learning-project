#include <stdio.h>
#include <stdlib.h>
int List[10001],n,m;
int cmp(const void*a, const void*b){
	int x=*(int*)a;
	int y=*(int*)b;
	if(x%m != y%m) return x % m - y % m;
	else{
		if(x%2!=0&&y%2!=0)return y - x;
		else if(abs(x%2)==0 && abs(y%2)==0)return x - y;
		else return abs(y%2) - abs(x%2);
	}
}
int main(){
	while(scanf("%d %d",&n,&m)&&(n!=0 && m!=0)){
		printf("%d %d\n",n,m);
		for(int i=0;i<n;i++){
			scanf("%d",&List[i]);
		}
		qsort(List,n,sizeof(int),cmp);
		for(int i=0;i<n;i++){
			printf("%d\n",List[i]);
		}
	}
	printf("0 0\n");
}
