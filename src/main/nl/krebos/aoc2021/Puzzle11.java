package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Puzzle11 extends Puzzle {
  int maxX;
  int maxY;
  int steps;
  Octopus [][] oct;
      
  public Puzzle11(String name, int day, int part, String file) {
    super(name, day, part, file);    
  }

  public long execute() {    
    long result = 0L;
    Stopwatch stopwatch = new Stopwatch();
    
    oct = new Octopus[maxX][maxY];

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
    prl("Before any step: ");
    printMap();
    // execute steps
    for (int i=0; i<steps; i++) {
      Stack<Octopus >flashes = new Stack<>();
      flashes = increaseEnergy();

      if (flashes.size() > 0) {
        flash(flashes);        
      }
      prl("After Step: " + (i+1));
      printMap();
      resetFlashed();
    }      
    result = countHighLighted();
    return result;
  }

  private boolean checkAllflashed() {
    boolean result = true;
    long total=0L;
    for (int y=0; y<maxY; y++) {
      for (int x=0; x<maxX; x++) {
        if (oct[x][y].energy==0) {
          total++;
        } else {
          // we can already quit if there is one octopus NOT flashing
          result = false;
          break;
        }
      }
      if (! result) break;
    }
    if (total == (maxX * maxY)) {
     result = true; 
    }
    return result;
  }

  private void flash(Stack<Octopus> flashes) {
    Octopus oc;    

    while (! flashes.empty()) {
      //prl("Flashes to handle: " + flashes.size());
      oc = flashes.pop();
      if (! oc.flashedInStep) {        
        prl("Flash: " + oc);
        oc.energy = 0;
        oc.flashes++;
        oc.flashedInStep = true;
        flashes.addAll(increaseEnergyAdjacent(oc));        
      }

    }    
  }

  private Stack<Octopus> increaseEnergyAdjacent(Octopus oc) {
    Stack<Octopus >flash = new Stack<>();
    Octopus adj = null;
    int x = oc.x;
    int y = oc.y;
    // Check left
    if (x > 0) {
      adj = oct[x-1][y];
      if (! adj.flashedInStep) {
        adj.energy++;
        if (adj.energy > 9) {
          prl("Add adjacent flash for: " + adj);
          flash.add(oct[x-1][y]);
        }        
      } 
    }
    // Check right
    if (x < maxX-1) {
      adj = oct[x+1][y];
      if (! adj.flashedInStep) {
        adj.energy++;
        if (adj.energy > 9) {
          prl("Add adjacent flash for: " + adj);
          flash.add(oct[x+1][y]);
        }        
      } 
    }
    // Check up
    if (y > 0) {
      adj = oct[x][y-1];
      if (! adj.flashedInStep) {
        adj.energy++;
        if (adj.energy > 9) {
          prl("Add adjacent flash for: " + adj);
          flash.add(oct[x][y-1]);
        }        
      } 
    }
    // Check down
    if (y < maxY-1) {
      adj = oct[x][y+1];
      if (! adj.flashedInStep) {
        adj.energy++;
        if (adj.energy > 9) {
          prl("Add adjacent flash for: " + adj);
          flash.add(oct[x][y+1]);
        }        
      } 
    }    
    
    // left/up
    if (x > 0 && y > 0) {
      adj = oct[x-1][y-1];
      if (! adj.flashedInStep) {
        adj.energy++;
        if (adj.energy > 9) {
          prl("Add adjacent flash for: " + adj);
          flash.add(oct[x-1][y-1]);
        }        
      } 
    }
    
    // right/up
    if (x < maxX-1 && y > 0) {
      adj = oct[x+1][y-1];
      if (! adj.flashedInStep) {
        adj.energy++;
        if (adj.energy > 9) {
          prl("Add adjacent flash for: " + adj);
          flash.add(oct[x+1][y-1]);
        }        
      } 
    }    
    
    // right/down
    if (x < maxX-1 && y < maxY-1) {
      adj = oct[x+1][y+1];
      if (! adj.flashedInStep) {
        adj.energy++;
        if (adj.energy > 9) {
          prl("Add adjacent flash for: " + adj);
          flash.add(oct[x+1][y+1]);
        }        
      } 
    }    

    // left/down
    if (x > 0 && y < maxY-1) {
      adj = oct[x-1][y+1];
      if (! adj.flashedInStep) {
        adj.energy++;
        if (adj.energy > 9) {
          prl("Add adjacent flash for: " + adj);
          flash.add(oct[x-1][y+1]);
        }        
      } 
    }    
    return flash;
  }

  private void printMap() {
    for (int y=0; y<maxY; y++) {
      for (int x=0; x<maxX; x++) {
        pr("" + oct[x][y].energy + " ");;
      }
      prl("");
    } 
  }
  
  private long countHighLighted() {
    long total=0L;
    for (int y=0; y<maxY; y++) {
      for (int x=0; x<maxX; x++) {
        total += oct[x][y].flashes;
      }
    }
    return total;
  }
  
  private long resetFlashed() {
    long total=0L;
    for (int y=0; y<maxY; y++) {
      for (int x=0; x<maxX; x++) {
        oct[x][y].flashedInStep = false;
      }
    }
    return total;
  }  
  
  private Stack<Octopus> increaseEnergy() {
    prl("increase for every Octopus the energy with +1");
    Stack<Octopus >flash = new Stack<>();
    for (int y=0; y<maxY; y++) {
      for (int x=0; x<maxX; x++) {
        oct[x][y].energy++;
        if (oct[x][y].energy > 9) {
         flash.add(oct[x][y]); 
        }
      }
    }
    prl("initial flashes: " + flash.size());
    return flash;
  }

  private void readFile() {
    String input = "";
    int y = 0;
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      while ((input = reader.readLine()) != null) {
        for (int x =0; x < input.length(); x++) {
          oct[x][y] = new Octopus(x,y,Integer.parseInt(input.substring(x, x+1)));
        }
        y++;
      }      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }

  private long part2() {
    long result = 0L;
    readFile();
    prl("Before any step: ");
    printMap();
    // execute steps
    for (int i=0; i<steps; i++) {
      Stack<Octopus >flashes = new Stack<>();
      flashes = increaseEnergy();

      if (flashes.size() > 0) {
        flash(flashes);        
      }
      prl("After Step: " + (i+1));
      printMap();
      if (checkAllflashed()) {
        result = i+1;
        prl("All flashed, Step : " + result);
        break;
      }
      resetFlashed();
    }      
    return result;
  }
  
  public void printResult() {    
    prl(this + "; Result: " + this.getResult());
  } 
}
class Octopus {
  int x;
  int y;
  int energy;
  int flashes;
  boolean flashedInStep = false;
  
  public Octopus(int x, int y, int e) {
    this.x = x;
    this.y = y;
    this.energy = e;
  }
  public String toString() {
    return "Octopus: x,y: " + x + ";" + y 
        + "; energy: " +  energy + "; flashes: " + flashes + "; fis: " + flashedInStep;        
  }
}
