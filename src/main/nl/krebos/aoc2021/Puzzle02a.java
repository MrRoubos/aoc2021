package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle02a extends Puzzle {
  
  public Puzzle02a(String name, int day, String part) {
    super(name, day, part);
  }

  public long part1() {
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      Long x = 0L, depth = 0L;
      String input;
      while ((input = reader.readLine()) != null) {
        Line line = processLine(input);
        switch (line.direction) {
          case "forward": 
            x += line.distance;
            break;
          case "down": 
            depth += line.distance;
            break;
          case "up":
            depth -= line.distance;
            break;
        }
        System.out.println("horizontal: " + x + "; depth: " + depth);
      }
      this.setResult(x * depth);
      System.out.println("horizontal: " + x + "; depth: " + depth + "; multiplied = " +this.getResult());
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    this.printResult();
    return this.getResult();
  }

  public long part2() {    
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      Long x = 0L, depth = 0L, aim = 0L;
      String input;
      while ((input = reader.readLine()) != null) {
        Line line = processLine(input);
        switch (line.direction) {
          case "forward": 
            x += line.distance;
            if (aim > 0) {
              depth += (aim * line.distance); 
            }
            break;
          case "down": 
            aim += line.distance;
            break;
          case "up":
            aim -= line.distance;
            break;
          }
        System.out.println("horizontal: " + x + "; depth: " + depth + "; aim: " + aim);
      }    
      this.setResult(x * depth);
      System.out.println("horizontal: " + x + "; depth: " + depth + "; multiplied = " +this.getResult());
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }          
    this.printResult();
    return this.getResult();
  }  
  
  public void printResult() {    
    System.out.println(this + "; Result: " + this.getResult());
  }
  
  private static Line processLine(String input) {
    String [] values = input.split(" ");
    Line line = new Line();
    line.direction = values[0];
    line.distance = Long.parseLong(values[1]);
    return line;
  }
}
class Line {
  String direction;
  Long distance;
}