package nl.krebos.aoc2021;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle13Test {
  
  @Test
  void test1a() {
    Puzzle13 pzzl = new Puzzle13("Transparent Origami", 13, 1, "data/puzzle13a1.txt");
    pzzl.stopFold=0;
    long result = pzzl.execute();
    assertEquals(17,result);
  }
  
  @Test
  void test1b() {
    Puzzle13 pzzl = new Puzzle13("Transparent Origami", 13, 1, "data/puzzle13a2.txt");
    pzzl.stopFold=0;
    long result = pzzl.execute();
    assertEquals(814,result);
  }  
  
  
  @Test
  void test2() {
    Puzzle13 pzzl = new Puzzle13("Transparent Origami", 13, 1, "data/puzzle13a2.txt");
    pzzl.stopFold=99;
    long result = pzzl.execute();
    //assertEquals(814,result);
  }  
}

