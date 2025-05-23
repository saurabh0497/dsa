package dynamic_programming;

public class CountSubsetSum {
    public int perfectSum(int[] nums, int k) {
        int n = nums.length;
        int [][] dp = new int[n+1][k+1];
        return solve(nums,n,k,dp);
    }
    public static int solve(int[] nums, int n, int sum, int[][] dp) {
        for (int i = 0; i < n+1; i++){
            dp[i][0] = 1;
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 0; j < sum+1; j++) {
                if (nums[i-1] <= j) {
                    dp[i][j] = dp[i-1][j-nums[i-1]] + dp[i-1][j];
                }
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
        return dp[n][sum];
    }
}
