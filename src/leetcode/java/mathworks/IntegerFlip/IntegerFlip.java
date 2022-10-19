package leetcode.java.mathworks.IntegerFlip;

import java.util.HashMap;
import java.util.Map;

public class IntegerFlip {
    public static void main(String[] args) {
        IntegerFlip ip = new IntegerFlip();
        System.out.println(ip.flip(25));
        System.out.println(ip.flip(9));
        System.out.println(ip.flip(8));
        System.out.println(ip.flip(7));
        System.out.println(ip.flip(11));
    }

    public String flip (int N) {
        StringBuilder sb = new StringBuilder();
        sb.append("0.");

        int cnt = 0;
        float r = (float)1 % N;
        Map<Integer, Integer> seen = new HashMap<>();
        boolean finish = false;

        while (!finish || cnt < 2) {  // while (true)
            if (seen.getOrDefault((int) r, 0) == 2) {
                // finish = true;
                break;
            }
            if (seen.getOrDefault((int) r, 0) != 0) {
                cnt += 1;
                if (cnt == 1) {
                    sb.append(" ");
                }
            }
            seen.put((int) r, seen.getOrDefault((int) r, 0) + 1);
            r *= 10;
            if (!finish || cnt < 2) {  // if (true)
                sb.append((int)r / N);
            }
            r %= N;
        }

        return sb.toString();
    }
}
