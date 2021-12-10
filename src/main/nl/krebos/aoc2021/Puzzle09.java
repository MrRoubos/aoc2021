package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Puzzle09 extends Puzzle {
  public int maxX =0;
  public int maxY =0;
  List<Point> points = new ArrayList<>();
  Map<String, Point> mpoints = new HashMap<>();
      
  public Puzzle09(String name, int day, int part, String file) {
    super(name, day, part, file);
  }

  public long execute() {    
    long result = 0L;

    if (this.getPart() == 1) {
      int map [][] = readFile();
      result = part1(map);
    } else {
      int map1 [][] = readFile();
      Point map [][] = readFile2();
      result = part2(map, map1);
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

  private long part2(Point[][] map, int[][] map1) {
    long result = 0L;
    
    List<LowPoint> low = analyse(map1);
    for (LowPoint lp : low) {
      result += lp.risk;
      prl("low x=" + lp.x + "; y="+ lp.y + "; value=" + lp.value);
    }
    prl("sum = " + result);
    
    printArray(map1);
    
    int bNr = 0;    
    for (LowPoint lp : low) {
      findBasins(bNr, low.get(1), map);
    }
    return result;
  }

  
  private List<Basin> findBasins(int bNr, LowPoint low, Point[][] map) {
    int x = low.x;
    int y = low.y;
    
    int count=0;
    boolean process = true;
    while (process) {
      createBasin(bNr, x,y, map);
      count++;
      if (count > 9) {
        process = false;
      }
    }
    return null;
  }


  private void createBasin(int bNr, int x1, int y1, Point[][] map) {
    Point p = map[x1][y1];
    int x = p.x;
    int y = p.y;
    p.basin = bNr;
    
    if (p.right && x < maxX-1) {
      if (map[x+1][y].value < 9) {
        prl("Add to right");
        p.right = false;
        map[x+1][y].left = false;
        map[x+1][y].basin = bNr;
      }
    }
    if (p.left && x > 0) {
      if (map[x-1][y].value < 9) {
        prl("Add to left");
        p.left = false;        
        map[x-1][y].right=false;
        map[x-1][y].basin = bNr;
      }
    }    
    if (p.down && y < maxY-1) {
      if (map[x][y+1].value < 9) {
        prl("Add to down");
        p.down = false;
        map[x][y+1].up = false;        
        map[x][y+1].basin = bNr;
      }
      if (p.up && y > 0) {
        if (map[x][y-1].value < 9) {
          prl("Add to up");
          p.up = false;
          map[x][y-1].down = false;
          map[x][y-1].basin = bNr;
        }
      }        
    }
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
  
  private Point [][] readFile2() {
    Point map[][] = new Point [maxX][maxY]; 
    String input = "";
    int y =0;
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      while ((input = reader.readLine()) != null) {        
        for (int x=0; x < input.length();x++) {
          Point p = new Point(x,y,Integer.parseInt(input.substring(x, x+1)));           
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
  
  class Point {
    int x;
    int y;
    int value;
    int basin;
    boolean left;
    boolean right;
    boolean up;
    boolean down;
    
    public Point (int x, int y, int value) {
      this.x = x;
      this.y = y;
      this.value = value;
      this.right=true;
      this.down=true;
      this.left=true;
      this.up=true;
    }
  }
}

