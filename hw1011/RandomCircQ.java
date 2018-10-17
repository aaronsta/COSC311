/*
 * Aaron Stark
 * COSC 311
 * hw1011
 * FALL 2018
 * https://github.com/aaronsta/COSC311/tree/master/hw1011
 * 
 */

import java.util.Random;

public class RandomCircQ {
	public static void main(String[] args) {
	
		Random random  = new Random();	
		random.setSeed(3);	
		int size = 10;
		CircQ cq = new CircQ(size);
		cq.initialize();
		
		for (int z = 0; z < 3; z++){
			System.out.println("round " + z);
			int x = random.nextInt(5) + 1;
			System.out.println("x " + x);
			for (int i = 0; i < x; i++){
				int a = random.nextInt(100); //bound is exclusive not inclusive so need to go to 100 to get to 99
				System.out.println("\t\t a " + a);
				cq.insert(a);
			}
			
			int y = random.nextInt(5) + 1; 
			System.out.println("y " + y);
			for (int j = 0; j < y; j++){
				System.out.println("\t delete");
				cq.delete();
			}
			
		}
		if (cq.isEmpty()) System.out.println("Empty queue");
		else {
			cq.printQueue();
			System.out.println("");
		}
	}

}
