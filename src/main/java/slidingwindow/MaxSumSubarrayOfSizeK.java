package slidingwindow;


///FixedLengthSlidingWindow
public class MaxSumSubarrayOfSizeK {
    public static int findMaxSubarray(int[] arr, int k) {
        int maxSum = 0;
        int currentSum = 0;

        int l = 0;

        for(int r = 0; r < arr.length; r++) {
            currentSum += arr[r];
            if (r - l + 1 > k) {
                currentSum -= arr[l];
                l++;
            }
            if (r - l + 1 == k) {
                if (currentSum > maxSum) maxSum = currentSum;
            }

        }
        return maxSum;
    }
    public static void main(String[] args) {
        System.out.println(findMaxSubarray(new int[]{4,2,1,7,8,1,2,8,1,0}, 3));
    }
}
