package leetcode.java.LC24;


// Definition for singly-linked list.
class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}

public class Main_24 {
    public static void main (String[] args){
        Integer num = new Integer(10);
        Integer ref = num;
        System.out.println(num);
        System.out.println(ref);
        System.out.println("");
        num++;
        System.out.println(num);
        System.out.println(ref);
        System.out.println("");
        ref++;
        System.out.println(num);
        System.out.println(ref);
    }


}
