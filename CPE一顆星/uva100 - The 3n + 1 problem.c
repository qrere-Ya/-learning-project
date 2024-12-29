#include <stdio.h>
int main(){
	int a,b,max,temp,time,start,end;
	while(scanf("%d %d",&a,&b)!=EOF){
		max=-1;
		if(a>b){
			start = b;
			end = a;
		}else{
			start = a;
			end = b;
		}
		for(int i=start;i<=end;i++){
			time=0;
			temp=i;
			while(temp!=1){
				if(temp%2==1){
					temp = 3*temp+1;
				}else{
					temp = temp/2;
				}
				time++;
			}
			time++;
			if(max<time)max=time;
		}
		printf("%d %d %d\n",a,b,max);
	}
}