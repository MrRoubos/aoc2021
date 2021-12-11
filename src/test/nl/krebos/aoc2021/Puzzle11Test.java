package nl.krebos.aoc2021;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle11Test {
  
  @Test
  void test1a() {
    Puzzle11 pzzl = new Puzzle11("Dumbo Octopus", 11, 1, "data/puzzle11a1.txt");
    pzzl.maxX=5;
    pzzl.maxY=5;
    pzzl.steps=2;
    long result = pzzl.execute();
    assertEquals(9,result);
  }
  
  @Test
  void test1() {
    Puzzle11 pzzl = new Puzzle11("Dumbo Octopus", 11, 1, "data/puzzle11a.txt");
    pzzl.maxX=10;
    pzzl.maxY=10;
    pzzl.steps=100;
    long result = pzzl.execute();
    assertEquals(1656,result);
  }  
  
  @Test
  void test1b() {
    Puzzle11 pzzl = new Puzzle11("Dumbo Octopus", 11, 1, "data/puzzle11a.txt");
    pzzl.maxX=10;
    pzzl.maxY=10;
    pzzl.steps=200;
    long result = pzzl.execute();
    assertEquals(3125,result);
  }  
  
  @Test
  void test2() {
    Puzzle11 pzzl = new Puzzle11("Dumbo Octopus", 11, 2, "data/puzzle11b.txt");
    pzzl.maxX=10;
    pzzl.maxY=10;
    pzzl.steps=100;    
    long result = pzzl.execute();
    //assertEquals(1691,result);
  }
  
  @Test
  void test2a() {
    Puzzle11 pzzl = new Puzzle11("Dumbo Octopus", 11, 2, "data/puzzle11b.txt");
    pzzl.maxX=10;
    pzzl.maxY=10;
    pzzl.steps=1000;    
    long result = pzzl.execute();
    // on laptop, 346 ms 
    assertEquals(216,result);
  }      
}

