package nl.krebos.aoc2021;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle12Test {
  
  @Test
  void test1a() {
    Puzzle12 pzzl = new Puzzle12("Passage Pathing", 12, 1, "data/puzzle12a1.txt");
    long result = pzzl.execute();
    //assertEquals(9,result);
  }
  
  @Test
  void test1b() {
    Puzzle12 pzzl = new Puzzle12("Passage Pathing", 12, 1, "data/puzzle12a2.txt");
    long result = pzzl.execute();
    //assertEquals(1656,result);
  }  
}

