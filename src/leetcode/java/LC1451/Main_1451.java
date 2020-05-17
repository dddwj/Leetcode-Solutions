package leetcode.java.LC1451;

import java.util.Arrays;
import java.util.Comparator;

public class Main_1451 {
    public static void main(String[] args) {
        Main_1451 main_1451 = new Main_1451();
        System.out.println(main_1451.arrangeWords_mysolution_opt1("Keep calm and strong"));
        System.out.println(main_1451.arrangeWords_mysolution_opt1("K"));
        System.out.println(main_1451.arrangeWords_mysolution_opt1("Leetcode is cool"));
    }


    // My Solution: (Opt1)  Sort with comparator
    // Time: O(NlgN)
    // Space: O(N) for sort
    public String arrangeWords_mysolution_opt1(String text) {
        String[] arr = text.split(" ");
        if (arr.length < 2) {
            return text;
        }

        arr[0] = arr[0].toLowerCase();

        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        arr[0] = arr[0].substring(0, 1).toUpperCase() + arr[0].substring(1);

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String word : arr) {
            if (!first) {
                sb.append(" ");
            }
            first = false;
            sb.append(word);
        }

        return sb.toString();
    }


    // My Solution: (Opt2: Concise version)
    // Ref: https://leetcode.com/problems/rearrange-words-in-a-sentence/discuss/636289/Java-4-lines-self-explanatory-clean-and-concise
    public String arrangeWords_mysolution_opt2(String text) {
        String[] sa = text.toLowerCase().split(" ");
        Arrays.sort(sa, (a, b) -> a.length() - b.length());
        sa[0] = Character.toUpperCase(sa[0].charAt(0)) + sa[0].substring(1);
        return String.join(" ", sa);
    }
}
