package heap;

import java.util.*;

public class TopKFrequentWithTies {
    public static List<Integer> getTopNWithTies(int[] nums, int topN) {
        // Count frequencies
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums)
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        System.out.println("map: " + freqMap);
        // Convert to list and sort by frequency descending
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(freqMap.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());
        System.out.println("entries: " + entries);
        // Get cutoff frequency
        int cutoffFreq = entries.get(Math.min(topN - 1, entries.size() - 1)).getValue();
        System.out.println("cutoffFreq: " + cutoffFreq);
        // Collect all keys with frequency >= cutoff
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : entries) {
            if (entry.getValue() >= cutoffFreq)
                result.add(entry.getKey());
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(getTopNWithTies(new int[]{1, 1, 1, 2, 2}, 1));         // [1]
        System.out.println("-----------------------------------------------------");
        System.out.println(getTopNWithTies(new int[]{1, 1, 1, 2, 2, 2, 3}, 1));
        System.out.println("-----------------------------------------------------");
        System.out.println(getTopNWithTies(new int[]{1,2,3,4,5,6,7}, 3));// [1, 2]
    }
}
class TopNWithTiesHeap {
    public static List<Integer> getTopNWithTies(int[] nums, int topN) {
        // Step 1: Count frequencies
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums)
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

        // Step 2: Min-heap to get top N frequencies
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>(Map.Entry.comparingByValue());

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > topN) {
                minHeap.poll();
            }
        }
        System.out.println("minHeap: " + minHeap);

        // Step 3: Determine the cutoff frequency
        int cutoffFreq = minHeap.peek().getValue();

        // Step 4: Collect all elements with freq >= cutoff
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() >= cutoffFreq) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(getTopNWithTies(new int[]{1, 1, 1, 2, 2}, 1));         // [1]
        System.out.println(getTopNWithTies(new int[]{1, 1, 1, 2, 2, 2, 3}, 1));   // [1, 2]
    }
}
