# Notes

## Two Pointers
Two pointers is a common interview technique often used to solve certain problems involving an iterable data structure, such as an array. As the name suggests, this technique uses two (or more) pointers that traverse through the structure. It does not have to be physically using two pointers. As long as the other pointer can be easily calculated from existing values, such as the index of the other pointer, it counts as a two pointer question.

Since "two pointers" is kind of a broad topic, there is no singular way to implement it. Depending on the questions you encounter, you need to implement the answer differently. Generally speaking, a two pointer algorithm has these characteristics:

Two moving pointers, regardless of directions, moving dependently or independently;
A function that utilizes the entries referenced by the two pointers, which relates to the answer in a way;
An easy way of deciding which pointer to move;
A way to process the array when the pointers are moved.
There are a lot of ways to classify two pointer problems. Below are some classifications, although they are in no way exhaustive.

### Classification
1. Same Directions:
These questions have two pointers that move in the same direction. Here is an example of a same direction two pointer question: Remove Duplicates.

2. Opposite Direction: These questions have two pointers that move in the opposite direction. Here is an example of an opposite direction two pointer question: Two Sum Sorted.

Two Pointers vs Sliding Window:
Sliding window problems are similar to the same directions problems, only instead, the function performs on the entire interval between the two pointers. Usually, however, we keep track of the cumulative result of the window, and each time we insert/remove an item from the window, we simply update the window according to the changes instead of recalculating everything.

As an example, Longest Substring without Repeating Characters is a classic sliding window problem.

Non-array Applications
The two-pointer technique is not limited to arrays. Two pointer can be done on other structures, like linked list, as long as they are iterable.

For example, in Happy Number, you are asked to detect a cycle from a linked list structure, and it can be solved using a two-pointer technique called Floyd's Cycle-Finding Algorithm.

Why Use Two Pointers?
Two pointers are helpful because it often offers a more efficient solution than the naive solution. From the examples above, if we use the naive solution and use two loops to iterate through the array, the time complexity would typically be O(n^2), which is generally insufficient. If we use two pointers for this type of problem, we are often only passing through the array once with the two pointers, which means that the time complexity is often O(n).
##  Sliding Window

Sliding window problems are a variant of the same direction two pointers problems. The function performs on the entire interval between the two pointers instead of only at the two positions. Usually, we keep track of the overall result of the window, and when we "slide" the window (insert/remove an item), we simply manipulate the result to accommodate the changes to the window. Time complexity wise, this is much more efficient as we do not recalculate the overlapping intervals between two windows over and over again. We try to reduce a nested loop into two passes on the input (one pass with each pointer).

### Why is Sliding Window required??
Through the brute force we do unnecessary iteration over elements we have already seen.
This is duplicated work and typically yields on O(N*K) time complexity.

### Recognising these problems in the wind
1. Contiguous sequence of elements
2. Strings, arrays, linkedlist
3. min, max, longest, shortest, contained in window
4. 
1. Fixed Length
```java
class SlidingWindow {
    private static W slidingWindowFixed(List<T> input, int windowSize) {
        W ans = window = input.subList(0, windowSize);
        for (int right = windowSize; right < input.length(); ++right) {
            int left = right - windowSize;
            //remove input[left]from window
            //append input[right]to window
            //ans = optimal(ans, window);
        }
        return ans;
    }
}
```
