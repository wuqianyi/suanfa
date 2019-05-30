#include<stdio.h>
#include<string.h>
int main()
{
    char n[999];
	int k,len;          
	printf("请输入整数：");//178543
    scanf("%s",n);
	printf("请输入要删除的位数：");
	scanf("%d",&k);   
    len=strlen(n);  
    if(k>len){
    	printf("错误！！！");
		return; 
	}
	int i=0;
	for(i=0;i<k;i++){  //控制删除的k个数 
		int j=0;
		while(j<len&&n[j]<=n[j+1]){   //每次找到一个元素，使其满足它大于下一个元素
			j++; 
		}
		printf("第%d次删除的数为%d",i+1,n[j]-'0');
		int m;
		for(m=j;m<len;m++)
			n[m]=n[m+1]; //覆盖删除 
		len--;
		
		printf("   删除后的结果为%s\n",n);
	} 

	printf("%s\n",n);
	return;
}


