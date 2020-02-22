package leetcode.java.LC38;

public class Main_38 {
    public static void main(String[] args) {
        Main_38 main_38 = new Main_38();
        System.out.println(main_38.countAndSay(10));
    }

    public String countAndSay(int n) {
//        String str = "312211";
        if (n == 1)
            return "1";

        String str = "1";
        for (int i = 0; i < n - 1; i++) {
            str = aux(str);
        }
        return str;
    }

    public String aux(String input) {
        StringBuilder stringBuilder = new StringBuilder();

        // 写法1：我的写法，设置i=0为初始值,每次向前后看n位
        char[] inputArr = input.toCharArray();
        for (int i = 0, j = i + 1; i < inputArr.length; i = j + 1) {
            char curChar = inputArr[i];
            int count = 1;
            for (j = i + 1; j < inputArr.length; j++) {
                if (inputArr[j] == curChar)
                    count++;
                else {
                    j--;        // Step back j
                    break;
                }
            }
            // Concat String
            stringBuilder.append(count).append(curChar);
        }


        // 写法2：设置i=1为初始值,每次向前看(而不是向后看)，代码更简洁
        // ref: https://leetcode.com/problems/count-and-say/discuss/512957/Simple-Java-Solution-(n2)-runtime
        /*
        int count = 1;
        for (int i = 1; i < input.length(); i++){
            if (input.charAt(i) == input.charAt(i - 1))
                count++;
            else{
                stringBuilder.append(count);
                stringBuilder.append(input.charAt(i - 1));
                count = 1;
            }
        }
        */


        return stringBuilder.toString();
    }

}
