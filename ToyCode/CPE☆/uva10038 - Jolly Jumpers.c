#include <stdio.h>
#include <stdlib.h>
int main(){
	int n,a[1001],i;
	while(scanf("%d",&n)==1){
		for(i=0;i<n;i++)scanf("%d",&a[i]);
		int s[1001]={},flag=0;
		for(i=1;i<n;i++){
			if(abs(a[i]-a[i-1])<n)s[abs(a[i]-a[i-1])]++;
		}
		for(i=1;i<n;i++)if(s[i]==0)flag=1;
			if(flag)printf("Not jolly\n");
			else printf("Jolly\n");
	}
}