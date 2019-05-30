import java.util.Scanner;
public class distanceMin {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入第一个字符串word1:");
        String word1 = input.next();
        System.out.print("请输入第一个字符串word2:");
        String word2 = input.next();
        System.out.println("最小编辑距离为"+minDistance(word1, word2));
    }

    public static int minDistance(String word1, String word2) {
//定义一个dp[word1.length() + 1][word2.length() + 1],其中dp[i][j]表示word1前i个字符变成word2前j个字符需要的步数。
        int dp[][] = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i < word1.length() + 1; i++) {
            // 从i个字符变成0个字符，需要i步（删除）
            dp[i][0] = i;
        }
        for (int i = 0; i < word2.length() + 1; i++) {
            // 当从0个字符变成i个字符，需要i步(增加)
            dp[0][i] = i;
        }

        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                //当相同的时，dp[i][j] = dp[i - 1][j - 1]
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //当不同的时候，我们需要求三种操作的最小值
                    //其中dp[i - 1][j - 1]表示的是替换，dp[i - 1][j]表示删除字符，do[i][j - 1]表示的是增加字符
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
