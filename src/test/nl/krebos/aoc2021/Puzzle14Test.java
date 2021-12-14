package nl.krebos.aoc2021;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle14Test {
  
  @Test
  void test1a() {
    Puzzle14 pzzl = new Puzzle14("Extended Polymerization", 14, 1, "data/puzzle14a1.txt");
    pzzl.steps = 10;
    long result = pzzl.execute();
    assertEquals(1588,result);
  }
  
  @Test
  void test1b() {
    Puzzle14 pzzl = new Puzzle14("Extended Polymerization", 14, 1, "data/puzzle14a2.txt");
    pzzl.steps = 10;
    long result = pzzl.execute();
    assertEquals(3306,result);
  }  
  
  
  @Test
  void test2() {
    Puzzle14 pzzl = new Puzzle14("Extended Polymerization", 14, 1, "data/puzzle14a1.txt");
    //pzzl.steps = 40;
    pzzl.steps = 10;
    long result = pzzl.execute();
    //assertEquals(814,result);
  }  
}

