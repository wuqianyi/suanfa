import java.util.Scanner;
public class Queen {
    static int n;    //皇后个数
    static int[] x;  //当前解
    static long sum; //当前已找到的可行方案数  子集数
    private static void nQueen(int m){
        n = m;
        sum = 0;
        x = new int[n+1];
        for(int i=0;i<=n;i++) x[i] = 0;
        backtrack();
    }
    private static boolean Place(int k){  //判断位置是否合适
        for(int j=1;j<k;j++)
            if((Math.abs(k-j)==Math.abs(x[k]-x[j]))||(x[k]==x[j]))
                return false;
        return true;
    }
    private static void backtrack(){
        x[1] = 0;
        int k = 1; //第一行
        while(k>0){
            System.out.println("---k="+k);
            //若出现第一个可行解,即此时k=n,需要把最后一行确定的列坐标+1,避免出现一个解就死循环当前解
            System.out.println("x["+k+"]="+x[k]);
            x[k] += 1;  //先将皇后放在第一列位置
            System.out.println((x[k]<=n)&&(!Place(k)));
            while((x[k]<=n)&&(!Place(k)))  //位置不合法   false表示位置合法
            {
                x[k] += 1;  //列数+1  寻找能够防止皇后的位置
                System.out.println("位置不合法后重新找位置为"+"x["+k+"]="+x[k]);
            }
            if(x[k]<=n){  //找到位置
                if(k==n) {  //如果寻找结束输出结果
                    for(int i=1;i<=n;i++){
                        System.out.print(x[i]+"   ");
                    }
                    System.out.println();
                    sum++;
                }
                else{ //没有结束寻找下一行
                    k++;       //向下一行扫描
                    x[k] = 0;  //下一行的确定列初始为"0"
                }
            }else {
                k--; //回溯到上一行,排列树中则回溯到父节点  没有找到合适的位置则回溯
                System.out.println("----------------------k="+k);
            }
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入皇后个数n:");
        int n = input.nextInt();
        nQueen(n);
        System.out.println(n+"个皇后共有："+sum+"种解");
    }
}