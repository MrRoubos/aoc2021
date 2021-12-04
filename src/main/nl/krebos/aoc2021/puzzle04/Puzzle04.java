package nl.krebos.aoc2021.puzzle04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.krebos.aoc2021.Puzzle;

public class Puzzle04 extends Puzzle {
  
  public Puzzle04(String name, int day, String part) {
    super(name, day, part);
  }

  /**
   * Read first line with random (bingo) numbers drawn, seperated by comma's 
   * 
   * @return
   */
  public long part1() {
    String input = "";
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      
      // First read bingo Nrs
      input = reader.readLine();
      String [] bingoNrs = input.split(",");
      System.out.println("Total numbers: " + bingoNrs.length);
      
       
      // Then read read set of Boards
      int boardNr = 0;
      int x = 0;
      int y = 0;
        
      Board board = null;
      List<Board> boards = new ArrayList<>();
      while ((input = reader.readLine()) != null) {
             
        if (input.length() == 0) {
          board = new Board(boardNr);
          boards.add(board);
          boardNr++;
          y = 0;
          System.out.println("Board: " + boardNr);
        } else {          
          String [] rowNumbers = processRow(input);
          x = 0;
          for (String value : rowNumbers) {
            Position pos = new Position(value, false);
            board.allValues.put(pos.value, "");
            board.positions [x][y] = pos;
            x++;
          }
          y++;
        }
        
      }
      System.out.println("Total boards: " + boardNr);
      for (Board b : boards) {
        b.printBoard();
      }
      
      // Then do the bingo GAME
      this.setResult(doBingo(bingoNrs, boards));
      
      
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.printResult();
    return this.getResult();
  }

  public long part2() {
    String input = "";
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      
      // First read bingo Nrs
      input = reader.readLine();
      String [] bingoNrs = input.split(",");     
       
      // Then read read set of Boards
      int boardNr = 0;
      int x = 0;
      int y = 0;
        
      Board board = null;
      List<Board> boards = new ArrayList<>();
      while ((input = reader.readLine()) != null) {
             
        if (input.length() == 0) {
          board = new Board(boardNr);
          boards.add(board);
          boardNr++;
          y = 0;
          System.out.println("Board: " + boardNr);
        } else {          
          String [] rowNumbers = processRow(input);
          x = 0;
          for (String value : rowNumbers) {
            Position pos = new Position(value, false);
            board.allValues.put(pos.value, "");
            board.positions [x][y] = pos;
            x++;
          }
          y++;
        }       
      }
           
      // Then do the bingo GAME
      this.setResult(doBingo2(bingoNrs, boards));     
      
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.printResult();
    return this.getResult();
  }  
  
  private long doBingo(String[] bingoNrs, List<Board> boards) {
    int draw = 0;
    boolean result = false;
    long finalScore = 0L;
    for (String bingoValue : bingoNrs) {
      draw++;
      System.out.println("Draw nr:" + draw + "; bingoValue" + bingoValue);
      for (Board board : boards) {
        result = doDraw(bingoValue, board);
        if (result) {
          System.out.println("Winning board:");
          board.printBoard();
          long sum = board.sumUnmarked();
          finalScore = sum * Long.parseLong(bingoValue);
          break;
        }
      }
      if (result) {
        break;
      }
    }
    return finalScore;
  }

  private long doBingo2(String[] bingoNrs, List<Board> boards) {
    int draw = 0;
    boolean result = false;
    long finalScore = 0L;
    for (String bingoValue : bingoNrs) {
      draw++;
      System.out.println("Draw nr:" + draw + "; bingoValue" + bingoValue);
      for (Board board : boards) {
        result = doDraw2(bingoValue, board);
        if (result) {
          System.out.println("Winning board: " + board.boardNr);
          board.won = true;
          if (boards.size() - countWonBoards(boards) == 0) {
            System.out.println("Last Winning board:" + board.boardNr);
            board.printBoard();
            long sum = board.sumUnmarked();
            finalScore = sum * Long.parseLong(bingoValue);
          }
        }
      }
    }
    return finalScore;
  }  
  private int countWonBoards(List<Board> boards) {
    int total = 0;
     for (Board board : boards) {
       if (board.won) {
         total++;
       }
     }
     return total;    
  }

