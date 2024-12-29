#include <stdio.h>
#include <string.h>
#include <ctype.h>
char*s="1234567890-=qwertyuiop[]asdfghjkl;'zxcvbnm,./";
int main(){
	int i,c;
	while((c=getchar())!=EOF){
		if(isupper(c))c=tolower(c);
		for(i=1;s[i]&&s[i]!=c;++i);
		if(s[i])putchar(s[i-2]);
		else putchar(c);
	}
}