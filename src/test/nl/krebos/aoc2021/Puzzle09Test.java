package nl.krebos.aoc2021;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle09Test {
  
  @Test
  void test1() {
    Puzzle09 pzzl = new Puzzle09("Smoke Basin", 9, 1, "data/puzzle09a.txt");
    pzzl.maxX=10;
    pzzl.maxY=5;
    long result = pzzl.execute();
    assertEquals(15,result);
  }
  
  @Test
  void test2() {
    Puzzle09 pzzl = new Puzzle09("Smoke Basin", 9, 1, "data/puzzle09b.txt");
    pzzl.maxX=100;
    pzzl.maxY=100;
    long result = pzzl.execute();
    assertEquals(575,result);
  }
  
  @Test
  void test3() {
    Puzzle09 pzzl = new Puzzle09("-", 9, 2, "data/puzzle09a.txt");
    pzzl.maxX=10;
    pzzl.maxY=5;
    long result = pzzl.execute();
    
    //assertEquals(61229,result);
  }  
  
  @Test
  void test4() {
    Puzzle09 pzzl = new Puzzle09("-", 9, 2, "data/puzzle09b.txt");
    long result = pzzl.execute();
    assertEquals(1010460,result);
  }  
}

