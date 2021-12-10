package nl.krebos.aoc2021;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Experiment {
  static int count = 1;
  
  public static void main (String[] args ) {
    List<String> myList = new LinkedList<String>();

    myList.add("A");
    myList.add("B");
    myList.add("C");

    ListIterator<String> it = myList.listIterator();

    if (it.hasNext()) {
        String s1 = it.next();
        System.out.println(s1);
        myList.add("D");
        s1 = it.next();
        System.out.println(s1);        
    }
    
    
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
