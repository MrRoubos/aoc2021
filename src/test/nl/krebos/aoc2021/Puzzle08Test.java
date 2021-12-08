package nl.krebos.aoc2021;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle08Test {
  
  @Test
  void test1ref() {
    Puzzle08 pzzl = new Puzzle08("Seven Segment Search", 8, "1ref");
    pzzl.setInputFile("data/puzzle08a.txt");
    long result = pzzl.part1();
    assertEquals(26,result);
  }
  
  @Test
  void test1() {
    Puzzle08 pzzl = new Puzzle08("Seven Segment Search", 8, "1");
    pzzl.setInputFile("data/puzzle08b.txt");
    long result = pzzl.part1();
    assertEquals(493,result);
  }
  
  @Test
  void test2ref() {
    Puzzle08 pzzl = new Puzzle08("Seven Segment Search", 8, "2ref");
    pzzl.setInputFile("data/puzzle08a.txt");
    long result = pzzl.part2();
    assertEquals(61229,result);
  }  
  
  @Test
  void test2() {
    Puzzle08 pzzl = new Puzzle08("Seven Segment Search", 8, "2");
    pzzl.setInputFile("data/puzzle08b.txt");
    long result = pzzl.part2();
    assertEquals(1010460,result);
  }  
}

