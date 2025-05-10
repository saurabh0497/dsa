package heap;

import java.util.*;

public class TryTopK {
    public static void main(String[] args) {
//        int[] arr = {1,1,1,2,2,2,4};
//        int k = 1;
        int[] arr = new int[]{1,1,1,1,2,2,2,3,3,4,5};
        int k = 3;
        System.out.println(topK(arr, k));

    }
    static List<Integer> topK(int[] arr, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        //list.sort((a, b) -> b.getValue() - a.getValue());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        int cutoffFreq = list.get(Math.min(k - 1, list.size() - 1)).getValue();
        System.out.println(list);
        System.out.println(cutoffFreq);

        List<Integer> ans = new ArrayList<>();
        for (var entry : list) {
            if (entry.getValue() >= cutoffFreq) {
                ans.add(entry.getKey());
            }
        }
        return ans;
    }
}
