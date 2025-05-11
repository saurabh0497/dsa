package dynamic_programming;

public class Knapsack01 {
    public static void main(String[] args) {
        runTests();
    }

    static void runTests() {
        int[] val = {1, 2, 3};
        int[] wt = {4, 5, 1};
        int W = 4;
        int expected = 3;

        System.out.println("Recursive:         " + test(knapsackRec(W, val, wt), expected));
        System.out.println("Memoization:       " + test(knapsackMemo(W, val, wt), expected));
        System.out.println("Tabulation:        " + test(Tabulation.knapsack(W, val, wt), expected));
        System.out.println("Space Optimized:   " + test(SpaceOptimisedTabulation.knapsack(W, val, wt), expected));

        int[] val2 = {60, 100, 120};
        int[] wt2 = {10, 20, 30};
        int W2 = 50;
        int expected2 = 220;

        System.out.println("Recursive:         " + test(knapsackRec(W2, val2, wt2), expected2));
        System.out.println("Memoization:       " + test(knapsackMemo(W2, val2, wt2), expected2));
        System.out.println("Tabulation:        " + test(Tabulation.knapsack(W2, val2, wt2), expected2));
        System.out.println("Space Optimized:   " + test(SpaceOptimisedTabulation.knapsack(W2, val2, wt2), expected2));
    }

    static String test(int actual, int expected) {
        return "Result: " + actual + " | Expected: " + expected + " | " + (actual == expected ? "PASS" : "FAIL");
    }

    static int knapsackRec(int W, int[] val, int[] wt) {
        return Recursion.knapsackRec(W, val, wt, val.length);
    }

    static int knapsackMemo(int W, int[] val, int[] wt) {
        int n = val.length;
        int[][] memo = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                memo[i][j] = -1;
            }
        }
        return Memoization.knapsackRec(W, val, wt, n, memo);
    }

    static class Recursion {
        static int knapsackRec(int W, int[] val, int[] wt, int n) {
            if (n == 0 || W == 0) return 0;
            int pick = 0;
            if (wt[n - 1] <= W)
                pick = val[n - 1] + knapsackRec(W - wt[n - 1], val, wt, n - 1);
            int notPick = knapsackRec(W, val, wt, n - 1);
            return Math.max(pick, notPick);
        }
    }

    static class Memoization {
        static int knapsackRec(int W, int[] val, int[] wt, int n, int[][] memo) {
            if (n == 0 || W == 0) return 0;
            if (memo[n][W] != -1) return memo[n][W];
            int pick = 0;
            if (wt[n - 1] <= W)
                pick = val[n - 1] + knapsackRec(W - wt[n - 1], val, wt, n - 1, memo);
            int notPick = knapsackRec(W, val, wt, n - 1, memo);
            return memo[n][W] = Math.max(pick, notPick);
        }
    }

    static class Tabulation {
        static int knapsack(int W, int[] val, int[] wt) {
            int n = val.length;
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
        static int knapsack(int W, int[] val, int[] wt) {
            int n = val.length;
            int[] dp = new int[W + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = W; j >= wt[i - 1]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - wt[i - 1]] + val[i - 1]);
                }
            }
            return dp[W];
        }
    }
}
