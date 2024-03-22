package ru.yandex_contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class RemoveDuplicate {

    public static void task() {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int expectedCount = Integer.parseInt(r.readLine());
            String line = r.readLine();
            final Set<String> resultSet = new HashSet<>();
            int readedTokens = 1;
            while (line != null && !line.isEmpty() && readedTokens <= expectedCount) {
                resultSet.add(line);
                line = r.readLine();
                readedTokens++;
            }

            final String resultString = resultSet.stream()
                    .sorted(Comparator.comparingInt(Integer::parseInt))
                    .collect(Collectors.joining("\n"));

            System.out.println(resultString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        task();
    }
}
