package ru.apzakharov.contest;

import java.util.Arrays;

public class Solution3 {

  public static int dummyCalculator(String expr) {
    return Arrays.stream(expr.split("\\+"))
        .mapToInt(token ->
            Arrays.stream(token.split("\\*"))
                .map(Integer::parseInt)
                .reduce(1, (o1, o2) -> o1 * o2)
        ).sum();
  }

  public static void main(String[] args) {
    System.out.println(dummyCalculator("1*4*3+1+9+2*2*5"));
  }

}
