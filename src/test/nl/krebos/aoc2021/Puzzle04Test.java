package nl.krebos.aoc2021;


import org.junit.jupiter.api.Test;

import nl.krebos.aoc2021.Puzzle04;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle04Test {
  
  @Test
  void test1ref() {
    Puzzle04 pzzl = new Puzzle04("Giant Squid", 4, "1ref");
    pzzl.setInputFile("data/puzzle04a.txt");
    long result = pzzl.part1();
    assertEquals(4512,result);
  }
  
  @Test
  void test1() {
    Puzzle04 pzzl = new Puzzle04("Giant Squid", 4, "1");
    pzzl.setInputFile("data/puzzle04b.txt");
    long result = pzzl.part1();
    assertEquals(54275,result);
  }  
  
  @Test
  void test2ref() {
    Puzzle04 pzzl = new Puzzle04("Giant Squid", 4, "2ref");
    pzzl.setInputFile("data/puzzle04a.txt");
    long result = pzzl.part2();
    assertEquals(1924,result);
  }  
  
  @Test
  void test2() {
    Puzzle04 pzzl = new Puzzle04("Giant Squid", 4, "2");
    pzzl.setInputFile("data/puzzle04b.txt");
    long result = pzzl.part2();
    assertEquals(13158,result);
  }  
}
