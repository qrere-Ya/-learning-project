#include <stdio.h>
#include <math.h>
int main(){
	double s,a,r,min,deg;
	char T[100];
	const double pi = acos(-1);
	while(scanf("%lf %lf",&s,&a)==2){
		scanf("%s",T);
		r=6440+s;
		if(T[0]=='m'){
			min=a*pi/(180*60);
			printf("%.6f %.6f\n",r*min,2*r*sin(min / 2.0));
		}else if(T[0]=='d'){
			deg=a*pi/180;
			printf("%.6f %.6f\n",r*deg,2*r*sin(deg/2.0));
		}
	}
}