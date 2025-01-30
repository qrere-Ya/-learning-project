#include <stdio.h>
int main(){
	int n,m;
	while(scanf("%d %d",&n,&m)){
		if (n == 0 && m == 0)
            break;
		int carry =0;
		while(n > 0 || m > 0){
			if((n%10)+(m%10)+carry>=10)carry++;
			n = n/10;
			m = m/10;
		}
		if(carry==0){
			printf("No carry operation.\n");
		}else if(carry==1){
			printf("1 carry operation.\n");
		}else{
			printf("%d carry operations.\n",carry);
		}
	}
}