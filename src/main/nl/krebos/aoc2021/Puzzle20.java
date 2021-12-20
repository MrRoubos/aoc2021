package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Puzzle20 extends Puzzle {
	char [] enh = new char[512];
	int maxLit = 0;

	public Puzzle20(String name, int day, int part, String file) {
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
		result = maxLit;
		return result;
	}


	private void readFile() {
		String input = "";

		// Read enhancement algorithm string
		try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
			int c=0;	      	     
			for (int i=0; i < 512; i++) {
				c = reader.read();
				enh[i] = (char)c;
			}
			prl ("Enhancement string: " + enh.length);
			for (char c1 : enh) {
				pr("" + c1);
			}
			prl("");

			// skip empty line
			reader.readLine();
			reader.readLine();
			
			// The art is, I think, to read 3 lines and then process them.

			int line=0;
			int maxX=0;
			Queue <String>queue = new LinkedList<>();
			String newLine;
			String emptyLine ="";
			while ((input = reader.readLine()) != null) {
				//newLine = "....." + input + ".....";
				newLine = input;
				if (line == 0) {
					line++;					
					maxX = newLine.length();					 
			    for (int x1=0; x1<maxX;x1++) {
			    	emptyLine += ".";
			    }
			    prl(emptyLine);
				}

				if (queue.size() < 3) {
					queue.add(newLine);
				} 
				if (queue.size() == 3 ){
					enhance(queue, newLine.length());
					queue.remove();
				}
			}
	    prl(emptyLine);
	    prl("");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	private void enhance(Queue<String> queue, int maxX) {
		//prl ("enhance, maxX:" + maxX);
		boolean process = true;
		String[][] map = new String[maxX][3];
		int y = 0;
		for (String str : queue) {
			for (int x=0;x<maxX;x++) {
				map[x][y] = str.substring(x,x+1);	
			}
			y++;
		}
		
    int x = 1;    
		pr(".");    
		while (process) {
			if (x < maxX-1) {
				String bin = map[x-1][0] + map[x][0] + map[x+1][0];
				bin += map[x-1][1] + map[x][1] + map[x+1][1];
				bin += map[x-1][2] + map[x][2] + map[x+1][2];
				String binaryNumber = bin.replace(".", "0").replace("#", "1");
				
	      // Source: https://stackoverflow.com/questions/7437987/how-to-convert-binary-string-value-to-decimal
	      int binNr = Integer.parseInt(binaryNumber, 2);
	      //prl ("bin number = " + binaryNumber  + "; enh[]=" + enh[binNr]);
	      
	      if (enh[binNr] == '#') maxLit++;
	      pr("" + enh[binNr]);
				x++;				
			} else {
				process = false;
			}
		}
		pr("..");
		prl("");

		
	}

	private long part2() {
		long result = 0L;   
		return result;
	}

	public void printResult() {    
		prl(this + "; Result: " + this.getResult());
	} 
}

class Pixel {
	int x;
	int y;
	String value;

	public Pixel (int x, int y, String value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
}