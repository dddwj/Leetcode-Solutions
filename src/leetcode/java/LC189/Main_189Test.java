package leetcode.java.LC189;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Main_189Test {
    private final Main_189 main_189 = new Main_189();

    @Test
    void testMain() {
        assertArrayEquals(new int[] {2, 1}, main_189.rotate_solution1(new int[] {1, 2}, 3));
        assertArrayEquals(new int[] {5,6,7,1,2,3,4}, main_189.rotate_solution1(new int[] {1,2,3,4,5,6,7}, 3));
    }

}