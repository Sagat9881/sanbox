package ru.apzakharov.contest;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Solution1 {

  public static void main(String[] args) {
    System.out.println(countWordEntries("test hello hello blabla hello run", "hello"));
    System.out.println(countWordEntries("test hello hello blabla hello run", "run"));
    System.out.println(countWordEntries("test hello hello blabla hello run", "test"));
    System.out.println(countWordEntries("test hello hello blabla hello run", "notExists"));
  }

  public static int countWordEntries(String source, String word) {
    String[] tokens = source.split(" ");

    Map<String, Integer> map = new HashMap<>();

    for (String token : tokens) {
      Integer integer = map.getOrDefault(token, 0);

      map.put(token, integer + 1);

    }

    return map.getOrDefault(word, 0);
  }

}
