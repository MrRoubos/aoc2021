package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Puzzle09 extends Puzzle {
  public int maxX =0;
  public int maxY =0;
      
  public Puzzle09(String name, int day, int part, String file) {
    super(name, day, part, file);
  }

  public long execute() {    
    long result = 0L;
    int map [][] = readFile();
    if (this.getPart() == 1) {
      result = part1(map);
    } else {      
      result = part2(map);
    }
    this.setResult(result);
    printResult();
    return result;
  }
  

  /**
   * First read the complete file, which contains a map of integers
   * Put each integers from a line in a 2 dimensional array
   * @return
   */
  private long part1(int[][] map) {
    long result = 0L;   
    List<LowPoint> low = analyse(map);
    for (LowPoint lp : low) {
      result += lp.risk;
      prl("low x=" + lp.x + "; y="+ lp.y + "; value=" + lp.value);
    }
    prl("sum = " + result);    
    return result;
  }

  private long part2(int[][] map) {
    // the first part is the same, we need the list with LowPoints
    long result = 0L;
    
    List<LowPoint> low = analyse(map);
    for (LowPoint lp : low) {
      result += lp.risk;
      prl("low x=" + lp.x + "; y="+ lp.y + "; value=" + lp.value);
    }
    prl("sum = " + result);
    
    List<Basin> bas = findBasins(low, map);
    
    return result;
  }

  
  private List<Basin> findBasins(List<LowPoint> low, int[][] map) {
    
    for (LowPoint lp : low) {
      findLocations(lp, map);
    }
    return null;
  }

  /**
   * redenerend vanuit een punt, op het moment dat 1 voldoet mag ie toegevoegd worden.
   * indien x+1 < 9, voeg dit punt toe
   * indien x-1 < 9, voeg dit punt toe
   * indien y+1 < 9, voeg dit punt toe
   * indien y-1 < 9, voeg dit punt toe
   * 
   * @param lp
   * @param map
   */
  private void findLocations(LowPoint lp, int[][] map) {
  }
  

  private List<LowPoint> analyse(int[][] map) {
    long sum = 0L;
    List<LowPoint> low = new ArrayList<>();
    // iterate over all lines
    for (int y=0; y < maxY; y++) {
      // inspect the first line
      if (y==0) {
        for (int x=0; x < maxX; x++) {
          // inspect the 1 column
          if (x==0) {
            if (map[x][y] < map[x+1][y] && map[x][y] < map[x][y+1]) {
              low.add(new LowPoint(x,y,map[x][y])); 
            }
          } else if (x == maxX -1) {
            // inspect the last column
            if (map[x][y] < map[x-1][y] && map[x][y] < map[x][y+1]) {
              low.add(new LowPoint(x,y,map[x][y])); 
            }            
          } else {
            if (map[x][y] < map[x-1][y] && map[x][y] < map[x+1][y] && map[x][y] < map[x][y+1]) {
              low.add(new LowPoint(x,y,map[x][y]));
            }
          }         
        }
      } else if (y == maxY -1) {
        // inspect the last line
        for (int x=0; x < maxX; x++) {
          // inspect the 1 column
          if (x==0) {
            if (map[x][y] < map[x+1][y] && map[x][y] < map[x][y-1]) {
              low.add(new LowPoint(x,y,map[x][y])); 
            }
          } else if (x == maxX -1) {
            // inspect the last column
            if (map[x][y] < map[x-1][y] && map[x][y] < map[x][y-1]) {
              low.add(new LowPoint(x,y,map[x][y])); 
            }            
          } else {
            if (map[x][y] < map[x-1][y] && map[x][y] < map[x+1][y] && map[x][y] < map[x][y-1]) {
              low.add(new LowPoint(x,y,map[x][y]));
            }
          }         
        }
        
      } else {
        // all other lines between the fist and last line
        for (int x=0; x < maxX; x++) {
          // inspect the 1 column
          if (x==0) {
            if (map[x][y] < map[x+1][y] && map[x][y] < map[x][y+1] && map[x][y] < map[x][y-1]) {
              low.add(new LowPoint(x,y,map[x][y])); 
            }
          } else if (x == maxX -1) {
            // inspect the last column
            if (map[x][y] < map[x-1][y] && map[x][y] < map[x][y+1] && map[x][y] < map[x][y-1]) {
              low.add(new LowPoint(x,y,map[x][y])); 
            }            
          } else {
            if (map[x][y] < map[x-1][y] && map[x][y] < map[x+1][y] && map[x][y] < map[x][y+1] && map[x][y] < map[x][y-1]) {
              low.add(new LowPoint(x,y,map[x][y]));
            }
          }         
        }        
      }
    }
    

    return low;
  }

  private int [][] readFile() {
    int map[][] = new int [maxX][maxY]; 
    String input = "";
    int y =0;
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      while ((input = reader.readLine()) != null) {        
        for (int x=0; x < input.length();x++) {
          map[x][y] = Integer.parseInt(input.substring(x, x+1));          
        }
        y++;
      }      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }    
    return map;
  }
  
  private void printArray(int [][] map) {
    for(int y=0; y < maxY; y++) {
      for(int x=0; x < maxX; x++) {
        pr(""+map[x][y]);
      }
      prl("");
    }
  }
  
  public void printResult() {    
    prl(this + "; Result: " + this.getResult());
  }
  
  class LowPoint {
    int x;
    int y;
    int value;
    int risk;
    
    public LowPoint(int x, int y, int value) {
      this.x = x;
      this.y = y;
      this.value = value;
      this.risk = value + 1;
    }
  }
  class Basin {
    int nr;
    int size;
    List<LowPoint> lp;
  }
    
}

