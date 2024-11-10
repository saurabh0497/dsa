package slidingwindow;


/////Variable length sliding window
public class MinimumSizeSubarraySum {

    public int minSubArrayLen(int target, int[] nums) {
        int currentSum = 0;
        int minWindow = Integer.MAX_VALUE;
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            currentSum += nums[r];

            while(currentSum >= target ) {
                minWindow = Math.min(minWindow, r - l + 1);
                currentSum -= nums[l++];

            }
        }
        return minWindow == Integer.MAX_VALUE ? 0 : minWindow;
    }
}
