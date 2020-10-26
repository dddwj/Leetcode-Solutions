package memos;

import java.util.Scanner;

public class UseScanner {
    public static void main(String[] args) {
        System.out.printf("Hello %.5f %s", (float) 0.5313453, "haha");

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(s);
        if (!in.hasNextLine())
            return;
        s = in.nextInt() + "";
        System.out.println(Integer.valueOf(s));


    }
}
