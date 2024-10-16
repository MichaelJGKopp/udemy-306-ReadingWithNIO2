package dev.lpa;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

    System.out.println(System.getProperty("file.encoding"));
    System.out.println(Charset.defaultCharset());

    Path path = Path.of("fixedWidth.txt");
    try {
      System.out.println(new String(Files.readAllBytes(path)));
      System.out.println("---------------------");
      System.out.println(Files.readString(path));   // also uses readAllBytes, safer for text

      Pattern p = Pattern.compile("(.{15})(.{3})(.{12})(.{8})(.{2}).*");
      Set<String> values = new TreeSet<>();
      Files.readAllLines(path).forEach(s -> {
        if (!s.startsWith("Name")) {    // not recommended in real life, quick work around
          Matcher m = p.matcher(s);
          if (m.matches()) {
            values.add(m.group(3).trim());
          }
        }
      });
      System.out.println(values);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
