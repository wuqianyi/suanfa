import java.util.Scanner;
public class minM {
    private static int[][] f = new int[999][999];
    private static int a[]=new int[999];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入整数序列的个数n:");
         int n = input.nextInt();
        System.out.print("请输入序列分割段数m:");
        int m = input.nextInt();
        System.out.println("m段子序列的和的最大值的最小值为："+minziduanhe(n, m));
    }

    public static int max(int a, int b) {
        int max;
        max = (a > b ? a : b);
        return max;
    }

    public static int minziduanhe(int n,int m){
        Scanner input = new Scanner(System.in);
        int i,j,k;
        System.out.print("请输入序列：");
        for(i=1;i<=n;i++)
            for ( i = 1; i <= n; i++) {
                a[i] = input.nextInt();
            }
        f[0][1]=0;  //初始值
        for(i=1;i<=n;i++){       //f[i][1]表示：前i个整数序列分成一段
            f[i][1]=f[i-1][1]+a[i];       //当前i段和=前i-1段+A[i]
        }
        if(m==1){
            return f[n][1];         //分成一段
        }
        //f[i][j]表示：将前i段数分成j段的最小值

        for(i=1;i<=n;i++){
            for(j=2;j<=m;j++){
                int temp=9999;
                for(k=1;k<=i-1;k++){
                    int min=max(f[i][1]-f[k][1],f[k][j-1]);   //比较取两者大的一个
                    if(min<temp){
                        temp=min;
                        System.out.println("temp="+temp);
                    }
                }
                f[i][j]=temp;
            }
        }

        return f[n][m];
    }
}
