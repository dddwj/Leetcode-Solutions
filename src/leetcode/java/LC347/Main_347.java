package leetcode.java.LC347;

import java.util.*;

public class Main_347 {


    public static void main(String[] args) {
        Main_347 main_347 = new Main_347();
        System.out.println(main_347.topKFrequent(new int[]{1, 2, 2, 3, 3, 4, 4, 4}, 3));
    }


    // Solution1: Frequency Map  +   Top K Sort (Heap)
    // Time: O(N + NlogK) = O(NlogK)
    // Space:  O(N) (for hashmap)
    public List<Integer> topKFrequent(int[] nums, int k) {
        // build hash map : character and how often it appears
        HashMap<Integer, Integer> freqMap = new HashMap();
        for (int n: nums) {
            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
        }


        // init heap 'the less frequent element first'
        // NOTE: To sort in a min-heap with K elements, time complexity: O(NlogK)
             // Opt1: Lambda Function
             // PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> freqMap.get(n1) - freqMap.get(n2));

             // Opt2: Override Comparator
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return freqMap.get(a) - freqMap.get(b);
            }
        });


        // keep k top frequent elements in the heap
        for (int n: freqMap.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // build output list (reverse the heap, so that: largest frequency first)
        List<Integer> top_k = new LinkedList();
            // Opt1:
        while (!heap.isEmpty())
            top_k.add(0, heap.poll());
            // Opt2:
            // while (!heap.isEmpty())
            //     top_k.add(heap.poll());
            // Collections.reverse(top_k);
        return top_k;
    }



    // My Solution: Frequency Map  +   Top K Sort
    // Time: O(N ~ N*N)??
    // Space: O(N + K)
    public List<Integer> topKFrequent_mysolution(int[] nums, int k) {
        HashMap<Integer, Integer> freqMap = new HashMap();

        for (int num : nums) {
            int freq = freqMap.getOrDefault(num, 0) + 1;
            freqMap.put(num, freq);
        }

        Integer[] topKKey = new Integer[k];
        Integer[] topKFreq = new Integer[k];
        Arrays.fill(topKKey, 0);
        Arrays.fill(topKFreq, 0);
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int key = entry.getKey();
            int freq = entry.getValue();

            for (int i = 0; i < k; i++) {
                if (freq > topKFreq[i]) {
                    for (int j = k-1; j > i; j--) {
                        topKFreq[j] = topKFreq[j-1];
                        topKKey[j] = topKKey[j-1];
                    }
                    topKFreq[i] = freq;
                    topKKey[i] = key;
                    break;
                }
            }
        }

        return Arrays.asList(topKKey);

    }
}
