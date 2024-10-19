package dev.lpa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solution {
  
  public static void main(String[] args) {
    
    try (BufferedReader br = new BufferedReader(new FileReader("article.txt"))) {
//      br.mark(100_000); // Mark the current position with a read-ahead limit of 100000 characters
//      System.out.printf(" %,d lines in file%n", br.lines().count());
//      br.reset(); // Reset to the marked position
//      System.out.printf(" %,d lines in file%n", br.lines().count());
      Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");
//      System.out.printf("%,d words in file%n",
//        br.lines()
////          .flatMap(pattern::splitAsStream)
//          .flatMap(s -> Arrays.stream(s.split(pattern.toString())))
//          .count());
      
      List<String> excluded = List.of(
        "canyon",
        "grand",
        "retrieved",
        "archived",
        "original",
        "service");
      var result = br.lines()
                     .flatMap(pattern::splitAsStream)
                     .map(w -> w.replaceAll("\\p{Punct}", ""))
                     .filter(w -> w.length() > 4)
                     .map(String::toLowerCase)
                     .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
      
      result.entrySet().stream()
        .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
        .filter(e -> !excluded.contains(e.getKey()))
        .limit(10)
        .forEach(e -> System.out.println(
          e.getKey() + " - " + e.getValue() + " times"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}