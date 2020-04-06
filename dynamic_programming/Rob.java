package algorithm.dp;

/**
 * 打家劫舍问题
 * 问题描述：
 *     你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *     给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *     示例 1:
 *       输入: [1,2,3,1]
 *       输出: 4
 *       解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *            偷窃到的最高金额 = 1 + 3 = 4
 */
public class Rob {

    public static void main(String[] args) {
        int[] money = new int[]{1, 2, 3, 1};
        System.out.println(new Rob().maxRob(money));
    }

    public int maxRob(int[] money) {
        if (money.length == 1) {
            return money[0];
        }
        int[] dp = new int[money.length];
        dp[0] = money[0];
        if (money[1] > money[0]) {
            dp[1] = money[1];
        } else {
            dp[1] = dp[0];
        }

        for (int i=2; i<money.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+money[i]);
        }

        return dp[money.length-1];
    }

}
