package leetcode.java.LC412;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main_412 {
    public static void main(String[] args) {
        Main_412 main_412 = new Main_412();
        System.out.println(main_412.fizzBuzz_solution1(15));
    }

    // Solution1: Naive Approach
    // Time: O(N)  Space: O(1)
    public List<String> fizzBuzz_solution1(int n) {
        List<String> list = new LinkedList<String>();
        for (int i = 1; i < n + 1; i++) {
            if (i % 3 == 0 && i % 5 == 0)
                list.add("FizzBuzz");
            else if (i % 3 == 0)
                list.add("Fizz");
            else if (i % 5 == 0)
                list.add("Buzz");
            else
                list.add(String.valueOf(i));
        }
        return list;
    }




    // Solution2: String Concatenation
    // Time: O(N)  Space: O(1)
    public List<String> fizzBuzz_solution2(int n) {
        // ans list
        List<String> ans = new ArrayList<String>();

        for (int num = 1; num <= n; num++) {

            boolean divisibleBy3 = (num % 3 == 0);
            boolean divisibleBy5 = (num % 5 == 0);

            String numAnsStr = "";

            if (divisibleBy3) {
                // Divides by 3, add Fizz
                numAnsStr += "Fizz";
            }

            if (divisibleBy5) {
                // Divides by 5, add Buzz
                numAnsStr += "Buzz";
            }

            if (numAnsStr.equals("")) {
                // Not divisible by 3 or 5, add the number
                numAnsStr += Integer.toString(num);
            }

            // Append the current answer str to the ans list
            ans.add(numAnsStr);
        }

        return ans;
    }
}
