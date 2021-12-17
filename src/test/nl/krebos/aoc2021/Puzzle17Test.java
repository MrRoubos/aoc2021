package nl.krebos.aoc2021;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle17Test {
  
  @Test
  void test1a() {
    Puzzle17 pzzl = new Puzzle17("Trick Shot", 17, 1, "");
    pzzl.tx1=20;
    pzzl.tx2=30;
    pzzl.ty1=-5;
    pzzl.ty2=-10;
    long result = pzzl.execute();
    //assertEquals(16,result);
  }       
  
  @Test
  void test1b() {
    Puzzle17 pzzl = new Puzzle17("Trick Shot", 17, 1, "");
    pzzl.tx1=88;
    pzzl.tx2=125;
    pzzl.ty1=-103;
    pzzl.ty2=-157;
    
    long result = pzzl.execute();
    //assertEquals(16,result);
  }  
}

