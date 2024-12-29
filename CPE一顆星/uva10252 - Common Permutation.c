#include <stdio.h>
int main(){
	char s[1001];
	while(gets(s)){
		int table1[128]={},table2[128]={};
		int i,j;
		for(i=0;s[i];i++)if (s[i] != ' ')table1[s[i]]++;
		gets(s);
		for(i=0;s[i];i++)if (s[i] != ' ')table2[s[i]]++;
		for(i=0;i<128;i++){
			for(j=0;j<table1[i]&&j<table2[i];j++)printf("%c",i);
		}
		puts("");
	}
}