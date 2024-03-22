package ru.hexlet;

public class MathUtils {
    int x;
    public static int sumOfInts(int i, int...nums) {
        int sum = i;

        for(int num : nums) {
            sum += num;
        }
        return sum;
    }
}