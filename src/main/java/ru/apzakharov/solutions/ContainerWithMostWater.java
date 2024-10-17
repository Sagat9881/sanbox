package ru.apzakharov.solutions;

/**
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        ContainerWithMostWater useCase = new ContainerWithMostWater();
        int i = useCase.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        System.out.println(i);
    }


    public int maxArea(int[] height) {
        int square = 0;
        for (int left = 0, right = height.length - 1; left != right; ) {
            int leftHeight = height[left];
            int rightHeight = height[right];
            if (leftHeight > rightHeight) {
                square = Math.max(square, rightHeight * (right - left));
                right--;
            } else {
                square = Math.max(square, leftHeight * (right - left));
                left++;
            }
        }

        return square;
    }
}
