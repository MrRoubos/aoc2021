package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Puzzle03 extends Puzzle {
  public int byteLength = 0; 
  
  public Puzzle03(String name, int day, String part) {
    super(name, day, part);
  }

  /**
   * Read each line from file, each line contains 5 bits (0 or 1)
   * Determine the most common bit of each position
   * Count of each line if the 1st postion is 0 , if so increase a counter
   * Do the same for the other positions. At the end you know how much 0 for each
   * position where present and you know the total number of lines
   * @return
   */
  public long part1() {
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      long lines = 0;
      long [] zeroes = new long [byteLength];
      String input;
      while ((input = reader.readLine()) != null) {
        processLine(input, zeroes);
        lines++;
      }
      String most = determineMostCommon(lines, zeroes);
      String least = determineMostCommon(most);
      // Source: https://stackoverflow.com/questions/7437987/how-to-convert-binary-string-value-to-decimal
      long mostDec = Long.parseLong(most, 2);
      long leastDec = Long.parseLong(least, 2);
      this.setResult(mostDec * leastDec);
      
      System.out.println("most: " + most + "; decimal: " + mostDec);
      System.out.println("least " + least+ "; decimal: " + leastDec);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.printResult();
    return this.getResult();
  }

  public long part2() {
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      long lines = 0;
      List <String> all0 = new ArrayList<>();
      List <String> all1 = new ArrayList<>();
      
      String input;
      while ((input = reader.readLine()) != null) {
        if (input.charAt(0) == '0') {
          all0.add(input); 
        } else {
          all1.add(input);          
        }
        lines++;
      }

      System.out.println("lines: " + lines);
      System.out.println("all0: " + all0.size() + "; all1: " + all1.size());
      // process most
      String most;
      short inspectBit = 1;
      if (all0.size() > lines/2) {
        most = determineMost(all0, inspectBit);
      } else {
        most = determineMost(all1, inspectBit);
      }
      System.out.println("most: " + most);

      // process least
      String least;
      inspectBit = 1;
      if (all0.size() < lines/2) {
        least = determineLeast(all0, inspectBit);
      } else {
        least = determineLeast(all1, inspectBit);
      }
      System.out.println("least: " + least);


      long mostDec = Long.parseLong(most.trim(), 2);
      long leastDec = Long.parseLong(least.trim(), 2);
      this.setResult(mostDec * leastDec);
      
      System.out.println("most: " + most + "; decimal: " + mostDec);
      System.out.println("least " + least+ "; decimal: " + leastDec);
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.printResult();
    return this.getResult();
  }  
  
  
  private String determineMost(List<String> all0, short inspectBit) {
    System.out.println("inspectBit:" + inspectBit + "; byteLength: " + byteLength + "; all0.size: " + all0.size());
    String result;
    List<String> list0 = new ArrayList<>();
    List<String> list1 = new ArrayList<>();
    
    if (all0.size() == 1 || inspectBit == byteLength) {
      return all0.get(0);
    }
    
    int count0 = 0;
    String value;
    for (int i = 0 ; i < all0.size(); i++) {
      value = all0.get(i);
      System.out.println("value: " + value +
      "; inspectBit: " + inspectBit + "; value: " + value.substring(inspectBit,inspectBit + 1));
      if (value.substring(inspectBit,inspectBit + 1).equals("0")) {
        count0++;
        list0.add(value);
      } else {
        list1.add(value);
      }
    }       
    inspectBit++;
    if (list0.size() > all0.size() / 2) {
      result = determineMost(list0, inspectBit);
    } else {
      result = determineMost(list1, inspectBit);
    }
    return result;
  }
  
  private String determineLeast(List<String> all0, short inspectBit) {
    System.out.println("inspectBit:" + inspectBit + "; byteLength: " + byteLength + "; all0.size: " + all0.size());
    String result;
    List<String> list0 = new ArrayList<>();
    List<String> list1 = new ArrayList<>();
    
    if (all0.size() == 1 || inspectBit == byteLength) {
      return all0.get(0);
    }
    
    int count0 = 0;
    String value;
    for (int i = 0 ; i < all0.size(); i++) {
      value = all0.get(i);
      System.out.println("value: " + value +
      "; inspectBit: " + inspectBit + "; value: " + value.substring(inspectBit,inspectBit + 1));
      if (value.substring(inspectBit,inspectBit + 1).equals("0")) {
        count0++;
        list0.add(value);
      } else {
        list1.add(value);
      }
    }       
    inspectBit++;
    if (list0.size() <= all0.size() / 2) {
      result = determineLeast(list0, inspectBit);
    } else {
      result = determineLeast(list1, inspectBit);
    }
    return result;
  }  

  private String determineMostCommon(String most) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < most.length(); i++) {
      if (most.charAt(i) == '0') {
          sb.append('1');
      } else {
        sb.append('0'); 
      }
    }
    return sb.toString();
  }

  private String determineMostCommon(long lines, long[] zeroes) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < zeroes.length; i++) {
      System.out.println("Lines: " + lines + "; nr of zeroes: " + zeroes[i] );
      if (zeroes[i] > lines /2) {
        sb.append("0");
      } else {
        sb.append("1");
      }
    }    
     return sb.toString();   
  }

  public void printResult() {    
    System.out.println(this + "; Result: " + this.getResult());
  }
    
  private void processLine(String input, long [] zeroes) {
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == '0') {
        zeroes[i]++;
      }
    }
  }
  class Line {
    String direction;
    Long distance;
  }  
}
