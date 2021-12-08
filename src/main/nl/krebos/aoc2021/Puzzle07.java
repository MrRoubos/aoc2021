package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Puzzle07 extends Puzzle {
  boolean part2 = false; 
    
  public Puzzle07(String name, int day, int part, String file) {
    super(name, day, part, file);
  }

  /**
   * Read first line from file and put the by comma seperated values in a string array 
   * Itterate over the array and put them in a Set/Map or List, increase count of same values
   * Itterate over newList, check highest values
   * 
   * @return
   */
  public long part1() {       
    String[] values = readFile();
    Map<String, Integer> map = new HashMap<>();
    prl("Values from file: " + values.length);
    int maxPos=0;
    for (String value : values ) {
      Integer hpos = map.get(value);
      if (Integer.parseInt(value) > maxPos) {
        maxPos = Integer.parseInt(value);
      }
      
      if (hpos != null) {
        hpos++;
        map.put(value, hpos);
      } else {
        map.put(value, 1);
      }
    }
    prl("Unique values: " + map.size());

    int min = Integer.MAX_VALUE;
    String minPos = "";
    Map<String, Integer> mapCostsPerPos = new HashMap<>();    
    if (!part2) {
      // Calculate per unique position the costs
      for (String pos :  map.keySet()) {      
        mapCostsPerPos = calculateFuel(pos, map, mapCostsPerPos);  
      }
    } else {
      prl("---part 2 ---");
      prl("calc: " + calc(4));
      prl("calc: " + calc(11));
      for (int i=1; i <= maxPos; i++) {
        mapCostsPerPos = calculateFuel("" + i, map, mapCostsPerPos);  
      }

    }
    prl("Now choose the cheapest");
    for (String pos :  mapCostsPerPos.keySet()) {
        if (mapCostsPerPos.get(pos) < min) {
          min = mapCostsPerPos.get(pos);
          minPos = pos;
        }
    }    
    prl("Cheapest: minPos" + minPos + "; costs: " + min);
    this.setResult(min);
         
    return this.getResult();
  }

  /**
   * part 2
   * 1 to 5 costs 10
   * 1->2 = 1
   * 2->3 = 2
   * 3->4 = 3
   * 4->5 = 4
   */
  private int calc(int dif) {
    int result =0;
    for (int i = 1; i <= dif; i++) {
      result += i;
    }
   return result;
  }
  
  /**
   * A recursive variant, still for me hard to understand :(
   * @param x
   * @return
   */
  private int calc2(int x) {
    if (x == 1) {
      return 1; 
    } else {
      return (x + calc2(x - 1));
    }
  }    
  
  private Map<String, Integer> calculateFuel(String pos, Map<String, Integer> map, Map<String, Integer> mapCostsPerPos) {
    int totalCost = 0;
    for (String pos1 : map.keySet()) {
      if (pos1 != pos) {
       int fuelCost = Math.abs(Integer.parseInt(pos) - Integer.parseInt(pos1));
       if (part2) {
         // the normal (for-loop) variant
         //fuelCost = calc(fuelCost);
         
         // the recursive variant
         if (fuelCost > 0) {
           fuelCost = calc2(fuelCost);  
         }         
       }       
       totalCost += (fuelCost * map.get(pos1));
       //prl("From: " + pos + "; to: " + pos1 + "; costs: " + fuelCost + "; total: " + totalCost);
      }     
    }
    mapCostsPerPos.put(pos, totalCost);
    return  mapCostsPerPos; 
  }

  private String[] readFile() {
    String[] initialStr = null;
    
    String input = "";
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      while ((input = reader.readLine()) != null) {        
        initialStr = input.split(","); 
      }      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return initialStr;
  }

  public void printResult() {    
    prl(this + "; Result: " + this.getResult());
  }   
}


