package nl.krebos.aoc2021;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle20Test {
  
  @Test
  void test1a() {
    Puzzle20 pzzl = new Puzzle20("Trench Map", 20, 1, "data/puzzle20a1.txt");
    long result = pzzl.execute();
    assertEquals(24,result);
  }
  
  @Test
  void test1b() {
    Puzzle20 pzzl = new Puzzle20("Trench Map", 20, 1, "data/puzzle20a2.txt");
    long result = pzzl.execute();
    assertEquals(35,result);
  }         
  
  @Test
  void test1c() {
    Puzzle20 pzzl = new Puzzle20("Trench Map", 20, 1, "data/puzzle20a3.txt");
    long result = pzzl.execute();
    //assertEquals(16,result);
  }  
  
  @Test
  void test1d() {
    Puzzle20 pzzl = new Puzzle20("Trench Map", 20, 1, "data/puzzle20a4.txt");
    long result = pzzl.execute();
    //assertEquals(16,result);
  }  
}

