import java.util.Scanner;
public class Car {
    public static int N;  //方格网络规模
    public static int K;  //装满油后，能行驶K条边
    public static int A;  //汽车在行驶过程中遇到油库应加满油并付加油费A
    public static int B;  //当汽车行驶经过一条网格边时，如果其x坐标或y坐标减少，应付费用B
    public static int C;  //在需要时可在网格点处增设油库，并付增设油库费用C（不含加油费A）


    private static int[][][] f = new int[50][50][2];
    private static int[][] s = {{-1,0,B},{0,-1,B},{1,0,0},{0,1,0}};   //左，上，右，下


    private static int[][] a = new int[50][50];  //方形网络

    private static int MAX = 100000;
    private static int MIN;

    public static void main(String[] args){

        /*
        f[i][j][0]表示汽车从网格点(1,1)行驶至网格点(i,j)所需的最少费用
        f[i][j][1]表示汽车行驶至网格点(i,j)后还能行驶的网格边数

        s[i][0]表示x轴方向
        s[i][1]表示y轴方向
        s[i][2]表示行驶费用

        f[i][j][0] = min{f[ i+s[k][0] ] [ [j+s[k][1] ][0] + s[k][2]}
        f[i][j][1] = f[ i+s[k][0] ][ [j+s[k][1] ][1] - 1

        f[1][1][0] = 0
        f[1][1][1] = K
        f[i][j][0] = f[i][j][0] + A , f[i][j][1] = K   如果(i, j)是油库
        f[i][j][0] = f[i][j][0] + C + A, f[i][j][1] = K  (i, j)不是油库，且f[i][j][1] = 0
        */

        /*
        input data:
        9 3 2 3 6
        0 0 0 0 1 0 0 0 0
        0 0 0 1 0 1 1 0 0
        1 0 1 0 0 0 0 1 0
        0 0 0 0 0 1 0 0 1
        1 0 0 1 0 0 1 0 0
        0 1 0 0 0 0 0 1 0
        0 0 0 0 1 0 0 0 1
        1 0 0 1 0 0 0 1 0
        0 1 0 0 0 0 0 0 0
        */

        Scanner input = new Scanner(System.in);
        System.out.print("请输入网格规模N:");
        N = input.nextInt();//网格规模
        System.out.print("请输入装满油后能行驶的网格边数K:");
        K = input.nextInt();//装满油后能行驶的网格边数
        System.out.print("请输入加油费A:");
        A = input.nextInt();//加油费
        System.out.print("请输入坐标减少时应付的费用B:");
        B = input.nextInt();//坐标减少时应付的费用
        System.out.print("请输入增设油库的费用C:");
        C = input.nextInt();//增设油库费用
        System.out.println("请输入网格规模N*N:");
        for(int i=1; i<=N; i++){//输入方形网络
            for(int j=1; j<=N; j++){
                a[i][j] = input.nextInt();
            }
        }
        if(a[N][N]==1) {
            a[N][N] = 0;    //终点不设加油站，防止输入有误
            System.out.println("正确的网格为：");
            int count=0;
            for(int i=1; i<=N; i++){//打印网格
                for(int j=1; j<=N; j++){
                    System.out.print(a[i][j]+" ");
                    count++;
                    if(count%3==0)
                        System.out.println();
                }
            }
        }

        System.out.println("最小费用为："+dynamic());//最优行驶路线所需的费用，即最小费用
    }

    public static int dynamic(){

        int i, j, k;
        for (i=1;i<=N;i++){
            for (j=1;j<=N;j++){
                f[i][j][0]=MAX;   //初始化-----汽车从网格点(1,1)行驶至网格点(i,j)所需的最少费用初始化为MAX,除过起点位置
                f[i][j][1]=K;     //初始化-----汽车行驶至网格点(i,j)后还能行驶的网格边数初始化为K
            }
        }

        f[1][1][0] = 0;
        f[1][1][1] = K;

        boolean flag = false;    //设置标志
        int x, y;
        while(!flag){
            flag = true;
            for(i=1; i<=N; i++){
                for(j=1; j<=N; j++){
                    if(i==1 && j==1)
                        continue;
                    int minFee = MAX;         //初始化最小费用
                    int driveEdges = MAX;     //初始化可以行使的网格数
                    int fee, canDriveEdges;   //费用，可以行使的网格数
                    for(k=0; k<4; k++){       //可走的四个方向
                        System.out.println("k="+k+"  i="+i  +"   j="+j);
                        x = i + s[k][0];      // s[i][0]表示x轴方向
                        System.out.print("s["+k+"][0]="+s[k][0]+"  x="+x+" ");
                        y = j + s[k][1];      //s[i][1]表示y轴方向       //s[i][2]表示行驶费用
                        System.out.println("s["+k+"][1]="+s[k][1]+"  y="+y+" ");
                        if(x<1 || y<1 || x>N || y>N) { //如果出界
                            System.out.println("出界");
                            continue;
                        }
                        System.out.print("f["+x+"]["+y+"][0]="+f[x][y][0]);
                        System.out.println(fee = f[x][y][0] + s[k][2]);
                        System.out.println("f["+x+"]["+y+"][0]="+f[x][y][0]);
                        System.out.println(canDriveEdges = f[x][y][1] - 1);
                        if(a[i][j] == 1){  //如果是油库
                            fee += A;
                            canDriveEdges = K;
                        }
                        if(a[i][j]==0 && canDriveEdges == 0 && (i!=N||j!=N)){  //如果不是油库,且油已经用完
                            fee += A + C;
                            canDriveEdges = K;
                        }
                        if(fee < minFee){  //记录更少费用
                            minFee = fee;
                            driveEdges = canDriveEdges;
                        }
                    }

                    if(f[i][j][0] > minFee){  //如果有更优解
                        flag = false;
                        f[i][j][0] = minFee;
                        f[i][j][1] = driveEdges;
                    }
                }
            }
        }
        return f[N][N][0];//汽车从网格点(1,1)行驶至网格点(N,N)所需的最少费用（亦即从起点到终点），此为所求
    }
}

