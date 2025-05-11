package dynamic_programming.unbounded;

public class CoinChange {
    public static void main(String[] args) {
        runTests();
    }

    static final int INF = Integer.MAX_VALUE - 1;

    static void runTests() {
        System.out.println("Top-Down (Memoized):");
        System.out.println(test("Coins: [1,2,5], Amount: 5", TopDown.coinChange(new int[]{1, 2, 5}, 5), 1));
        System.out.println(test("Coins: [2], Amount: 3", TopDown.coinChange(new int[]{2}, 3), -1));

        System.out.println("\nBottom-Up (Tabulation):");
        System.out.println(test("Coins: [1], Amount: 1", BottomUp.coinChange(new int[]{1}, 1), 1));
        System.out.println(test("Coins: [1,2,5], Amount: 5", BottomUp.coinChange(new int[]{1, 2, 5}, 5), 1));
        System.out.println(test("Coins: [2,5,10,1], Amount: 27", BottomUp.coinChange(new int[]{2, 5, 10, 1}, 27), 4));
        System.out.println(test("Coins: [186,419,83,408], Amount: 6249", BottomUp.coinChange(new int[]{186, 419, 83, 408}, 6249), 20));
    }

    static String test(String description, int actual, int expected) {
        return description + " => Result: " + actual + " | Expected: " + expected + " | " + (actual == expected ? "PASS" : "FAIL");
    }

    static class TopDown {
        static int coinChange(int[] coins, int amount) {
            int n = coins.length;
            int[][] dp = new int[n + 1][amount + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= amount; j++) {
                    dp[i][j] = -1;
                }
            }
            int ans = solve(coins, amount, n, dp);
            return ans == INF ? -1 : ans;
        }

        static int solve(int[] coins, int amount, int n, int[][] dp) {
            if (amount == 0) return 0;
            if (n == 0) return INF;
            if (dp[n][amount] != -1) return dp[n][amount];

            if (coins[n - 1] <= amount) {
                dp[n][amount] = Math.min(
                        1 + solve(coins, amount - coins[n - 1], n, dp),
                        solve(coins, amount, n - 1, dp)
                );
            } else {
                dp[n][amount] = solve(coins, amount, n - 1, dp);
            }
            return dp[n][amount];
        }
    }

    static class BottomUp {
        static int coinChange(int[] coins, int amount) {
            int n = coins.length;
            int[][] dp = new int[n + 1][amount + 1];

            for (int i = 0; i <= n; i++) {
                dp[i][0] = 0;
            }

            for (int j = 1; j <= amount; j++) {
                dp[0][j] = INF;
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= amount; j++) {
                    if (coins[i - 1] <= j) {
                        dp[i][j] = Math.min(
                                1 + dp[i][j - coins[i - 1]],
                                dp[i - 1][j]
                        );
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[n][amount] == INF ? -1 : dp[n][amount];
        }
    }
}
