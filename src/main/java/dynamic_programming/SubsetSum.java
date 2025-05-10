package dynamic_programming;

public class SubsetSum {
    static boolean isSubsetSum(int[] arr, int sum) {
        return SubsetSum.TabulationSpaceOptimized.isSubsetSum(arr, sum);
    }

    public static void main(String[] args) {

        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum = 9;

        if (isSubsetSum(arr, sum)) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }
    static class Recursive {
        static boolean isSubsetSum(int[] arr, int n, int sum) {
            if (sum == 0) {
                return true;
            }
            if (n == 0) {
                return false;
            }
            if (arr[n - 1] > sum) {
                return isSubsetSum(arr, n - 1, sum);
            }
            return isSubsetSum(arr, n - 1, sum) ||
                    isSubsetSum(arr, n - 1, sum - arr[n - 1]);
        }
    }
    static class Memoized {
        static boolean isSubsetSum(int[] arr, int n, int sum,
                                      int[][] memo) {
            if (sum == 0) {
                return true;
            }
            if (n <= 0) {
                return false;
            }
            if (memo[n][sum] != -1) {
                return memo[n][sum] == 1;
            }
            if (arr[n - 1] > sum) {
                memo[n][sum] = isSubsetSum(arr, n - 1, sum, memo)
                        ? 1 : 0;
            }
            else {
                memo[n][sum] = (isSubsetSum(arr, n - 1, sum, memo)
                        || isSubsetSum(arr, n - 1, sum - arr[n - 1], memo))
                        ? 1 : 0;
            }

            return memo[n][sum] == 1;
        }
    }
    static class Tabulation {
        static boolean isSubsetSum(int[] arr, int sum) {
            int n = arr.length;
            boolean[][] dp = new boolean[n + 1][sum + 1];
            for (int i = 0; i <= n; i++) {
                dp[i][0] = true;
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= sum; j++) {
                    if (j < arr[i - 1]) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j]
                                || dp[i - 1][j - arr[i - 1]];
                    }
                }
            }

            return dp[n][sum];
        }
    }

    static class TabulationSpaceOptimized {
        public static boolean isSubsetSum(int[] arr, int sum) {
            int n = arr.length;
            boolean[] prev = new boolean[sum + 1];
            boolean[] curr = new boolean[sum + 1];
            prev[0] = true;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= sum; j++) {
                    if (j < arr[i - 1]) {
                        curr[j] = prev[j];
                    }
                    else {
                        curr[j]
                                = prev[j] || prev[j - arr[i - 1]];
                    }
                }
                System.arraycopy(curr, 0, prev, 0, sum + 1);
            }
            return prev[sum];
        }
    }
}
