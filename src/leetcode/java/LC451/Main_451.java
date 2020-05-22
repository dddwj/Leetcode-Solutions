package leetcode.java.LC451;

import java.util.*;

public class Main_451 {

    public static void main(String[] args) {
        Main_451 main_451 = new Main_451();
        System.out.println(main_451.frequencySort("leetcodo"));
    }


    // Solution: HashTable + Heap
    // Time: O(N + NlgM),  N: len(s),  M: distinguish characters
    // Space: O(128 + M) == O(1)
    // Ref: https://leetcode-cn.com/problems/sort-characters-by-frequency/solution/java-heap-by-chris_wing/
    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int[] letters = new int[128];
        for (char ch : s.toCharArray()) {
            letters[ch]++;
        }

        PriorityQueue<Character> heap = new PriorityQueue<>(128, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return letters[o2] - letters[o1];       // NOTE: Priority Queue + Comparator的写法！
            }
        });
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] != 0)
                heap.offer((char)i);
        }

        StringBuilder sb = new StringBuilder();
        while (!heap.isEmpty()) {
            char ch = heap.poll();
            while (letters[ch] > 0) {
                sb.append(ch);
                letters[ch]--;
            }
        }

        return sb.toString();
    }
}
