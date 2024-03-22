package ru.yandex_contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DigitOneLenghts {
    public static void task() {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String line = r.readLine();
            int maxLenghtCount = 0;
            int currentLenght = 0;
            while (line != null && !line.isEmpty()) {
                int digit = Integer.parseInt(line);
                if (digit == 1) {
                    currentLenght++;
                } else {
                    maxLenghtCount = Math.max(currentLenght, maxLenghtCount);
                    currentLenght = 0;
                }
                line = r.readLine();
            }

            System.out.println(maxLenghtCount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {
        String[] lengthsTwo = {"0", "3", "1", "1"};
        String[] lengthsOne = {"2938", "3", "1", "0"};

        task();


    }
}
