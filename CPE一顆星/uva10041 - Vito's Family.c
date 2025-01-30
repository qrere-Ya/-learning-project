#include <stdio.h>
#include <stdlib.h>
int cmp (const void*i, const void*j){
	return *(int *)i - *(int *)j;
}
int main(){
	int r[500],T,n,i;
	scanf("%d",&T);
	while(T--){
		scanf("%d",&n);
		for(i=0;i<n;i++)scanf("%d",&r[i]);
		qsort(r,n,sizeof(int), cmp);
		int Su=0,Sr=n/2;
		for(i=0;i<n;i++)Su+=abs(r[Sr]-r[i]);
		printf("%d\n",Su);
	}
}