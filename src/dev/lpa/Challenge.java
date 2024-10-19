package dev.lpa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Challenge {

  public static void main(String[] args) {

    Path path = Path.of("challenge.txt");

    try (var stream = Files.lines(path)) {

      Pattern p = Pattern.compile("[A-Za-z]{6,}");
      var result = stream
        .flatMap(line -> {
          var matcher = p.matcher(line);
          return matcher.results();
        })
        .collect(Collectors.groupingBy(MatchResult::group, TreeMap::new, Collectors.counting()));

      System.out.println("--------");
      result.entrySet()
          .stream()
//        .sorted(Comparator.comparing((Map.Entry<String, Long> entry) -> entry.getValue()).reversed())
          .sorted(Comparator.<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue).reversed())
          .limit(5)
          .forEach(System.out::println);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
