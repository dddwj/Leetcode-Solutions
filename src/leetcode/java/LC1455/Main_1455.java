package leetcode.java.LC1455;

public class Main_1455 {

    public static void main(String[] args) {
        Main_1455 main_1455 = new Main_1455();
        System.out.println(main_1455.isPrefixOfWord_solution("i am leetcode", "lee"));

    }


    // My Solution: Naive  (Built-in functions)
    // Time: O(N)
    public int isPrefixOfWord_mysolution(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.startsWith(searchWord)) {
                return i + 1;
            }
        }
        return -1;
    }


    // Solution: Naive
    // Time: O(N),  where N = len(sentence)
    // Ref: https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/discuss/648269/Java-O(n)-Without-using-string-methods-split-or-indexOf-or-startsWith
    public int isPrefixOfWord_solution(String sentence, String searchWord) {
        boolean search = true;
        for (int i = 0, j = 0, wordCount = 1; i < sentence.length(); i++) {
            if (search) {
                if (searchWord.charAt(j) == sentence.charAt(i)) {
                    j++;
                    if (j == searchWord.length())
                        return wordCount;
                } else
                    search = false;
            } else if (sentence.charAt(i) == ' ') {
                search = true;
                j = 0;
                wordCount++;
            }
        }
        return -1;
    }
}
