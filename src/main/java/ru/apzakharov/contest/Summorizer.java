package ru.apzakharov.contest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Summorizer {

  public static final String OUTPUT_TXT = "output.txt";
  public static final String INPUT_TXT = "input.txt";

  public static void main(String[] args) throws IOException {
    Summorizer summorizer = new Summorizer();
    summorizer.printSumFromFile();
  }

  void printSum() {
    Scanner scanner = new Scanner(System.in);
    String line = scanner.nextLine();
    String[] numbers = line.split(" ");

    System.out.println(Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]));

  }

  void printSumFromFile() throws IOException {
    File file = new File(OUTPUT_TXT);
    boolean newFile = file.createNewFile();
    if (!newFile) {
      Path path = Paths.get(OUTPUT_TXT);
      Files.writeString(path, "");
    }

    try (FileWriter fileWriter = new FileWriter(file)) {
      List<String> numbers = Files.readAllLines(Paths.get(INPUT_TXT));
      List<Integer> sums = numbers.stream().map(string -> string.split(" "))
          .map(string -> Integer.parseInt(string[0]) + Integer.parseInt(string[1]))
          .toList();

      for (int i : sums) {
        fileWriter.append(String.valueOf(i));
        fileWriter.flush();
      }
    } catch (Exception e) {
      file.deleteOnExit();
    }


  }

}
