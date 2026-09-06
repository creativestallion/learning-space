package pairprogramming;

import org.junit.jupiter.api.Test;
import pairprogramming.model.Delivery;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SolutionTest {

    private final Solution solution = new Solution();


    @Test
    void testConsolidate() {
        Delivery d1 = new Delivery("123", 0, 5);
        Delivery d2 = new Delivery("124", 1, 2);
        Delivery d3 = new Delivery("125", 4, 6);
        Delivery d4 = new Delivery(null, 1, 2);

        var result = solution.consolidate(Set.of(d1, d2, d3));

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testConsolidateWithoutOverlap() {
        Delivery d1 = new Delivery("123", 0, 5);
        Delivery d2 = new Delivery("124", 11, 20);
        Delivery d3 = new Delivery("125", 40, 60);
//        Delivery d4 = new Delivery(null, 1, 2);

        var result = solution.consolidate(Set.of(d1, d2, d3));

        assertNotNull(result);
        assertEquals(3, result.size());
    }
}
