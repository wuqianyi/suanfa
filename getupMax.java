import java.util.Scanner;
public class getupMax {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入数组个数：");
        int N = Integer.parseInt(input.nextLine());
        int array[] = new int[N];
        System.out.print("请输入数组数据：");
        for (int i = 0; i < array.length; i++) {
            array[i] = input.nextInt();
        }

        System.out.println("\n最长单调递增子序列长度为：" + getLength(array));
    }
private static int getLength(int[] array) {
    int[] tempArray = new int[array.length];
    tempArray[0] = array[0];
    int length = 0;
    for (int number : array) {
        //System.out.println("number="+number+"  tempArray["+length+"]="+tempArray[length]);
        if (tempArray[length] < number) {   //如果大于temp中的元素，则直接插入到temp数组的末尾
            tempArray[++length] = number;
        } else {         //二分法查找需要插入的位置
            int low = 0;
            int high = length;
            while (low < high) {
                int middle = (low + high) >> 1;
                if (tempArray[middle] < number) {
                    low = middle + 1;
                } else {
                    high = middle - 1;
                }
            }
            if (tempArray[low] < number && (low + 1) <tempArray.length) {
               tempArray[low + 1] = number;
            } else {
                tempArray[low] = number;
            }
        }
    }
    System.out.print("最长单调递增子序列为：");
    for (int i=0;i<=length;i++) {
        System.out.print(tempArray[i]+" ");
    }
    return length + 1;
}

}