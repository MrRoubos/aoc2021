package nl.krebos.aoc2021.puzzle01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle01a {
  public static void main (String [] args) throws FileNotFoundException, IOException {
    try (var reader = new BufferedReader(new FileReader("data/puzzle01a.txt"))) {
      String input;
      Long previous = null;
      Long current;
      int nrOfMeassurements = 0;
      while ((input = reader.readLine()) != null) {
        current = Long.valueOf(input);
        if (previous != null) {
          if (current > previous) {
            nrOfMeassurements++;
            System.out.println(input + "; increased");            
          }
          previous = current;
        } else {
          previous = current;
        }                
      }
      System.out.println("nrOfMeasurements :" + nrOfMeassurements);
    }
  }
}
