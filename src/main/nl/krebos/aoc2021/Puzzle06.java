package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Puzzle06 extends Puzzle {
  List<Fish> list = new ArrayList<>();
  List<Fish> list2 = new ArrayList<>();
  int days = 18;
  long totalCreated=0;
  private boolean part2 = false; 
    
  public Puzzle06(String name, int day, int part, String file) {
    super(name, day, part, file);
  }

  public long part1() {
    // populate initial list with fishes
    readFile();    
    // iterate over the number of days
    for (int i=1; i <= days; i++) {
      Fish[] list3 = list.toArray(new Fish[0]);
      for (Fish fish : list3) {
        processFish(fish);
      }
      printList(i);
    }        
    this.setResult(list.size());
    
    this.printResult();
    return this.getResult();
  }


  private void printList(int i) {
    pr("After " + i + " day(s): ");
    if (part2) {
      prl("" + list.size());
    } else {
      for (int j=0; j < list.size(); j++) {
        pr(list.get(j).timer +  ",");
      }
      prl("");
    }
  }

  private void processFish(Fish fish) {
    int newTimer = fish.timer--;
    if (newTimer == 0) {
      list.add(new Fish());
      fish.timer = 6;
    }    
  }  
      
  private void readFile() {
    String[] initialStr = null;
    
    String input = "";
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      while ((input = reader.readLine()) != null) {
        prl("Initial state: " + input);
        initialStr = input.split(","); 
      }      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
        
    for (int i=0; i < initialStr.length; i++) {
      list.add(new Fish(Integer.parseInt(initialStr[i])));     
    }    
  }

  public void printResult() {    
    prl(this + "; Result: " + this.getResult());
  }   
}

class Fish {
  int name;
  int timer;
  
  public Fish () {
    this.timer = 8;
  }
  public Fish (int timer) {
    this.name = timer;
    this.timer = timer;
  }  
}

