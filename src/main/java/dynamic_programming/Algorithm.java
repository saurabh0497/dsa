package dynamic_programming;

public abstract class Algorithm {
    static String timeComplexity = "O(N)";
    static String spaceComplexity = "O(N)";

    private static String getTimeComplexity() {
        return timeComplexity;
    }

    private static String getSpaceComplexity() {
        return spaceComplexity;
    }

    static String getTimeAndSpaceComplexity() {
        return "Time Complexity: " + getTimeComplexity() + " Space Complexity: " + getSpaceComplexity();
    }
}
