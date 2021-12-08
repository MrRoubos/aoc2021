package nl.krebos.aoc2021;

public class Experiment {
  static int count = 1;
  
  public static void main (String[] args ) {

    /*
     *  x = 5
     *  
     * 1 to 5 costs 10
     * 5 -> 4 = 1
     * 4 -> 3 = 2
     * 3 -> 2 = 3
     * 2 -> 1 = 4
     * total = 4 + 3 + 2 + 1 = 10
     */
    
    System.out.println("totaal = " +calc(4));
    System.out.println("totaal = " +calc(11));
    
    
  }

  private static int calc(int x) {
    if (x == 1) {
      return 1; 
    } else {
      return (x + calc(x - 1));
    }
  }    
}
