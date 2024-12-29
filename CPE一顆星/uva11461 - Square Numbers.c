#include <stdio.h>
#include <math.h>
int main(){
	int a,b;
	while(scanf("%d %d",&a,&b)){
		if (a == 0 && b == 0) break;
		int start = ceil(sqrt(a));
        int end = floor(sqrt(b));
        int result = (end - start + 1);
		printf("%d\n",result);
	}
}