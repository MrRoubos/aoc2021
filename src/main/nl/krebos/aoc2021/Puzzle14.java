package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Puzzle14 extends Puzzle {
  int steps;
  String poltempl =null;
  Map<String, String> piRules = new HashMap<>();
  Set<String> quantity = new HashSet<>();
  Map<String, Integer> totals = new HashMap<>();
  Stack<String> list = new Stack<>();
  Stack<String> list2 = new Stack<>();
  String[] list3=null;
  
  public static void main (String [] args) {
    Puzzle14 pzl = new Puzzle14("bla", 14, 2, "data/puzzle14a2.txt");
    pzl.steps=20;
    pzl.execute();
    
  }
  
  public Puzzle14(String name, int day, int part, String file) {
    super(name, day, part, file);    
  }

  public long execute() {    
    long result = 0L;
    Stopwatch stopwatch = new Stopwatch();
    
    if (this.getPart() == 1) {
      result = part1();
      stopwatch.split("end of part1");
    } else {
      result = part2();
      stopwatch.split("end of part2");
    }
    
    this.setResult(result);
    printResult(); 
    return result;
  }

  private long part1() {
    long result = 0L;   
    readFile();
    for (int i=0; i<steps; i++) {
      String stepResult = processStep();
      poltempl = stepResult;
      // prl("After step : " + (i + 1) + ": " +  stepResult);
      
      List<Integer> totalList = new ArrayList<>();
      for (String key : totals.keySet()) {
        prl (key + " = " + totals.get(key));
        totalList.add(totals.get(key));
      }
      Collections.sort(totalList);
      result = totalList.get(totalList.size()-1) - totalList.get(0);
      
    }
    prl("unique values: " + quantity.size());
    countUniques();
    List<Integer> totalList = new ArrayList<>();
    for (String key : totals.keySet()) {
      prl (key + " = " + totals.get(key));
      totalList.add(totals.get(key));
    }
    Collections.sort(totalList);
    result = totalList.get(totalList.size()-1) - totalList.get(0);
    return result;
  }

  private void countUniques() {
    Iterator<String> it = quantity.iterator();
    String check=null;
    int count=0;
    StringBuilder sb = new StringBuilder();
    sb.append(poltempl);
    while (it.hasNext()) {
      count=0;
      check = it.next();
      prl ("count number of :" + check);
      for (int i=0; i<poltempl.length();i++) {
        if (poltempl.substring(i,i+1).equals(check)) {
          count++;
        }
      }
      totals.put(check, count);
    }    
  }

  private String processStep() {
    String check=null;
    String element=null;
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<poltempl.length()-1;i++) {
      check = poltempl.substring(i,i+2);
      //prl("Check: " +check);
      element = piRules.get(check);
      if (element != null) {
        sb.append(poltempl.substring(i,i+1));
        sb.append(element);
        quantity.add(poltempl.substring(i,i+1));
        quantity.add(element);
      }
    }    
    sb.append(poltempl.substring(poltempl.length()-1, poltempl.length()));
    return sb.toString();
  }

  private long part2() {
    long result = 0L;   
    readFile();
    
    // perhaps via ArrayList or Stack ? instead of a String?
    for(int i=0; i<poltempl.length()-1;i++) {
      list.add(poltempl.substring(i,i+2));
    }
    //list2 = new Stack<>();
    list3 = list.toArray(new String[0]);
    prl("list3: " + list3.length);
    for (int i=0; i<steps; i++) {
      processStep2();
//      pr(list2.get(0).substring(0,1));
//      for (String key : list2) {
//        pr(key.substring(1,2));
//      }
//      prl("");
      list3 = list2.toArray(new String[0]);
      
      // prl("After step : " + (i + 1) + ": " +  stepResult);
      //countUniques();
    }          
    prl("unique values: " + quantity.size());
    countUniques2();
    List<Integer> totalList = new ArrayList<>();
    for (String key : totals.keySet()) {
      prl (key + " = " + totals.get(key));
      totalList.add(totals.get(key));
    }
    Collections.sort(totalList);
    result = totalList.get(totalList.size()-1) - totalList.get(0);
    return result;
  }

  private void processStep2() {
    String element=null;            
    for(String check : list3) {
      //prl("Check: " +check);
      element = piRules.get(check);
      if (element != null) {
        list2.push(check.substring(0,1) + element);
        list2.push(element + check.substring(1,2));
        
        quantity.add(check.substring(0,1));
        quantity.add(element);        
      }
    }
  }

  private void countUniques2() {
    Iterator<String> it = quantity.iterator();
    String check=null;
    int count=0;
        
    while (it.hasNext()) {
      count=0;
      check = it.next();
      for (String key : list3) {
        if (key.substring(1,2).equals(check)) {
          count++;          
        }
      }
      totals.put(check, count);
    }
    check = null;
  }

  
  private void readFile() {
    String input = "";
        
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      poltempl = reader.readLine();
      prl("Template: " + poltempl);
      
      // skip empty line
      input = reader.readLine();
      
      // read pair insertions rules
      while ((input = reader.readLine()) != null) {
        String [] parts = input.split(" -> ");
        piRules.put(parts[0], parts[1]);
      }      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    prl("Number of piRules: " + piRules.size());
  }
  
  public void printResult() {    
    prl(this + "; Result: " + this.getResult());
  } 
}
