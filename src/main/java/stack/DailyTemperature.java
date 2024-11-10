package stack;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperature {
    static {
        for (int i = 0; i < 500; i++) {
            dailyTemperatures(new int[]{});
        }
    }

    public static int[] dailyTemperatures(int[] t) {
        int[] res = new int[t.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < t.length; i++) {
            while (!stack.isEmpty() && stack.peek() <= t[i]) {
                res[i] = stack.pop();
            }
            stack.push(t[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(dailyTemperatures(input)));
    }
}
