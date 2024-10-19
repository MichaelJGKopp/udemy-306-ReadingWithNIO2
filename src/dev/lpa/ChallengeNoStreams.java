package dev.lpa;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ChallengeNoStreams {
  
  public static void main(String[] args) {
  
    Path path = Paths.get("challenge.txt");
    Map<String, Integer> wordCount = new HashMap<>();
    
    try (Scanner scanner = new Scanner(path)) {
      scanner.useDelimiter("[\\s\\p{Punct}]+");
      while (scanner.hasNext()) {
        String token = scanner.next();
        if (token.length() > 5) {
          wordCount.merge(token, 1, Integer::sum);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    
    wordCount.entrySet().forEach(System.out::println);
    
    Map<Integer, List<String>> topWords = new TreeMap<>(Comparator.reverseOrder());
    for (var e : wordCount.entrySet()) {
      topWords.merge(
        e.getValue(),
        new ArrayList<>(Collections.singletonList(e.getKey())),
        (o, n) -> {
          o.addAll(n);
          return o;
        }
      );
    }
    
    topWords.entrySet().forEach(System.out::println);
    
    int i = 0;
    outerloop:
    for (var wordEntry : topWords.entrySet()) {
      List<String> words = wordEntry.getValue();
      words.sort(null);
      for (var word : words) {
        if (i >= 10) {
          break outerloop;
        }
        System.out.printf("%d. %s %d %n", ++i, word, wordEntry.getKey());
      }
    }
  }
}
