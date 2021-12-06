package nl.krebos.aoc2021;


import org.junit.jupiter.api.Test;

import nl.krebos.aoc2021.Puzzle05;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle05Test {
  
  @Test
  void test1ref() {
    Puzzle05 pzzl = new Puzzle05("Hydrothermal Venture", 5, "1ref");
    pzzl.setInputFile("data/puzzle05a.txt");
    long result = pzzl.part1();
    assertEquals(5,result);
  }

  @Test
  void test1() {
    Puzzle05 pzzl = new Puzzle05("Hydrothermal Venture", 5, "1");
    pzzl.setInputFile("data/puzzle05b.txt");
    pzzl.maxX=1000;
    pzzl.maxY=1000;
    long result = pzzl.part1();
    assertEquals(6225,result);
  }  

  @Test
  void test2ref() {
    Puzzle05 pzzl = new Puzzle05("Hydrothermal Venture", 5, "2ref");
    pzzl.setInputFile("data/puzzle05a.txt");
    long result = pzzl.part1();
    assertEquals(12,result);
  }
  
  @Test
  void test2() {
    Puzzle05 pzzl = new Puzzle05("Hydrothermal Venture", 5, "2");
    pzzl.setInputFile("data/puzzle05b.txt");
    pzzl.maxX=1000;
    pzzl.maxY=1000;
    
    long result = pzzl.part1();
    assertEquals(22116,result);
  }  
}

