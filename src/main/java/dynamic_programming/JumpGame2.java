package dynamic_programming;

import java.util.Arrays;

public class JumpGame2 {
    public static void main(String[] args) {
        System.out.println(Greedy.jump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(Greedy.timeComplexity);
        System.out.println(Greedy.spaceComplexity);


        System.out.println(DynamicProgramming.jump(new int[]{2, 3, 1, 1, 4}));

    }
    static class Greedy {
        static final String timeComplexity = "O(N)";
        static final String spaceComplexity = "O(1)";
        public static int jump(int[] nums) {
            int jumps = 0, farthest = 0, currentEnd = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                farthest = Math.max(farthest, i + nums[i]);

                if (i == currentEnd) {
                    jumps++;
                    currentEnd = farthest;
                }
            }
            return jumps;
        }
    }
    static class DynamicProgramming {
        static final String timeComplexity = "O(N)";
        static final String spaceComplexity = "O(N)";
        public static int jump(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n+1];
            Arrays.fill(dp, -1);
            return solve(nums, 0, dp);
        }
        public static int solve(int[] nums, int index, int[] dp) {
            if (index >= nums.length - 1) {
                return 0;
            }
            if (dp[index] != -1) {
                return dp[index];
            }
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= nums[index]; i++) {
                int jump = solve(nums, index+ i,dp);
                if(jump != Integer.MAX_VALUE) {
                    min = Math.min(min, 1 + jump);
                }
            }
            dp[index] = min;
            return min;
        }
    }
}
