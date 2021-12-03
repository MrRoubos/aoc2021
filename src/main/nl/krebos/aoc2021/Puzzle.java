package nl.krebos.aoc2021;

public class Puzzle {
  private String name;
  private int day;
  private String part;
  private String inputFile;
  private long result;

  public Puzzle (String name, int day, String part) {
    this.name = name;
    this.day = day;
    this.part = part;
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
  public String getPart() {
    return part;
  }
  public void setPart(String part) {
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
}
