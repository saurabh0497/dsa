import java.util.Arrays;

class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        int count = 0;

        // Sort nums3 and nums4 to apply two-pointer technique
        Arrays.sort(nums3);
        Arrays.sort(nums4);

        // Iterate through each pair (nums1[i], nums2[j])
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int target = -(nums1[i] + nums2[j]);

                // Apply two-pointer technique on nums3 and nums4 to find the complement
                int k = 0;
                int l = n - 1;

                while (k < n && l >= 0) {
                    int sum = nums3[k] + nums4[l];

                    if (sum == target) {
                        // Found a valid quadruplet
                        count++;

                        // Skip duplicates in nums3 (move k to the next unique element)
                        k++;

                        // Move both pointers to the next positions after counting a valid pair
                        l = n-1;

                    } else if (sum < target) {
                        // If the sum is too small, move the left pointer to the right to increase the sum
                        k++;
                    } else {
                        // If the sum is too large, move the right pointer to the left to decrease the sum
                        l--;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case
        int[] nums1 = {0, 1, -1};
        int[] nums2 = {-1, 1, 0};
        int[] nums3 = {0, 0, 1};
        int[] nums4 = {-1, 1, 1};

        int result = solution.fourSumCount(nums1, nums2, nums3, nums4);
        System.out.println("The number of tuples is: " + result);  // Expected output: 17
    }
}
