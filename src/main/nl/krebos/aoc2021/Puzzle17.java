package nl.krebos.aoc2021;

import java.util.ArrayList;
import java.util.List;

/**
 * tx1 = target area x1 (left)
 * tx2 = target area x2 (right)
 * ty1 = target area y1 (up)
 * ty2 = target area y2 (down)
 * maxYpos = highest y point
 *
 * itterate over y values starting from 1 .. to higher
 *   per y value, processY
 *     itterate over x values starting from 1 .. to further
 *       per x,y value, while processX
 *         if (hitTarget(x,y))
 *           add velocity (x,y) to ArrayList
 *           processX = false
 *       y++
 *         
 * 
 * hitTarget, when is target not hit?
 * px=0
 * py=0
 * maxYpos =0
 * while (process {
 *  px = px + x
 *  py = py + y
 *  if (px >= x1 && px <=x2 && py <= y1 && py >=y2) then hit=true, process=false;
 *  else
 *    if (px > x2) || (py < y2) then hit=false, process=false  
 *    if (x > 0) x--
 *    y--
 *    
 * 
 * 
 * - if x of bullet > x2 , it passes on the right side
 * - if y of bullet < y2, it passes on the down side

 * 
 * @author jan
 *
 */
public class Puzzle17 extends Puzzle {
  int tx1;
  int tx2;
  int ty1;
  int ty2;
  int maxYpos;
  List<Velocity> velos = new ArrayList<>();
  
  public Puzzle17(String name, int day, int part, String file) {
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
    int x = 1;
    int y = 1;    
    boolean processY = true;
    boolean processX;
    boolean debug=false;
    while (processY) {
      processX=true;
      x = 1;
      while (processX) {
//        if (x==6) {
//          debug=true;
//        } else {
//          debug=false;
//        }
        Velocity velo = hitTarget(x,y,debug); 
        if (velo.hit) {
          velo.x=x;
          velo.y=y;
          velos.add(velo);
          processX=false;
        } else if (velo.missed) {
          if (velo.passedRight) {
            processX=false;  
          } else {
            x++;
          }          
        }
        prl("" + velo);
      }
      y++;
      if (y > 2000) processY=false;
    }
    prl("nunber: " + velos.size());
    prl("============================");
    for (Velocity velo : velos) {
      if (velo.hit) {
        prl ("" + velo);        
      }

    }
    
    
    return result;
  }

  private Velocity hitTarget(int x, int y, boolean debug) {
    int px = 0;
    int py = 0;
    boolean process = true;
    boolean hit = false;
    int maxY = 0;
    Velocity velo = new Velocity();
    velo.hit = false;
    while (process) {
      px += x;
      py += y;
      if (debug)  prl ("- next point: x,y" + px + "," + py);
      if (y> 0) maxY += y;
      if (px >= tx1 && px <=tx2 && py <= ty1 && py >=ty2) {
        velo = new Velocity();
        velo.hit = true;
        velo.maxY = maxY;
        velo.xHit=px;
        velo.yHit=py;
        process=false;
      } else {
        if ((px > tx2) || (py < ty2)) {
          velo.missed = true;
          if (px > tx2) {
            velo.passedRight = true;
          }
          process=false;  
        }
        if (x > 0) x--;
        y--;        
      }      
    }
    return velo;
  }

  private long part2() {
    long result = 0L;   
    return result;
  }
  
  public void printResult() {    
    prl(this + "; Result: " + this.getResult());
  } 
}
class Velocity {
  int x;
  int y;
  int xHit;
  int yHit;
  int maxY;
  boolean hit;
  boolean missed;
  boolean passedRight;
  public String toString() {
    return "velo: x="+x+";y="+y+";maxY="+maxY+";hit="+hit
        + ";xHit=" + xHit + ";yHit="+yHit
        +";missed="+missed+";passedRight="+passedRight;
  }
}
