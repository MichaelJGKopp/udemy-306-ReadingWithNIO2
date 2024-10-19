package dev.lpa;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ChallengeAlt {
  
  public static void main(String[] args) {
    
    Path path = Path.of("challenge.txt");
    try (Scanner scanner = new Scanner(path)) {
      scanner.useDelimiter("[\\p{Punct}\\s]+");
      var result = scanner.tokens()
        .filter(s -> s.length() > 5)
        .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
      
      result.entrySet()
        .stream()
        .sorted(Comparator.<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue).reversed())
        .limit(10)
        .forEach(System.out::println);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
