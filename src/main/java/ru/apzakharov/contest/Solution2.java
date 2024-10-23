package ru.apzakharov.contest;

public class Solution2 {

  public static void print(int n) {
    if (n < 0) {
      return;
    }
    System.out.println(n * 2);
    print(n - 1);
    System.out.println(n * 2);
  }

  public static void main(String[] args) {
    print(4); //  8  6  4  2  0
  }

}
