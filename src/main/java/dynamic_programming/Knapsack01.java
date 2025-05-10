package dynamic_programming;

public class Knapsack01 {
    static int knapsack(int W, int[] val, int[] wt) {
        int n = val.length;
        return Recursion.knapsackRec(W, val, wt, n);
    }

    public static void main(String[] args) {
        int[] val = {1, 2, 3};
        int[] wt = {4, 5, 1};
        int W = 4;

        System.out.println(knapsack(W, val, wt));
    }

    static class Recursion {
        static final String timeComplexity = "Time Complexity: O(2^n)";
        static final String spaceComplexity = "Space Complexity: O(n)";
        static int knapsackRec(int W, int[] val, int[] wt, int n) {

            // Base Case
            if (n == 0 || W == 0)
                return 0;
            int pick = 0;

            if (wt[n - 1] <= W)
                pick = val[n - 1] + knapsackRec(W - wt[n - 1], val, wt, n - 1);

            int notPick = knapsackRec(W, val, wt, n - 1);

            return Math.max(pick, notPick);
        }
    }

    static class Memoization {
        static final String timeComplexity = "Time Complexity: O(n*W)";
        static final String spaceComplexity = "Space Complexity: O(n*W)";
        static int knapsackRec(int W, int[] val, int[] wt, int n, int[][] memo) {
            if (n == 0 || W == 0)
                return 0;
            if (memo[n][W] != -1)
                return memo[n][W];
            int pick = 0;
            if (wt[n - 1] <= W)
                pick = val[n - 1] + knapsackRec(W - wt[n - 1], val, wt, n - 1, memo);
            int notPick = knapsackRec(W, val, wt, n - 1, memo);
            return memo[n][W] = Math.max(pick, notPick);
        }
    }

    static class Tabulation {
        static final String timeComplexity = "Time Complexity: O(n*W)";
        static final String spaceComplexity = "Space Complexity: O(n*W)";
        static int knapsack(int W, int[] val, int[] wt) {
            int n = wt.length;
            int[][] dp = new int[n + 1][W + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= W; j++) {
                    if (i == 0 || j == 0)
                        dp[i][j] = 0;
                    else {
                        int pick = 0;
                        if (wt[i - 1] <= j)
                            pick = val[i - 1] + dp[i - 1][j - wt[i - 1]];
                        int notPick = dp[i - 1][j];

                        dp[i][j] = Math.max(pick, notPick);
                    }
                }
            }
            return dp[n][W];
        }
    }

    static class SpaceOptimisedTabulation {
        static final String timeComplexity = "Time Complexity: O(n*W)";
        static final String spaceComplexity = "Space Complexity: O(W)";
        static int knapsack(int W, int[] val, int[] wt) {
            int[] dp = new int[W + 1];
            for (int i = 1; i <= wt.length; i++) {

                // Starting from back, so that we also have data of
                // previous computation of i-1 items
                for (int j = W; j >= wt[i - 1]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - wt[i - 1]] + val[i - 1]);
                }
            }
            return dp[W];
        }
    }
}
