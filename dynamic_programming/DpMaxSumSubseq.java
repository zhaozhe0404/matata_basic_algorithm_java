package algorithm.dp;

/**
 * 最大连续子序列之和问题
 * 问题描述：给定一个整数数组，找到一个具有最大和的连续子数组（子数组至少包含一个元素），返回其最大和
 */
public class DpMaxSumSubseq {
    public static void main(String[] args) {
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new DpMaxSumSubseq().getMaxSum(arr));
    }

    /**
     * dp[i]代表“以i元素结尾的连续子序列的最大和”
     * @param arr
     * @return
     */
    public int getMaxSum(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int max = dp[0];

        for (int i=1; i<arr.length; i++) {
            if (dp[i-1] > 0) { // 若dp[i-1]小于0，则dp[i]加上前面的任意长度的序列和都会小于不加前面的序列（即自己本身一个元素是以自己为结尾的最大自序和）
                dp[i] = dp[i-1] + arr[i];
            } else {
                dp[i] = arr[i];
            }
            max = Math.max(dp[i], max);
        }

        return max;
    }
}
