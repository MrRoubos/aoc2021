package nl.krebos.aoc2021;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle16Test {
  
  @Test
  void test1a() {
    // 8A004A801A8002F478
    Puzzle16 pzzl = new Puzzle16("Packet Decoder", 16, 1, "data/puzzle16a1.txt");
    long result = pzzl.execute();
    assertEquals(16,result);
  }     
  @Test
  void test1b() {
    // 620080001611562C8802118E34
    Puzzle16 pzzl = new Puzzle16("Packet Decoder", 16, 1, "data/puzzle16a2.txt");
    long result = pzzl.execute();
    assertEquals(16,result);
  }  
}

