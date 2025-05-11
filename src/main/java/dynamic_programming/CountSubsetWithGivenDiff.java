package dynamic_programming;

import java.util.Arrays;

public class CountSubsetWithGivenDiff {


//User function Template for Java

//Back-end complete function Template for Java

    class Solution{

        public int countPartitions(int n, int d, int arr[]){
            int sum = Arrays.stream(arr).sum();
            if((sum+d)%2!=0)
                return 0;
            int s = (sum+d)/2;

            int[][]dp = new int[n+1][s+1];
            Arrays.stream(dp).forEach(row -> Arrays.fill(row,-1));
            return perfectSum(arr,n,s);

        }
        static int perfectSum(int arr[], int N, int sum) {
            int mod = (int) 1e9 + 7;
            int[][] dp = new int[N + 1][sum + 1];
            for (int i = 0; i < sum + 1; i++) {
                dp[0][i] = 0;
            }
            dp[0][0] = 1;

            // If sum is not 0 and set is empty,
            // then answer is false


            for (int i = 1; i < N + 1; i++) {
                for (int j = 0; j < sum + 1; j++) {
                    if (arr[i - 1] <= j) {
                        dp[i][j] = ((dp[i - 1][j - arr[i - 1]]) % mod + (dp[i - 1][j]) % mod) % mod;
                    } else
                        dp[i][j] = (dp[i - 1][j]) % mod;
                }
            }
            return dp[N][sum];
        }
    }
}
