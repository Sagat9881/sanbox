package ru.apzakharov.solutions;

/**
 * You are given a 0-indexed array of integers nums of length n.
 * You are initially positioned at nums[0].
 * <p>
 * Each element nums[i] represents the maximum length of a forward jump from index i.
 * In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 * 0 <= j <= nums[i] and * i + j < n
 * <p>
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
 */
public class JumpGame {

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        int jump = jumpGame.jump(new int[]{2, 3, 1, 1, 4});
        System.out.println(jump);
    }

    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        int step = 0;
        for (int i = 0; i < nums.length; ) {
            step++;
            int value = nums[i];
            int maxLength = i + nums[i];
            if (maxLength < nums.length - 1) {
                for (int j = i + 1, count = 0; count < value && j < nums.length; j++, count++) {
                    int newLength = j + nums[j];
                    if (newLength > maxLength) {
                        maxLength = newLength;
                        i = j;
                    }
                }
            } else {
                break;
            }

        }
        return step;

    }
}
