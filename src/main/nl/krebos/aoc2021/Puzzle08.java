package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Puzzle08 extends Puzzle {
  boolean part2 = false; 
    
  public Puzzle08(String name, int day, int part, String file) {
    super(name, day, part, file);
  }

  public long execute() {
    if (this.getPart() == 1) {
      return part1();
    } else {
      return part2();
    }
  }
  
  /**
   * Read line from file
   *   split the line on | character
   *   part0, contains 10 unique signal patterns
   *   part1, 4 digit output value
   *   The digits 1, 4, 7 and 8 use a unique number of segments:
   *   1 -> 2 segments
   *   4 -> 4 segments
   *   7 -> 3 segments
   *   8 -> 7 segments 
   * @return
   */
  private long part1() {           
    String input = "";
    int total = 0;
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      while ((input = reader.readLine()) != null) {        
        total +=processLine(input) ;
      }      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }    
    prl("Total 1,4,7,8: " + total);
    this.setResult(total);         
    return this.getResult();
  }

  private int processLine(String input) {
    prl("input : " + input);
    String [] parts = input.split(" \\| ");
    prl("part0 : " + parts[0]);
    prl("part1 : " + parts[1]);

    String [] digitsOut = parts[1].split(" ");
    for (String value : digitsOut) {
      prl("Digits: >" + value + "<") ;      
    } 
    int count = count1478(digitsOut);
    prl("Aantal 1,4,7,8: " + count);
    return count;
  }
  
  private int count1478(String[] digits) {
    int count=0;
    for (String value : digits) {
      switch (value.length()) {
        case 2: 
        case 3:
        case 4:
        case 7:  
          count++;       
      }        
    }
    return count;
  }

  private long part2() {           
    String input = "";
    int total = 0;
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      while ((input = reader.readLine()) != null) {        
        total +=processLine2(input) ;
      }      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }    
    prl("Total 1,4,7,8: " + total);
    this.setResult(total);         
    return this.getResult();
  }

  private int processLine2(String input) {
    int nr = 0;
    String [] parts = input.split(" \\| ");
    String [] patterns = parts[0].split(" ");
    String [] digitsOut = parts[1].split(" ");
    Map<String,String> map = analyse(patterns);
    nr = decode(digitsOut, map);
    
    return nr;
  }
  
  private int decode(String[] digitsOut, Map<String, String> map) {
    String strNumber = "";
    for (String digit : digitsOut) {
      // prl("digit: " + digit);
      for (String value : map.keySet()) {
        String number = map.get(value);
        if (value.length() == digit.length()) {
          int count = 0;
          //prl("check: " + value);
          for (int i = 0; i < value.length(); i++) {
            for (int j=0; j < digit.length(); j++) {
              if (digit.substring(j,j+1).equals(value.substring(i,i+1))) {
                count++;                
              }
            }          
          }
          if (count == value.length()) {
            //prl("gevonden");
            strNumber += number;      
          }
        }
      }
      
      //strNumber += map.get(digit);
    }
    int nr = Integer.parseInt(strNumber);
    prl("number: " + nr);
    return nr;
  }

  /*
   *   The digits 1, 4, 7 and 8 use a unique number of segments:
   *   1 -> 2 segments
   *   4 -> 4 segments
   *   7 -> 3 segments
   *   8 -> 7 segments
   */
  private Map<String, String> analyse(String[] patterns) {
    Map<String,String> map = new HashMap<>();
    String[] numbers = new String[10];
    for (String pattern : patterns) {
      switch (pattern.length()) {
        case 2: numbers[1] = pattern; map.put(pattern, "1"); break;        
        case 3: numbers[7] = pattern; map.put(pattern, "7"); break;
        case 4: numbers[4] = pattern; map.put(pattern, "4"); break;
        case 7: numbers[8] = pattern; map.put(pattern, "8"); break;
        default: prl("uitzoeken: " + pattern);
      }      
    }     
    
    // Find length 5, the 3
    for (String pattern : patterns) {
      if (map.get(pattern) == null) {
        if (pattern.length() == 5) {
          //prl("Found the 3 in " + pattern);
          // then we have a 2,3 or 5
          // the 3 pattern must have the 1 pattern in it
          int count = 0;
          for (int i = 0; i < numbers[1].length(); i++) {
            //prl("Check if " + numbers[1].substring(i,i+1) + " is present");
            for (int j= 0; j < pattern.length(); j++) {
              if (pattern.substring(j,j+1).equals(numbers[1].substring(i,i+1))) {
                count++;
              }
            }
          }
          if (count == numbers[1].length()) {
            prl("we found the 3 = " + pattern);          
            numbers[3] = pattern;
            map.put(pattern, "3");
          }        
        }                       
      }      
    }    
    
    // Find length 5, the 5
    for (String pattern : patterns) {
      if (map.get(pattern) == null) {
        if (pattern.length() == 5) {
          // 3 digits of the 4 are present in the 5
          prl("Found the 5 in " + pattern);
          int count = 0;
          for (int i = 0; i < numbers[4].length(); i++) {
            //prl("Check if " + numbers[1].substring(i,i+1) + " is present");
            for (int j= 0; j < pattern.length(); j++) {
              if (pattern.substring(j,j+1).equals(numbers[4].substring(i,i+1))) {
                count++;
              }
            }
          }
  
          if (count == 3) {
            prl("we found the 5 = " + pattern);
            numbers[5] = pattern;
            map.put(pattern, "5");
          }
        }
      }
    }
    
    // Find length 5, the 2
    for (String pattern : patterns) {
      if (map.get(pattern) == null) {
        if (pattern.length() == 5) {
            prl("we found the 2 = " + pattern);
            numbers[2] = pattern;
            map.put(pattern, "2");
          }
        }
      }

    // Still 3 digits to go, we have to find 0, 6 and 9
    for (String pattern : patterns) {
      if (map.get(pattern) == null) {
        if (pattern.length() == 6) {
          // all characters of the 4 are present in the in the 9
          int count = 0;
          for (int i = 0; i < numbers[4].length(); i++) {
            //prl("Check if " + numbers[1].substring(i,i+1) + " is present");
            for (int j= 0; j < pattern.length(); j++) {
              if (pattern.substring(j,j+1).equals(numbers[4].substring(i,i+1))) {
                count++;
              }
            }
          }
  
          if (count == 4) {
            prl("we found the 9 = " + pattern);
            numbers[9] = pattern;
            map.put(pattern, "9");
          }          
        }
      }
    }
    
    // Now find the 6 
    for (String pattern : patterns) {
      if (map.get(pattern) == null) {
        if (pattern.length() == 6) {
          // the characters of the 1 are present in 0 and not in 6
          int count = 0;
          for (int i = 0; i < numbers[1].length(); i++) {
            //prl("Check if " + numbers[1].substring(i,i+1) + " is present");
            for (int j= 0; j < pattern.length(); j++) {
              if (pattern.substring(j,j+1).equals(numbers[1].substring(i,i+1))) {
                count++;
              }
            }
          }
  
          if (count == 2) {
            prl("we found the 0 = " + pattern);
            numbers[0] = pattern;
            map.put(pattern, "0");
          }          
        }
      }
    }
    
    // So the last has to be the 6
    for (String pattern : patterns) {
      if (map.get(pattern) == null) {
        prl("we found the 6 = " + pattern);
        numbers[6] = pattern;
        map.put(pattern, "6");
      }
    }
    
    for (int i = 0; i < 10; i++) {
      prl("numbers["+i+"] = " + numbers[i]);
    }
    for (String value : map.keySet()) {
      prl(value + " -> " + map.get(value));
    }    
   return map; 
  }

  public void printResult() {    
    prl(this + "; Result: " + this.getResult());
  }   
}
