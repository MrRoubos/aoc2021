package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Puzzle16 extends Puzzle {
  Map<String, String> bin = new HashMap<>();
  Map<String, String> versions = new HashMap<>();
  Map<String, String> types = new HashMap<>();
  List<Integer> binaries = new ArrayList<>();
  List<Package> packages = new ArrayList<>();
  int pointer=0;
  int totalpack=0;
  
  public Puzzle16(String name, int day, int part, String file) {
    super(name, day, part, file);
    bin.put("0", "0000");
    bin.put("1", "0001");
    bin.put("2", "0010");
    bin.put("3", "0011");
    bin.put("4", "0100");
    bin.put("5", "0101");
    bin.put("6", "0110");
    bin.put("7", "0111");
    bin.put("8", "1000");
    bin.put("9", "1001");
    bin.put("A", "1010");
    bin.put("B", "1011");
    bin.put("C", "1100");
    bin.put("D", "1101");
    bin.put("E", "1110");
    bin.put("F", "1111");
    versions.put("000", "0");
    versions.put("001", "1");
    versions.put("010", "2");
    versions.put("011", "3");
    versions.put("100", "4");
    versions.put("101", "5");
    versions.put("110", "6");
    versions.put("111", "7");

    
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
    printBinaries();
    result = process();
    return result;
  }

  private void printBinaries() {
    binaries.forEach(it -> pr(""+it));
    prl("");   
  }

  private int process() {
    int totalversion=0;
    while (pointer < binaries.size()) {
      processPackage();
      prl("Packages: " + packages.size());
    }
    return countVersions();    
  }


  private int countVersions() {
    Iterator<Package>it = packages.iterator();
    int total=0;
    while (it.hasNext()) {
      Package pack = it.next();
      total += Integer.parseInt(pack.version);
    }
   return total; 
  }

  private void processPackage() {
    Package pack = new Package();
    packages.add(pack);
    pack.version = getVersion();
    pack.type = getVersion();
    totalpack += Integer.parseInt(pack.version);
    prl("Package: " + pack + "; pointer: " + pointer + "; total versions: " + totalpack);
              
    if (pack.type.equals("4")) {
      prl("literal package, x * 5-bits");
      boolean process=true;
      while (process) {
        String literal="";
        for (int i=0; i<5; i++) {
          literal += binaries.get(pointer);
          pointer++;
        }
        prl("- literal: " + literal);
        if (literal.startsWith("0")) {  
          prl("- literal stop reading");
          process=false;
        } 
      }
      
    } else {
      prl("operator package, general");
      //prl("pointer-1: " + pointer + "; " + binaries.get(pointer));
      if (binaries.get(pointer).equals(1)) {
        prl("- read number of subpackages");
        pointer++;
        String nr="";
        for (int i=0; i<11; i++) {
          if (pointer > binaries.size()-1) break;
          nr += binaries.get(pointer);
          pointer++;
        }
        int subs = Integer.parseInt(nr, 2);
        prl("- nr of subs: " + nr + "; = " + subs);
        processPackage();
        while (pointer < binaries.size() && binaries.get(pointer).equals(0)) {
          pointer++;
        }

      } else if (binaries.get(pointer).equals(0)) {
        prl("- read number of bits");        
        pointer++;
        String nr="";
        for (int i=0; i<15; i++) {
          if (pointer > binaries.size()-1) break;
          nr += binaries.get(pointer);
          pointer++;
        }
        if (nr.length() > 0) {
          int subs = Integer.parseInt(nr, 2);
          prl("- nr of subs: " + nr + "; = " + subs);
          processPackage();          
        }
      }
    }        
  }

  private String getVersion() {
    //prl("pointer-1: " + pointer);
    String version="";
    String temp="";
    for (int i=0; i<3; i++) {
      if (pointer > binaries.size()-1) break;
      version += binaries.get(pointer);
      pointer++;
    }
    //prl("pointer-2: " + pointer);
    temp = versions.get(version);
    if (temp == null) {
      prl("unknown version/type: " + version);
    }
    return temp;
  }

  private long part2() {
    long result = 0L;   
    return result;
  }
  
  private void readFile() {
    String input = "";
        
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      int c=0;
      String bits=null;
      while ((c = reader.read()) != -1) {
        bits = bin.get("" + (char)c);
        prl("" + (char)c + " = " + bits);        
        binaries.add(Integer.parseInt(bits.substring(0,1)));
        binaries.add(Integer.parseInt(bits.substring(1,2)));
        binaries.add(Integer.parseInt(bits.substring(2,3)));
        binaries.add(Integer.parseInt(bits.substring(3,4)));
      }      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void printResult() {    
    prl(this + "; Result: " + this.getResult());
  } 
}
class Package{
 String version;
 String type;
 
 public String toString() {
   return "Version: " + version + "; type: " + type;
 }
}