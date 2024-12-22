#include <stdio.h>
#include <string.h>
int x[32],y[32],z[256];
void HashTable(){
	x['B'&31]=1;
	x['C'&31]=2;
	x['H'&31]=3;
	x['Z'&31]=4;
	x['Y'&31]=5;
	y['A'&31]=6;
	y['E'&31]=10;
	y['D'&31]=14;
	y['I'&31]=18;
	y['O'&31]=22;
	z[110]=0;
	z[30]=1;
	z[66]=2;
	z[18]=3;
	z[22]=4;
	z[36]=5;
	z[56]=6;
}

char *key[] = {"YOUGOOD","HELLO","HOLA","HALLO","BONJOUR","CIAO","ZDRAVSTVUJTE"};
char *lang[] = {"UNKNOWN","ENGLISH","SPANISH","GERMAN","FRENCH","ITALIAN","RUSSIAN"};

int main(){
	char str[64];
	int Case=1;
	HashTable();
	while(1){
		if(str[0]=='#')break;
		gets(str);
		if(str[0]=='#')break;
		int idx=z[x[str[0]&31]*y[str[1]&31]];
		if(strcmp(str+2,key[idx]+2)!=0)idx=0;
//		printf("%s:%s\n",str+2,key[idx]+2);
		printf("Case %d: %s\n",Case++,lang[idx]);
	}
}