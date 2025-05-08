package dynamic_programming;

/*
 * @Author
 *
 *
 * */

import java.util.Arrays;

public class JumpGame {

    public static void main(String[] args) {

        System.out.println(DynamicProgramming.canJump(new int[]{2,3,0,0,4}));
    }


    //Dynamic Programming
    // Time complexity:
    // Space Complexity:
    static class DynamicProgramming {
        public static boolean canJump(int[] nums) {
            return canReach(nums, 0);
        }

        private static boolean canReach(int[] nums, int position) {
            // Base case: reached or passed the last index
            if (position >= nums.length - 1) return true;

            // Try all jumps from 1 to nums[position]
            for (int jump = 1; jump <= nums[position]; jump++) {
                if (canReach(nums, position + jump)) {
                    return true;
                }
            }

            return false; // No path worked
        }
//        public static boolean canJump(int[] nums) {
//            int[] dp = new int[nums.length];
//            Arrays.fill(dp, -1);
//            return solve(nums, 0, dp) != Integer.MIN_VALUE;
//        }
//
//        public static int solve(int[] nums, int index, int[] dp) {
//            if (index >= nums.length - 1) {
//                return 0;
//            }
//            if (dp[index] != -1) { return dp[index]; }
//            int min = Integer.MAX_VALUE;
//            for (int i = 1; i <= nums[index]; i++) {
//                var jump = solve(nums, i + index,dp);
//                if (jump != Integer.MAX_VALUE) {
//                    min = Math.min(min, 1 + jump);
//                }
//            }
//            return min;
//        }
    }


    // Greedy Solution
    // Time complexity: O(N)
    // Space Complexity: O(N)
    static class Greedy {

        public boolean canJump(int[] nums) {
            return false;
        }
    }

}
