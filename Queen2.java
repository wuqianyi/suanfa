import java.util.Scanner;
public class Queen3 {
    static int n;    //皇后个数
    static int []x;//当前解
    static int sum; //当前已找到的可行方案数
    static int[] a;
   // max表示n个皇后 用array[n]表示皇后在第n+1行,array[n]列,比如array[0] = 8 意思为:该皇后位于第1行第8列的坐标;
    //排列树与子集树的区别在于子集树不需要初始化而排列树需要,此初始化内容为众多可能解集合(注:是可能解,不一定为正确解)中的一个
    //初始化为a[]数组(1-n随意排列);a[]中只是可能性,所以要将所有可能性用for循环表示.即:for(int i = 0;i < n ; i++);
   //将a数组的值向x中输出,初始传入n为0;表示该皇后在第一行,但具体哪列不确定,此时初始化的a数组就起到了作用,a[n]表示在n+1行的第a[n]列, 将其赋值给x,即: x[n] = a[i];
   //然后更新a数组 swap(a,n,i) (意为 既然已经使用过a[i]那就用原本的a[n]替换a[i] 保证列值不重复)
   //更新后判断该位置是否与已经存在的皇后的位置冲突(同斜线) 已经通过a数组和n已经去除掉同行同列的可能;n保证不同行,a[i]保证不同列;
    //如果合法,则进入n+1;重复讨论n+2个皇后的位置 /如果不合法,交换回之前位置(只有合法之后才能占该列值) i++;
    //当i++到for循环结束,也就是说该皇后在这一行所有列中都没有找到合适自己的位置,回退,即该方法执行结束,重新讨论之前上个皇后的位置;

    public static void nQueen(int m){
        n=m;
        sum=0;
        a=new int[n];
        x=new int[n];    //当前解
        for(int i=0;i<n;i++){   //初始化，列没有重复值
            a[i]=i+1;
        }
        backtrack(0);
    }
    public static void backtrack(int t) {
        if (t == n) {
            output();
            return;
        } else {
            for (int i = t; i < n; i++) {
                x[t] = a[i];
                System.out.println("x["+t+"]="+x[t]);
                swap(a, t, i);
                System.out.print("交换：");
                for(int k=0;k<x.length;k++)
                System.out.print(a[k]+" ");
                System.out.println();
                if (Place(t)) {//如果位置不合法,交换回之前位置(只有合法之后才能占该列值)
                    backtrack(t+ 1);
                }
                swap(a, t, i);
                System.out.print("位置不合法，交换回之前位置：");
                for(int j=0;j<x.length;j++)
                    System.out.print(a[j]+" ");
                System.out.println();
            }
        }
    }
    public static boolean Place(int t) {
        for (int i = 0; i < t; i++) {
            if (Math.abs(t - i) == Math.abs(x[t] - x[i])) {
                return false;
            }
        }
        return true;
    }
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void output() {
        ++sum;
        System.out.print("第" + sum + "种解法:");
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入皇后个数m:");
        int m = input.nextInt();
        new Queen3().nQueen(m);
    }
}