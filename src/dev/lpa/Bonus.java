package dev.lpa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bonus {
  
  public static void main(String[] args) {
    
    String input = null;
    try {
      input = Files.readString(Path.of("bigben.txt"));
      input = input.replaceAll("\\p{Punct}", "");
      Pattern pattern = Pattern.compile("\\w{5,}");  // instead of \\w+
      Matcher matcher = pattern.matcher(input);
      Map<String, Long> results = new HashMap<>();
      while (matcher.find()) {
        String word = matcher.group().toLowerCase();
//        if (word.length() > 4) {
          results.merge(word, 1L, (o, n) -> o += n);
//        }
      }
      
      var sortedEntries = new ArrayList<>(results.entrySet());
      sortedEntries.sort(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()));
      for (int i = 0; i < Math.min(10, sortedEntries.size()); i++) {  // if < 10 entries
       var entry = sortedEntries.get(i);
        System.out.println(entry.getKey() + " - " + entry.getValue() + " times");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
