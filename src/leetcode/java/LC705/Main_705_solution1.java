package leetcode.java.LC705;

// Ref: https://leetcode-cn.com/problems/design-hashset/solution/she-ji-ha-xi-ji-he-by-leetcode/

import java.util.LinkedList;


// Solution1: LinkedList + Hash
// Time: O(N/K), where K = keyRange = 769
// Space: O(K + N)
public class Main_705_solution1 {

    public static void main(String[] args) {
        MyHashSet_List hashSet = new MyHashSet_List();
        hashSet.add(1);
        hashSet.add(2);
        System.out.println(hashSet.contains(1));
        System.out.println(hashSet.contains(3));
        hashSet.add(2);
        System.out.println(hashSet.contains(2));
        hashSet.remove(2);
        System.out.println(hashSet.contains(2));
    }
}

class MyHashSet_List {

    int keyRange;
    MyList[] set;

    /** Initialize your data structure here. */
    public MyHashSet_List() {
        this.keyRange = 769;        // or: 997. Should be prime number, but cannot be too big or too small
        this.set = new MyList[this.keyRange];
        for (int i = 0; i < this.keyRange; i++) {
            this.set[i] = new MyList();
        }
    }

    public void add(int key) {
        if (!contains(key)) {
            int index = key % this.keyRange;
            set[index].add(key);
        }
    }

    public void remove(int key) {
        int index = computeHash(key);
        if (set[index].indexOf(key) != -1)
            set[index].remove(key);
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index = computeHash(key);
        return set[index].indexOf(key) != -1;
    }

    private int computeHash(int key) {
        return key % this.keyRange;
    }
}

class MyList {

    LinkedList<Integer> list;

    public MyList() {
        this.list = new LinkedList<>();
    }

    public void add(int val) {
        this.list.addLast(val);
    }

    public void remove(int val) {
        int index = indexOf(val);
        if (index == -1)
            return;
        this.list.remove(index);
    }

    public int indexOf(int val) {
        return this.list.indexOf(val);
    }
}

