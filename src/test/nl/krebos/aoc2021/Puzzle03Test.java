package nl.krebos.aoc2021;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle03Test {
  
  @Test
  void test1ref() {
    Puzzle03 pzzl = new Puzzle03("Binary Diagnostic", 3, 1, "data/puzzle03a.txt");
    pzzl.byteLength = 5;
    long result = pzzl.part1();
    assertEquals(198,result);
  }

  @Test
  void test1() {
    Puzzle03 pzzl = new Puzzle03("Binary Diagnostic", 3, 1, "data/puzzle03b.txt");
    pzzl.byteLength = 12;
    long result = pzzl.part1();
    assertEquals(3687446,result);
  }

  @Test
  void test2ref() {
    Puzzle03 pzzl = new Puzzle03("Binary Diagnostic", 3, 2, "data/puzzle03a.txt");
    pzzl.byteLength = 5;
    long result = pzzl.part2();
    assertEquals(230,result);
  }
  
  @Test
  void test2() {
    Puzzle03 pzzl = new Puzzle03("Binary Diagnostic", 3, 2, "data/puzzle03b.txt");
    pzzl.byteLength = 12;
    long result = pzzl.part2();
    //assertEquals(230,result);
  }  
}
