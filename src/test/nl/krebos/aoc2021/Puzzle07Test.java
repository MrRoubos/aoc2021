package nl.krebos.aoc2021;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle07Test {
  
  @Test
  void test1ref() {
    Puzzle07 pzzl = new Puzzle07("The Treachery of Whales", 7, "1ref");
    pzzl.setInputFile("data/puzzle07a.txt");
    long result = pzzl.part1();
    assertEquals(37,result);
  }
  
  @Test
  void test1() {
    Puzzle07 pzzl = new Puzzle07("The Treachery of Whales", 7, "1");
    pzzl.setInputFile("data/puzzle07b.txt");
    long result = pzzl.part1();
    assertEquals(352707,result);
  }  
  
  @Test 
  void test2ref() {
    Puzzle07 pzzl = new Puzzle07("The Treachery of Whales", 7, "2ref");
    pzzl.setInputFile("data/puzzle07a.txt");
    pzzl.part2 = true;
    long result = pzzl.part1();
    assertEquals(168,result);
  }
  
  @Test 
  void test2() {
    Puzzle07 pzzl = new Puzzle07("The Treachery of Whales", 7, "2");
    pzzl.setInputFile("data/puzzle07b.txt");
    pzzl.part2 = true;
    long result = pzzl.part1();
    assertEquals(95519693,result);
  }
}

