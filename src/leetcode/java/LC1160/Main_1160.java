package leetcode.java.LC1160;

public class Main_1160 {
    public static void main(String[] args) {
        Main_1160 main_1160 = new Main_1160();
        System.out.println(main_1160.countCharacters(new String[]{"cat", "bt", "hat", "tree"}, "atach"));
    }

    // My Solution: Hashtable,  brute force
    // Time: O(1)
    // Space: O(1)
    public int countCharacters(String[] words, String chars) {
        if (words.length == 0 || chars == null)
            return 0;
        char[] alphabet = new char[26];
        for (char c : chars.toCharArray()) {
            alphabet[c - 'a']++;
        }

        int sum = 0;
        for (String word : words) {
            char[] charCount = new char[26];
            for (char c : word.toCharArray()) {
                charCount[c - 'a']++;
            }
            boolean valid = true;
            for (int i = 0; i < 26; i++) {
                if (charCount[i] > alphabet[i]) {
                    valid = false;
                    break;
                }
            }
            if (valid)
                sum += word.length();
        }
        return sum;
    }
}
