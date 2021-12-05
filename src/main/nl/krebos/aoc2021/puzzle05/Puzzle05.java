package nl.krebos.aoc2021.puzzle05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import nl.krebos.aoc2021.Puzzle;

public class Puzzle05 extends Puzzle {
  public int maxX = 10;
  public int maxY = 10;
  private boolean part2 = false; 
  
  
  public Puzzle05(String name, int day, String part) {
    super(name, day, part);
  }

  public long part1() {
    String input = "";
    int [][] map = new int[maxX][maxY];
    
    if (this.getPart().charAt(0) == '2') {
      part2 = true;
    }
    
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      while ((input = reader.readLine()) != null) {
        Line line = processLine(input);
        if (line != null) {
          drawLine(line, map);
        }
      }        
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    drawMap(map);    
    this.setResult(determineOverlap(map));
    
    this.printResult();
    return this.getResult();
  }

  private long determineOverlap(int[][] map) {
    long total = 0;
    for (int y=0; y < maxY; y++) {
      for (int x=0; x < maxX; x++ ) {
        if (map[x][y] >= 2) {
          total++;
        }         
      }  
    }
    return total;
  }

  private void drawMap(int[][] map) {
    for (int y=0; y < maxY; y++) {
      for (int x=0; x < maxX; x++ ) {
        if (map[x][y]==0) {
          System.out.print(". ");
        } else {
          System.out.print(map[x][y] + " "); 
        }
        
      }
      System.out.println("");
    }
  }

  private void drawLine(Line line, int [][] map) {
    int x1 = line.start.x;
    int y1 = line.start.y;
    int x2 = line.end.x;
    int y2 = line.end.y;
    int y=0;
    int x=0;
    if (x1 == x2) {
      // vertical
      if (y1 > y2) {
        y = y2;
        y2 = y1;
        y1 = y;
      }
      for (int i = y1;  i <= y2; i++) {
        map[x1][i]++;
      }
    } else if (y1 == y2) {
      // horizontal
      if (x1 > x2) {
        x = x2;
        x2 = x1;
        x1 = x;
      }      
      for (int i = x1;  i <= x2; i++) {
        map[i][y1]++;
      }      
    } else {
      // part2, diagonal
      if (part2) {
        System.out.println("part2");
        //System.out.println("x1="+x1+";y1="+y1+";x2="+x2+";y2="+y2 );
        boolean drawing = true;
        x= x1;
        y= y1;
        while (drawing) {
          //System.out.println("x="+x+";y="+y);
          map[x][y]++;
          
          if (x1 > x2) x--;
            else x++;
          if (y1 > y2) y--;
            else  y++;         
          if (x == x2) drawing = false;
          if (y == y2) drawing = false;  
        }
        map[x][y]++;
      }
    }
  }

  private Line processLine(String input) {
    // let's try to use a regexp, but I think it is too hard for now :(       
    System.out.println("input: " + input);
    String startend[] = input.split(" -> ");
    //System.out.println("startend: " + startend[0] + "; " + startend[1]);
    Line line = new Line();
    String [] coord = startend[0].split(",");
    line.addStart(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
    coord = startend[1].split(",");
    line.addEnd(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
    
    if (line.start.x == line.end.x || line.start.y == line.end.y) {
      // for part 1 of the puzzle we are only interested in 
      // horizontal or vertical lines
      line.printPoint();
    } else {
      if (! part2) {
        System.out.println("Not horizontal or vertical");
        line = null;        
      } else {
        // for part2 we are also interested in diagonal lines, only 45 degrees
        int x1 = line.start.x;
        int x2 = line.end.x;
        int y1 = line.start.y;
        int y2 = line.end.y;
        int xres = 0;
        int yres = 0;
        if (x1 > x2) xres = x1-x2;
        else xres = x2-x1;
        if (y1 > y2) yres = y1-y2;
        else yres = y2-y1;
        if (xres != yres) {
          System.out.println("No diagonal, so skip!!!");
          System.exit(0);
          line = null;
        } else {
          System.out.println("Diagonal");
        }
      }

    }           
    return line;
 }
   

  public void printResult() {    
    System.out.println(this + "; Result: " + this.getResult());
  }   
}


class Line {
  Point start;
  Point end;

  void addStart(int x, int y) {
    this.start = new Point(x,y);
  }
  void addEnd(int x, int y) {
    this.end = new Point(x,y);
  }
  void printPoint() {
    System.out.println("Start: x="+start.x + ";y="+start.y);
    System.out.println("End  : x="+end.x + ";y="+end.y);
  }
}

class Point {
  int x;
  int y;
  
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
