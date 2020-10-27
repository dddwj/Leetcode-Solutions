package leetcode.java.LC284;

import java.util.*;

public class Main_284 {
    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        PeekingIterator peekingIterator = new PeekingIterator(input.listIterator());
        System.out.println(peekingIterator.next());
        System.out.println(peekingIterator.peek());
        System.out.println(peekingIterator.next());
        System.out.println(peekingIterator.next());
        System.out.println(peekingIterator.hasNext());
    }

}

class PeekingIterator implements Iterator<Integer> {

    private Integer next;
    private Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        if (iterator.hasNext())
            this.next = iterator.next();
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer temp = next;
        if (iterator.hasNext())
            next = iterator.next();
        else
            next = null;
        return temp;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }
}