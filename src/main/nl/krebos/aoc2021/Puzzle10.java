package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

public class Puzzle10 extends Puzzle {
  Map<String, Integer> invalidMap = new HashMap<>();
      
  public Puzzle10(String name, int day, int part, String file) {
    super(name, day, part, file);
  }

  public long execute() {    
    long result = 0L;

    if (this.getPart() == 1) {
      result = part1();
    } else {
      result = part2();
    }
    this.setResult(result);
    printResult();
    return result;
  }
  

  private long part1() {
    long result = 0L;   
    String input = "";

    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      while ((input = reader.readLine()) != null) {
        corrupt(input);        
      }      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }    
      
    for (String str : invalidMap.keySet()) {
      prl(str + " -> " + invalidMap.get(str));
      switch (str) {
        case ")": result += invalidMap.get(str) * 3; break;
        case "]": result += invalidMap.get(str) * 57; break;
        case "}": result += invalidMap.get(str) * 1197; break;
        case ">": result += invalidMap.get(str) * 25137; break;
      }
    }
    this.setResult(result);
    return result;
  }

  private void countInvalid(String chunk) {
    Integer count = invalidMap.get(chunk);
    if (count != null) {
      count++;
      invalidMap.put(chunk, count);
    } else {
      invalidMap.put(chunk, 1);
    }        
  }

  private long part2() {
    long result = 0L;   
    String input = "";
    List<Long> scores = new ArrayList<Long>();
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      while ((input = reader.readLine()) != null) {        
        if (! corrupt(input)) {
          prl("this line is incomplete: " + input);
          result = completeLine(input);
          scores.add(result);
        }
      }        
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Collections.sort(scores);
    prl("total: " + scores.size());
    int middle = scores.size()/2;
    prl("score middle: " + scores.get(middle));    
    return scores.get(middle);
  }

  private long completeLine(String input) {
    Stack <String >stack = new Stack<>();
    StringBuffer sb = new StringBuffer();
    
    for (int x=0; x < input.length();x++) {
      String chunk = input.substring(x, x+1);
      switch (chunk) {
      case "(":
      case "[":
      case "{":
      case "<":
        stack.push(chunk);
        break;
      case ")":      
      case "]": 
      case "}": 
      case ">": 
        stack.pop();
        break;
      }
    }
    String latest = "";
    while (stack.size() > 0) {
      latest = stack.pop();
      switch (latest) {
        case "(": sb.append(")"); break;
        case "[": sb.append("]"); break;
        case "{": sb.append("}"); break;
        case "<": sb.append(">"); break;
      }      
    }
    stack.forEach(it -> prl(it));
    prl("completion: " + sb.toString());
    return calculateScore(sb.toString());
  }

  private long calculateScore(String string) {
    int value = 0;
    long total = 0L;
    for (int i=0; i < string.length(); i++) {
      switch (string.substring(i,i+1)) {
        case ")": value = 1; break;
        case "]": value = 2; break;
        case "}": value = 3; break;
        case ">": value = 4; break;
      }
      total = (5 * total) + value;
      prl(string.substring(i,i+1) + " " + total);
    }
    prl("score: " + total);
    return total;
  }

  private boolean corrupt(String input) {
    Stack <String >stack = new Stack<>();
    boolean corrupt = false;        
    // itterate over each character in the line
    for (int x=0; x < input.length();x++) {
      String chunk = input.substring(x, x+1);
      String latest = "";
      switch (chunk) {
      case "(":
      case "[":
      case "{":
      case "<":
        stack.push(chunk);
        break;
      case ")": {
          latest = stack.peek();
          if (! latest.equals("(")) {
            corrupt = true;
            prl("expected: " + latest + ", but found " + chunk + " instead");
            countInvalid(chunk);
          } else {
            stack.pop();
          }
          break;
        }
      case "]": {
          latest = stack.peek();
          if (! latest.equals("[")) {
            corrupt = true;
            prl("expected: " + latest + ", but found " + chunk + " instead");
            countInvalid(chunk);
          } else {
            stack.pop();
          }
          break;
        }
      case "}": {
          latest = stack.peek();
          if (! latest.equals("{")) {
            corrupt = true;
            prl("expected: " + latest + ", but found " + chunk + " instead");
            countInvalid(chunk);
          } else {
            stack.pop();
          }
          break;
        }
      case ">": {
          latest = stack.peek();
          if (! latest.equals("<")) {
            corrupt = true;
            prl("expected: " + latest + ", but found " + chunk + " instead");
            countInvalid(chunk);
          } else {
            stack.pop();
          }
          break;
        }
      }
      if (corrupt) break;
    }
    return corrupt;
  }

  public void printResult() {    
    prl(this + "; Result: " + this.getResult());
  } 
}

