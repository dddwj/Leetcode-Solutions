package leetcode.java.LC771;

public class Main_771 {

    public static void main(String[] args) {
        Main_771 main_771 = new Main_771();
        System.out.println('z' - 'A');
        System.out.println(main_771.numJewelsInStones_mysolution("z", "zZZ"));
        System.out.println(main_771.numJewelsInStones_mysolution("zb", "zab"));
    }


    // Solution1: Brute Force
    // Time: O( len(J) * len(S) )
    // Space: O(1)



    // Solution2: Hashtable  or  Alphabet Array
    // Time: O( len(J) + len(S) )
    // Space: O(N)  or  O(1)
    public int numJewelsInStones_mysolution(String J, String S) {
        if (J == null || S == null)
            return 0;

        int[] stones = new int['z' - 'A' + 1];      // Note: ASCII: 'A': 65, 'Z': 90, 'a': 97, 'z': 122
        for (Character c : S.toCharArray()) {
            stones[c - 'A']++;
        }
        int res = 0;
        for (Character c : J.toCharArray()) {
            res += stones[c - 'A'];
        }
        return res;
    }
}
