package nl.krebos.aoc2021;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle10Test {
  
  @Test
  void test1() {
    Puzzle10 pzzl = new Puzzle10("Syntax Scoring", 10, 1, "data/puzzle10a.txt");
    long result = pzzl.execute();
    assertEquals(26397,result);
  }
  
  @Test
  void test2() {
    Puzzle10 pzzl = new Puzzle10("Syntax Scoring", 10, 1, "data/puzzle10b.txt");
    long result = pzzl.execute();
    assertEquals(436497,result);
  }
  
  @Test
  void test3() {
    Puzzle10 pzzl = new Puzzle10("Syntax Scoring", 10, 2, "data/puzzle10a.txt");
    long result = pzzl.execute();
    assertEquals(288957,result);
  }  
  
  @Test
  void test4() {
    Puzzle10 pzzl = new Puzzle10("-", 10, 2, "data/puzzle10b.txt");
    long result = pzzl.execute();
    //assertEquals(1010460,result);
  }  
}

