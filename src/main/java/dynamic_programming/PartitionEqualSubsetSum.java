package dynamic_programming;

import java.util.Arrays;

public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1,2,3,4,5,6,7}));
    }
    public static boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum =  Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        int m = sum/2;
        boolean [][] dp = new boolean[n+1][m+1];
        return solve(nums,n, m,dp);
    }
    public static boolean solve(int[] nums, int n, int sum, boolean[][] dp) {
        for (int i = 0; i < n+1; i++){
            dp[i][0] = true;
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < sum+1; j++) {
                if (nums[i-1] <= j) {
                    dp[i][j] = dp[i-1][j-nums[i-1]] || dp[i-1][j];
                }
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
        return dp[n][sum];
    }
}
