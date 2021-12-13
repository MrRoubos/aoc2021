package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle13 extends Puzzle {
  String map [][];
  String fold [];
  int maxX;
  int maxY;
  int maxFold;
  int stopFold;
  
  public Puzzle13(String name, int day, int part, String file) {
    super(name, day, part, file);    
  }

  public long execute() {    
    long result = 0L;
    Stopwatch stopwatch = new Stopwatch();
    
    if (this.getPart() == 1) {
      result = part1();
      stopwatch.split("end of part1");
    } else {
      result = part2();
      stopwatch.split("end of part2");
    }
    
    this.setResult(result);
    printResult(); 
    return result;
  }

  private long part1() {
    long result = 0L;   
    readFile();
    printMap();
    for (int i=0; i<maxFold; i++) {
      prl("Fold action: " + i);
      fold(fold[i]);
      result = countVisible();
      if (i==stopFold) {
        break;        
      }
    }
    printMap();
    
    return result;
  }

  private long countVisible() {
    int count=0;
    for (int y=0; y<maxY; y++) {
      for (int x =0; x<maxX; x++) {
        if (map[x][y] == "#") count++; 
      }
    }
    prl("Visible dots: " + count);
    return count;
  }

  private void fold(String fold) {
    String [] parts = fold.split("=");
    String xy = parts[0];
    String line = parts[1];   

    if (xy.equals("y")) {
      // fold an horizontal line
      prl("fold horizontal");
      int y = Integer.parseInt(line);
      int inc=1;
      for (int y1=y+1; y1<maxY; y1++) {
        for (int x=0; x<maxX; x++ ) {
          if (map[x][y-inc] == ".") {
            map[x][y-inc]=map[x][y1];            
          }         
        }
        inc++;
      }
      
      String newMap [][] = new String[maxX][y];
      for (int y1=0; y1<y; y1++) {
        for (int x =0; x<maxX; x++) {
          newMap[x][y1] = map[x][y1];
        }
      }      
      map = newMap;
      maxY = y;
    } else if (xy.equals("x")) {
      // fold an vertical line
      prl("fold vertical");
      int x = Integer.parseInt(line);
      int inc=1;
      
      for (int y=0; y<maxY; y++ ) {
        for (int x1=x+1; x1<maxX; x1++) {
          if (map[x-inc][y] == ".") {
            map[x-inc][y]=map[x1][y];            
          }
          inc++;
        }
        inc = 1;
      }
           
      String newMap [][] = new String[x][maxY];
      for (int y=0; y<maxY; y++ ) {
        for (int x1 =0; x1<x; x1++) {
          newMap[x1][y] = map[x1][y];
        }
      }      
      map = newMap;
      maxX = x;      
    }    
  }

  private void printMap() {
    for (int y=0; y<maxY; y++) {
      for (int x =0; x<maxX; x++) {
        pr(map[x][y] + " ");
      }
      prl("");
    }
    prl("fold instructions");
    for (int i=0; i<maxFold; i++) {
      prl("fold: " + fold[i]);
    }
      
  }
  private void initMap() {
    for (int y=0; y<maxY; y++) {
      for (int x =0; x<maxX; x++) {
        map[x][y] = ".";
      }
    }
  }  

  private void readFile() {
    String input = "";
    int foldLines=0;
    
    // read the file 2 times, to determine the max size of the map
    prl("First determine the max sizes of the map");
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      boolean coordinates = true;
      while ((input = reader.readLine()) != null) {
        if (input.trim().length() ==0) {
          prl("empty line, so now the fold instructions follow");
          coordinates = false;
        }        
        if (coordinates) {
          prl("Input : " + input);
          String [] coord = input.split(",");
          prl("coord : " + coord[0] + " - " + coord[1]);
          int x1 = Integer.parseInt(coord[0]);
          int y1 = Integer.parseInt(coord[1]);
          if (x1 > maxX) maxX = x1;
          if (y1 > maxY) maxY = y1;
        } else {
          prl("input: " + input);
          foldLines++;
        }
      }      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }      
    maxX++;
    maxY++;
    maxFold = foldLines - 1;
    prl("maxX = " + maxX + "; maxY =" + maxY + "; foldLines: " + maxFold);
    map = new String[maxX][maxY];
    fold = new String[foldLines];
    initMap();
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      boolean coordinates = true;
      int line=0;
      while ((input = reader.readLine()) != null) {
        if (input.trim().length() ==0) {
          prl("empty line, so now the fold instructions follow");
          coordinates = false;
        }        
        if (coordinates) {
          String [] coord = input.split(",");
          int x1 = Integer.parseInt(coord[0]);
          int y1 = Integer.parseInt(coord[1]);
          map[x1][y1] = "#";          
        } else {
          if (input.trim().length() > 0) {
            fold[line] = input.substring(11);
            line++;
          }
        }
      }      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }          
  }


  private long part2() {
    long result = 0L;
    return result;
  }
  
  public void printResult() {    
    prl(this + "; Result: " + this.getResult());
  } 
}
