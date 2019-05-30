import java.util.Scanner;
public class packAge {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入物品个数n:");
        int n = input.nextInt();
        System.out.print("请输入背包的最大容量c:");
        int c = input.nextInt();
        System.out.print("请输入背包的最大容积d:");
        int d = input.nextInt();
        System.out.print("请依次输入物品的重量wi:");
        int w[] = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = input.nextInt();
        }
        System.out.print("请依次输入物品的体积bi:");
        int b[] = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = input.nextInt();
        }
        System.out.print("请依次输入物品的价值vi:");
        int v[] = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = input.nextInt();
        }
      /*  int[] v = {6,3,5,4,6};  //价值
        int[] w = {2,2,6,5,4};  //重量
        int[] b = {3,2,5,7,6};  //体积
        int c = 10;  //背包容量
        int d = 12;  //背包容积*/
/*m(i,j,k)是背包容量为j,体积为k,可选择物品为i,i+1,…n时0-1背包问题的最优值
                     max(m(i+1,j,k), m(i+1,j-wi,k-bi)+vi)    ,j>=wi and k>=bi
        m(i,j,k)={
                      m(i+1,j,k)       ,0<=j<wi or 0<=k<bi


                        Vn    ，j>=wn and k>=bn
        m(n,j,k)= {
                         0      ，0<=j<wn or 0<=k<bn
*/

        int[] x = new int[v.length];  //价值
        int[][][] m = new int[v.length][c+1][d+1];  //价值、容量、容积

        knapsack(v,w,b,c,d,m);
        traceback(m,w,c,d,x);


    }


    public static void knapsack(int[] v,int[] w,int[] b,int c,int d,int[][][] m) {
        int n = v.length-1;
        //填满第n行
        int jMax = Math.min(w[n]-1,c); //容量
        int kMax = Math.min(b[n], d);  //容积
        for(int j=0;j<=jMax;j++)
            for(int k=0;k<=kMax;k++)
                m[n][j][k] = 0;//初始化
        for(int j=w[n];j<=c;j++)
            for(int k=b[n];k<=d;k++)
                m[n][j][k] = v[n];

        //填满1~n-1行
        for(int i=n-1;i>0;i--) {
            jMax = Math.min(w[i]-1, c);
            kMax = Math.min(b[i]-1, d);
            for(int j=0;j<=jMax;j++)
                for(int k=0;k<=kMax;k++)
                    m[i][j][k] = m[i+1][j][k];
            for(int j=w[i];j<=c;j++)
                for(int k=b[i];k<=d;k++)
                    m[i][j][k] = Math.max(m[i+1][j][k], m[i+1][j-w[i]][k-b[i]]+v[i]);
        }

        //填第0行
        if(c>=w[0] && d>=b[0]){
            jMax = Math.min(w[0]-1, c);
            kMax = Math.min(b[0]-1, d);
            for(int j=0;j<=jMax;j++)
                for(int k=0;k<=kMax;k++)
                    m[0][j][k] = m[1][j][k];
            for(int j=w[0];j<=c;j++)
                for(int k=b[n];k<=d;k++)
                    m[0][j][k] = Math.max(m[1][j][k], m[1][j-w[0]][k-b[0]]+v[0]);
        }

        //输出m数组  m(i,j,k)是背包容量为j,体积为k,可选择物品为i,i+1,…n时0-1背包问题的最优值
        for(int i=0;i<=n;i++) { //n=5
            for(int j=0;j<=c;j++) {  //c=10  容量
                for(int k=0;k<=d;k++) {  //d=12 容积
//                    System.out.print(m[i][j][k] + "\t");
                }
//                System.out.println();
            }
//            System.out.println();
        }
    }
    //输出哪个物品装入了背包
    public static void traceback(int[][][] m,int[] w,int c, int d,int[] x) {
        int n = w.length-1;
        for(int i=0;i<n;i++) {
            if(m[i][c][d]==m[i+1][c][d]) {x[i]=0;}  //未装入
            else {                                  //装入
                x[i]=1;
                c -= w[i];
            }
            x[n]=(m[n][c][d]>0)?1:0;
        }
        for(int i=0;i<=n;i++) {
            System.out.print(x[i]+" ");
        }
    }
}
