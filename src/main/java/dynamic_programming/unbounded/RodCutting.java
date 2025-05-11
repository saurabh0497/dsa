package dynamic_programming.unbounded;

public class RodCutting {
    public int cutRod(int[] price) {
        int n = price.length;
        int[] arr = new int[n];
        int[][] dp = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            arr[i-1] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i-1] > j) {
                    dp[i][j] = dp[i-1][j];
                }
                else {
                    dp[i][j] = Math.max(price[i-1] + dp[i][j-arr[i-1]], dp[i-1][j]);
                }
            }
        }
        return dp[n][n];
    }
}
