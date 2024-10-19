package dev.lpa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
  
  public static void main(String[] args) {
    
    try (BufferedReader br = new BufferedReader(new FileReader("article.txt"))) {
      br.mark(100_000); // Mark the current position with a read-ahead limit of 100000 characters
      System.out.printf(" %,d lines in file%n", br.lines().count());
      br.reset(); // Reset to the marked position
      System.out.printf(" %,d lines in file%n", br.lines().count());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}