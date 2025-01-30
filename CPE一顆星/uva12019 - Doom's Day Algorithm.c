#include <stdio.h>
int main(){
	const int days[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	const char* week[] = {"Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
	int Case,M,D;
	scanf("%d",&Case);
	while(Case--){
		scanf("%d %d",&M,&D);
		int day = D;
		for(int j=1;j<M;++j)day+=days[j];
		printf("%s\n",week[day%7]);
	}
}