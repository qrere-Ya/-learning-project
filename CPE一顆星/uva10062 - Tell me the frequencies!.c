#include <stdio.h>
#include <string.h>
int main(){
	char str[1001];
	int i,j,flag=0;
	while(gets(str)){
		if(flag++)puts("");
		int ASCII[128]={0};
		for(i=0;i<strlen(str);i++){
			ASCII[str[i]]++;
		}
		for(i=1;i<=strlen(str);i++){
			for(j=127;j>=32;j--){
				if(ASCII[j]==i)
				printf("%d %d\n",j,i);
			}
		}
	}
}
