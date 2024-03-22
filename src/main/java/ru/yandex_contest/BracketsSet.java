package ru.yandex_contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BracketsSet {

    public static final String OPEN_BRACKET = "(";
    public static final String CLOSE_BRACKET = ")";

    private static void task() {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String expectedLength = r.readLine();
            int expectedSingleSetLengths = Integer.parseInt(expectedLength) * 2;

            List<String> planeResult = new ArrayList<>(expectedSingleSetLengths);
            List<List<String>> result = new ArrayList<>();
            result.add(planeResult);
            taskRecursive(0, 0, expectedSingleSetLengths, planeResult, result);
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<List<String>> taskRecursive(int bracketBalance,
                                                    int index,
                                                    int expectedSingleSetLengths,
                                                    final List<String> singleSetResult,
                                                    final List<List<String>> resultSet) {

        // сам индекс отстает от размера на 1,
        // index + 2 - проверяет есть ли еще в массиве место под одну закрывающую скобку
        // где 2 - это 1 (отставание) + 1 (проверка существования места под еще одну скобку)
        if (bracketBalance <= expectedSingleSetLengths - (index + 2)) {
            singleSetResult.add(index, OPEN_BRACKET);

            return taskRecursive(bracketBalance + 1, index + 1, expectedSingleSetLengths, singleSetResult, resultSet);
        } else if (bracketBalance > 0) {
            singleSetResult.add(index, CLOSE_BRACKET);
            return taskRecursive(bracketBalance - 1, index + 1, expectedSingleSetLengths, singleSetResult, resultSet);
        }
        if (index == expectedSingleSetLengths && bracketBalance == 0) {
            resultSet.add(singleSetResult);
            return taskRecursive(
                    bracketBalance,
                    0,
                    expectedSingleSetLengths,
                    singleSetResult,
                    resultSet);
        } else {
            return resultSet;
        }// выходим из рекурсии
    }


    public static void main(String[] args) {
        task();
    }
}
