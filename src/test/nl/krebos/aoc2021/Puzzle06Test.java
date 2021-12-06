package nl.krebos.aoc2021;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle06Test {
  
  @Test
  void test1ref() {
    Puzzle06 pzzl = new Puzzle06("Hydrothermal Venture", 6, "1ref");
    pzzl.setInputFile("data/puzzle06a.txt");
    pzzl.days=18;
    long result = pzzl.part1();
    assertEquals(26,result);
  }
  
  @Test
  void test1ref2() {
    Puzzle06 pzzl = new Puzzle06("Hydrothermal Venture", 6, "1ref2");
    pzzl.setInputFile("data/puzzle06a.txt");
    pzzl.days=80;
    long result = pzzl.part1();
    assertEquals(5934,result);
  }  

  @Test
  void test1() {
    Puzzle06 pzzl = new Puzzle06("Hydrothermal Venture", 6, "1");
    pzzl.setInputFile("data/puzzle06b.txt");
    pzzl.days=80;
    long result = pzzl.part1();
    assertEquals(359344,result);
  }  
  
}

