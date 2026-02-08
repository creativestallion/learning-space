package Leetcode150;

import java.util.HashSet;

public class Solution {
    private static HashSet<Integer> set = new HashSet<>();

    public static HashSet<Integer> getSet() {
        return set;
    }

    public static void setSet(HashSet<Integer> set) {
        Solution.set = set;
    }

    static void dummy() {
        set.add(10);
        set.remove(10);
        System.out.print("size = ");
        set.size();
    }
}
