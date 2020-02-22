package leetcode.java.LC1108;

import java.io.IOException;
import java.util.Scanner;

public class Main_1108 {
    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
//        System.out.println(sc.next());

        String address = "192.168.1.1";
//        String[] segments = address.split("\\.");  // regex中.号需要转义

        String rst = address.replace(".", "[.]");
        System.out.println(rst);



    }
}
