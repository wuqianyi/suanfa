#include<stdio.h>
#include<string.h>
int main()
{
    char n[999];
	int k,len;          
	printf("������������");//178543
    scanf("%s",n);
	printf("������Ҫɾ����λ����");
	scanf("%d",&k);   
    len=strlen(n);  
    if(k>len){
    	printf("���󣡣���");
		return; 
	}
	int i=0;
	for(i=0;i<k;i++){  //����ɾ����k���� 
		int j=0;
		while(j<len&&n[j]<=n[j+1]){   //ÿ���ҵ�һ��Ԫ�أ�ʹ��������������һ��Ԫ��
			j++; 
		}
		printf("��%d��ɾ������Ϊ%d",i+1,n[j]-'0');
		int m;
		for(m=j;m<len;m++)
			n[m]=n[m+1]; //����ɾ�� 
		len--;
		
		printf("   ɾ����Ľ��Ϊ%s\n",n);
	} 

	printf("%s\n",n);
	return;
}


