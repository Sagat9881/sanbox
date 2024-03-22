package ru.apzakharov;

/*Нам поступила задача сделать консольное приложение, которое должно читать csv-файл (объем файла умещается в RAM) и выводить данные на экран в заданном формате.
  Мы также хотим сохранять данные в промежуточную структуру, чтобы дальше обрабатывать их в памяти (сортировка, фильтрация, группировка и т.п).
  Разработчик-стажёр выполнил эту задачу. Сделай код ревью его решения. Какие замечания есть по коду? Какие edge-кейсы ты видишь?

  1;Адыгейск;Адыгея;Южный;12248;1973
  2;Майкоп;Адыгея;Южный;144246;1857
  3;Горно-Алтайск;Алтай;Сибирский;56928;1830
  4;Алейск;Алтайский край;Сибирский;29512;1913
  5;Барнаул;Алтайский край;Сибирский;612091;1730
  6;Белокуриха;Алтайский край;Сибирский;14660;1803
  7;Бийск;Алтайский край;Сибирский;210055;1709
  8;Горняк;Алтайский край;Сибирский;13924;1942
  9;Заринск;Алтайский край;Сибирский;48456;1979
  10;Змеиногорск;Алтайский край;Сибирский;10955;1736
  11;Камень-на-Оби;Алтайский край;Сибирский;43880;1751
  12;Новоалтайск;Алтайский край;Сибирский;70438;1736
  13;Рубцовск;Алтайский край;Сибирский;147008;1892
  14;Славгород;Алтайский край;Сибирский;32390;1910
  15;Яровое;Алтайский край;Сибирский;18605;1943 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo {
    // Необходимо считать данные из файла cities.csv и вывести на экран в следующем формате:
    // Город: Сергач, регион: Нижегородская область, федеральный округ: Приволжский, население: 21387, основан: 1649

    private static String FILE_PATH = "src/cities.csv";

    public static void main(String[] args) {
        List<City> cityListFromFile = getCityListFromFile();
        printCitiesName(cityListFromFile);
    }

    public static List<City> getCityListFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            do {
                line = reader.readLine();
                return addCityToArray(line);
            }
            while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static List<City> addCityToArray(String line) {
        List<City> resultList = new ArrayList<>();
        String[] splitLine;
        int population;
        int foundation;
        try {
            splitLine = line.split(";");
            if (splitLine.length != 6)
                throw new Exception("Error line");
            population = Integer.parseInt(splitLine[4]);
            foundation = Integer.parseInt(splitLine[5]);
            resultList.add(new City(splitLine[1], splitLine[2], splitLine[3], population, foundation));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return resultList;
    }

    public static void printCitiesName(List<City> cityList) {
        for (City c : cityList) {
            System.out.printf("Город: %s, регион: %s, федеральный округ: %s, население: %d, основан: %d\n\n", c.name, c.region, c.district,
                    c.population, c.foundation);
        }
    }

    private static class City {
        String name;
        String region;
        String district;
        int population;
        int foundation;

        City(String name, String region, String district, int population, int foundation) {
            this.name = name;
            this.region = region;
            this.district = district;
            this.population = population;
            this.foundation = foundation;
        }
    }
}

