#include <stdio.h>
int main(){
	char chr;
	bool flag=true;
	while((chr=getchar())!=EOF){
		if(flag&&chr=='"'){
			printf("``");
			flag = false;
		}else if(!flag&&chr=='"'){
			printf("''");
			flag=true;
		}else{
			printf("%c",chr);
		}
	}
	
}