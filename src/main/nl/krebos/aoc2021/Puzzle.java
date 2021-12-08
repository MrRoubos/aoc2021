package nl.krebos.aoc2021;

public class Puzzle {
  private String name;
  private int day;
  private int part;
  private String inputFile;
  private long result;

  public Puzzle (String name, int day, int part, String file) {
    this.name = name;
    this.day = day;
    this.part = part;
    this.inputFile = file;
  }
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getDay() {
    return day;
  }
  public void setDay(int day) {
    this.day = day;
  }
  public int getPart() {
    return part;
  }
  public void setPart(int part) {
    this.part = part;
  }
  public String getInputFile() {
    return inputFile;
  }
  public void setInputFile(String inputFile) {
    this.inputFile = inputFile;
  }  
  public long getResult() {
    return result;
  }
  public void setResult(long result) {
    this.result = result;
  }

  public String toString() {
    return "Day: " + day + "; Puzzle: " + name + "; Part: " + part;
  }

  public static void pr(String out) {
    System.out.print(out);
  }
  public static void prl(String out) {
    System.out.println(out);
  }
}