  private boolean doDraw(String bingoValue, Board board) {
    boolean full = false;
    // first check if the value is on the board
    if (board.allValues.get(bingoValue) != null) {
      for (int y = 0; y < 5 ; y++) {
        for (int x =0; x < 5; x++ ) {
          if (board.positions[x][y].value.equals(bingoValue)) {
            board.positions[x][y].drawn = true;
            break;
          }
        }
      }      
      
      // check for full row
      int x1 = 0;
      for (int y = 0; y < 5 ; y++) {
        x1=0;
        for (int x =0; x < 5; x++ ) {
          if (board.positions[x][y].drawn) {
            x1++;
          }
          
        }
        if (x1 == 5) {
          System.out.println("Full row!");
         full = true;
         break;
        }
      }
      
      if (! full) {
        // check for full column
        int y1 = 0;
        for (int y = 0; y < 5 ; y++) {
          x1=0;
          for (int x =0; x < 5; x++ ) {
            if (board.positions[y][x].drawn) {
              x1++;
            }
            
          }
          if (x1 == 5) {
            System.out.println("Full column!");
           full = true;
           break;
          }
        }               
      }            
    }
    return full;
  }

  private boolean doDraw2(String bingoValue, Board board) {
    boolean full = false;
    if (board.won) {
      return false;
    }
    
    // first check if the value is on the board
    
    
    if (board.allValues.get(bingoValue) != null) {
      for (int y = 0; y < 5 ; y++) {
        for (int x =0; x < 5; x++ ) {
          if (board.positions[x][y].value.equals(bingoValue)) {
            board.positions[x][y].drawn = true;
            break;
          }
        }
      }      
      
      // check for full row
      int x1 = 0;
      for (int y = 0; y < 5 ; y++) {
        x1=0;
        for (int x =0; x < 5; x++ ) {
          if (board.positions[x][y].drawn) {
            x1++;
          }
          
        }
        if (x1 == 5) {
          System.out.println("Full row!");
         full = true;
         break;
        }
      }
      
      if (! full) {
        // check for full column
        int y1 = 0;
        for (int y = 0; y < 5 ; y++) {
          x1=0;
          for (int x =0; x < 5; x++ ) {
            if (board.positions[y][x].drawn) {
              x1++;
            }
            
          }
          if (x1 == 5) {
            System.out.println("Full column!");
           full = true;
           break;
          }
        }               
      }            
    }
    return full;
  }  
  private String[] processRow(String input) {
    StringBuilder sb = new StringBuilder();
    List<String> values = new ArrayList<>();
    for (int i=0; i < input.length(); i++) {            
      //if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {        
      if (input.charAt(i) != ' ') {
        sb.append(input.charAt(i));  
      } else {
        if (sb.length() > 0 ) {
          values.add(sb.toString());
          sb = new StringBuilder();          
        }          
      }                  
    }
    if (sb.length() > 0 ) {
      values.add(sb.toString());
    }    
    System.out.println("values: " + values.size());   
    // source: https://www.javatpoint.com/convert-arraylist-to-string-array-in-java 
    return values.toArray(new String[values.size()]);
  }

  public void printResult() {    
    System.out.println(this + "; Result: " + this.getResult());
  }   
}

class Board {
  int boardNr;
  Map<String,String> allValues = new HashMap<>();
  Position [][] positions = new Position [5][5];
  boolean won;

  public Board(int boardNr) {
    this.boardNr = boardNr;
    
  }
  
  public long sumUnmarked() {
    long sum = 0L;
    for (int y = 0; y < 5 ; y++) {
      for (int x =0; x < 5; x++ ) {
        if (positions[x][y].drawn == false) {
          sum = sum + Long.parseLong(positions[x][y].value); 
        }
      }      
    }
    return sum;
  }

  public void printBoard() {
    System.out.println("BoardNr: " + boardNr);
    for (int y = 0; y < 5 ; y++) {
      for (int x =0; x < 5; x++ ) {
        System.out.print(positions[x][y].value + " ");
      }
      System.out.println("");
    }
  }
}

class Position {
  boolean drawn;
  String value;
 
  public Position(String value, boolean drawn) {
    this.value = value;
    this.drawn = drawn;
  }
}