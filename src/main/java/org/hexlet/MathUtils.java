package org.hexlet;

public class MathUtils {
    public static int sumOfInts(int i, int...nums) {
        int sum = i;

        for(int num : nums) {
            sum += num;
        }
        return sum;
    }
}