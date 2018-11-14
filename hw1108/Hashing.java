/*
 * Aaron Stark
 * hw1108
 * https://github.com/aaronsta/COSC311/tree/master/hw1108
 * COSC 311, FALL 2018
 
  */

import java.util.Random;

public class Hashing {

	
	static int linearProbeRecurse(int[] arr, int i, int size){
		int wrappedIndex = i % size;
		if(arr[wrappedIndex] != 0){
			return linearProbeRecurse(arr, wrappedIndex+1,size);
		}
		else {
			return wrappedIndex;
		}
	}

	public static void main(String[] args) {
			
		double ratio = 0.0;
		int runSize = 8;
		int tableSize = 8;
		int[] hashTableFirst = new int[tableSize];
		int count = 0;
		Random random  = new Random();	
		random.setSeed(97);	
		
		int[] inputArray = new int[20];
		int[] arrSrc = new int[50];

		//populate arrSrc  with some values
		for (int i = 0; i < 50; i++){
			arrSrc[i] = i+1;
		}

		
		int max = 49;
		//make input array from random non replaceable selection of vals from arr src  - based on code in asst
		for (int j = 0; j < 20; j++){
			int ix = random.nextInt(max+1);
			inputArray[j] = arrSrc[ix];
			arrSrc[ix] = arrSrc[max];         
			max--;                          
		}
		
/*
		for (int j = 0; j < 20; j++){
			System.out.println("inputArray " + j + " "  + inputArray[j]);
		}
*/		

		//hash first 8 from inputArray
		for (int i = 0; i < runSize; i++){
			int key = inputArray[i];
			int index = key % tableSize;
			if(hashTableFirst[index] != 0){
				int collideIndex = linearProbeRecurse(hashTableFirst,index,tableSize);
				hashTableFirst[collideIndex] = key;
			}
			else {
				hashTableFirst[index] = key;
			}
			count++;
			ratio = (double)count/(double)tableSize;
			if (ratio > 0.75){
				break;
			}
			
		}//end first for
		
		//output first hash table
/*
		for (int j = 0; j < runSize; j++){
			System.out.println("at loc " + j  + " " + hashTableFirst[j]);
		}
*/
		if (ratio > 0.75){
			runSize = 16;
			tableSize = 16;
			int[] hashTableSecond = new int[tableSize];
			int loadSize = 16;

			//now hash 16
			count = 0;
			for (int i = 0; i < runSize; i++){
				int key = inputArray[i];
				int index = key % tableSize;
				
				if(hashTableSecond[index] != 0){
					int collideIndex = linearProbeRecurse(hashTableSecond,index,tableSize);
					hashTableSecond[collideIndex] = key;
				}
				else {
					hashTableSecond[index] = key;
				}
			
				count++;
				ratio = (double)count/(double)tableSize;
				if (ratio > 0.75){
					break;
				}
			}

		
			//output second hash table
/*
			for (int j = 0; j < runSize; j++){
				System.out.println("at loc " + j  + " " + hashTableSecond[j]);
			}
	*/		
		}//end if ratio
		
		if (ratio > 0.75){
			runSize = 20;
			tableSize = 32;
			int[] hashTableThird = new int[tableSize];
			
			count = 0;
			for (int i = 0; i < runSize; i++){
				int key = inputArray[i];
				int index = key % tableSize;
				
				if(hashTableThird[index] != 0){
					int collideIndex = linearProbeRecurse(hashTableThird,index,tableSize);
					hashTableThird[collideIndex] = key;
				}
				else {
					hashTableThird[index] = key;
				}
			
				count++;
				ratio = (double)count/(double)tableSize;
				if (ratio > 0.75){
					break;
				}
			}
			
			//output final hash table
			System.out.println("index \t data value");
			for (int j = 0; j < 32; j++){
				System.out.println(j  + "\t" + hashTableThird[j]);
			}
		}

	}//end main
	
}//end class
