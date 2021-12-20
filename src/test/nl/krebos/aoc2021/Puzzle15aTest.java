package nl.krebos.aoc2021;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle15aTest {
  
  @Test
  void test1a() {
    Puzzle15a pzzl = new Puzzle15a("Chiton", 15, 1, "data/puzzle15a1.txt");
    pzzl.maxX=10;
    pzzl.maxY=10;
    long result = pzzl.execute();
//    assertEquals(1588,result);
  }
     
}

