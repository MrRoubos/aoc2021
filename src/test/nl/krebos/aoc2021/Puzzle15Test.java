package nl.krebos.aoc2021;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle15Test {
  
  @Test
  void test1a() {
    Puzzle15 pzzl = new Puzzle15("Chiton", 15, 1, "data/puzzle15a1.txt");
    pzzl.maxX=10;
    pzzl.maxY=10;
    long result = pzzl.execute();
//    assertEquals(1588,result);
  }
  
  @Test
  void test1b() {
    Puzzle15 pzzl = new Puzzle15("Chiton", 15, 1, "data/puzzle15a2.txt");
    pzzl.maxX=100;
    pzzl.maxY=100;    
    long result = pzzl.execute();
    assertEquals(3306,result);
  }  
  
  
  @Test
  void test2a() {
    Puzzle15 pzzl = new Puzzle15("Chiton", 15, 2, "data/puzzle15a1.txt");
    long result = pzzl.execute();
    assertEquals(1588,result);
  }    
}

