package leetcode.java.UnknownNumber.FirstUnique;

import java.util.*;


// Solution:
// Ref: https://leetcode.com/discuss/interview-question/124822/Bloomberg-or-First-Unique-Number-in-Data-Stream/519893
public class FirstUniqueSolution {
    private Set<Integer> uniqueSet;
    private Set<Integer> repeatSet;

    public FirstUniqueSolution(int[] nums) {
        uniqueSet = new LinkedHashSet<Integer>();       // Note: LinkedHashSet == HashSet with *ordered* keys
        repeatSet = new HashSet<Integer>();
        for (int num : nums) {  // O(N)
            add(num);
        }
    }

    // O(1)
    public int showFirstUnique() {
        if (uniqueSet.isEmpty()) {
            return -1;
        }
        for (int num : uniqueSet) {
            return num;
        }
        return -1;
    }

    // O(1)
    public void add(int value) {
        if (!uniqueSet.contains(value) && !repeatSet.contains(value))
            uniqueSet.add(value);
        else {
            if (uniqueSet.contains(value)) {
                uniqueSet.remove(value);
                repeatSet.add(value);
            }
        }
    }
}




// My Solution:
// TLE
class FirstUnique_MySolution {

    private List<Integer> list;
    private Set<Integer> uniqueSet;
    private Set<Integer> repeatSet;

    public FirstUnique_MySolution(int[] nums) {
        list = new LinkedList<>();
        uniqueSet = new HashSet<>();
        repeatSet = new HashSet<>();
        for (int num : nums) {          // O(N)
            add(num);
        }
    }

    public int showFirstUnique() {
        for (Integer num : list) {      // O(N), takes time!! Should be optimized to O(1) with traversal of uniqueList (LinkedHashSet)
            if (uniqueSet.contains(num))  // O(1)
                return num;
        }
        return -1;
    }

    public void add(int value) {
        list.add(value);                    // O(1)
        if (uniqueSet.contains(value)) {    // O(1)
            uniqueSet.remove(value);        // O(1)
            repeatSet.add(value);           // O(1)
            return;
        }
        if (repeatSet.contains(value)) {    // O(1)
            return;
        }
        uniqueSet.add(value);               // O(1)
    }
}


class test {
    public static void main(String[] args) {
//        FirstUnique_MySolution solution = new FirstUnique_MySolution(new int[]{7,7,7,7});
        FirstUniqueSolution solution = new FirstUniqueSolution(new int[]{7,7,7,7});
        System.out.println(solution.showFirstUnique());
        solution.add(5);
        System.out.println(solution.showFirstUnique());
        solution.add(5);
        System.out.println(solution.showFirstUnique());

//        FirstUnique_MySolution solution2 = new FirstUnique_MySolution(new int[]{});
        FirstUniqueSolution solution2 = new FirstUniqueSolution(new int[]{});
        System.out.println(solution2.showFirstUnique());
        solution2.add(1);
        System.out.println(solution2.showFirstUnique());

    }
}