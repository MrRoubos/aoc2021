package nl.krebos.aoc2021;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle02aTest {
  
  @Test
  void test1ref() {
    Puzzle02a pzzl = new Puzzle02a("Dive", 2, 1, "data/puzzle02a.txt");
    long result = pzzl.part1();
    assertEquals(150,result);
  }

  @Test
  void test1() {
    Puzzle02a pzzl = new Puzzle02a("Dive", 2, 1, "data/puzzle02a2.txt");
    long result = pzzl.part1();
    assertEquals(2272262,result);
  }
  
  @Test
  void test2ref() {
    Puzzle02a pzzl = new Puzzle02a("Dive", 2, 2, "data/puzzle02a.txt");
    long result = pzzl.part2();
    assertEquals(900,result);
  }
  
  @Test
  void test2() {
    Puzzle02a pzzl = new Puzzle02a("Dive", 2, 2, "data/puzzle02a2.txt");
    long result = pzzl.part2();
    assertEquals(2134882034,result);
  }  
  
}
