package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle01b {
    
  public static void main (String [] args) throws FileNotFoundException, IOException {
    try (var reader = new BufferedReader(new FileReader("data/puzzle01a.txt"))) {
      String input;
      Long previous = null, current = null;
      Long value, total;
      int y = 0;
      int lineCounter = 0;
      Long [] values = new Long[3];
      
      int nrOfMeassurements = 0;
      while ((input = reader.readLine()) != null) {
        value = Long.valueOf(input);
        if (lineCounter < 3) {
          values[lineCounter] = value;
        } else {
          total = 0L;
          for (int i=0; i < 3; i++) {
            // System.out.println("values: " + values[i]);
            total = total + values[i];            
          }
          current = total;
          if (previous != null) {
            if (current > previous) {
              nrOfMeassurements++;
              System.out.println(input + "; increased");  
            }
            previous = current;
          } else {
            previous = current;
          }
          System.out.println("total" + total);
          if (y > 2) {
            y = 0;
          } 
          values[y] = value;
          y++;          
        }
        lineCounter++;  
      }
      total = 0L;
      for (int i=0; i < 3; i++) {
        // System.out.println("values: " + values[i]);
        total = total + values[i];            
      }
      current = total;
      if (previous != null) {
        if (current > previous) {
          nrOfMeassurements++;
          System.out.println(input + "; increased");  
        }
        previous = current;
      } else {
        previous = current;
      }

      System.out.println("total" + total);

      System.out.println("nrOfMeassurements:" + nrOfMeassurements);      
    }
  }
  
}
