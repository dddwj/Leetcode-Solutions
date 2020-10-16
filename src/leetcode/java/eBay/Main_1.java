package leetcode.java.eBay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_1 {

    public static void main(String[] args) {
//                  "5 2" +
//                "1 3 1" +
//                "2 5 2";
        Scanner s = new Scanner(System.in);
        if (!s.hasNext())
            return;
        String firstLine = s.nextLine();
        String[] line1 = firstLine.split(" ");
        int n = Integer.parseInt(line1[0]), m = Integer.parseInt(line1[1]);

        List<Integer> res = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) {
            res.add(0);
        }

        while (m > 0) {
            if (!s.hasNextLine())
                return;
            String line = s.nextLine();
            String[] params = line.split(" ");
            int a = Integer.parseInt(params[0]);
            int b = Integer.parseInt(params[1]);
            int c = Integer.parseInt(params[2]);
            for (int i = a - 1; i < b; i++) {
                res.set(i, res.get(i) + c);
            }
            m--;
        }

        for (int i = 0; i < res.size() - 1; i++) {
            System.out.print(res.get(i) + " ");
        }
        System.out.println(res.get(res.size() - 1));
        return;
    }

}
