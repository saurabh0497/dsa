package stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class NextGreaterElement {

    ///// loop starts from 0 to n
    public ArrayList<Integer> nextLargerElement(int[] arr) {

        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        ArrayList<Integer> al = new ArrayList<>(Collections.nCopies(n, -1));
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                al.set(stack.pop(), arr[i]);
            }
            stack.push(i);
        }
        return al;
    }

    ////loop starts from n to 0
    public ArrayList<Integer> nextLargerElement2(int[] arr) {

        Stack<Integer> stack = new Stack<>();
        int j = 0;
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }

            int nge = stack.isEmpty() ? -1 : stack.peek();
            al.add(nge);
            stack.push(arr[i]);
        }
        Collections.reverse(al);
        return al;
    }
}
