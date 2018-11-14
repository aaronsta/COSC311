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

	public static int[] createHash(int[] hashTbl, int[] inputArray, int runSize, int tableSize){
		int count = 0;
		double ratio = 0.0;
		for (int i = 0; i < runSize; i++){
			int key = inputArray[i];
			int index = key % tableSize;
			if(hashTbl[index] != 0){
				int collideIndex = linearProbeRecurse(hashTbl,index,tableSize);
				hashTbl[collideIndex] = key;
			}
			else {
				hashTbl[index] = key;
			}
			count++;
			ratio = (double)count/(double)tableSize;
			if (ratio > 0.75){
				break;
			}
			
		}//end first for
		return hashTbl;
	}
	
	public static void main(String[] args) {
		Random random  = new Random();	
		random.setSeed(97);	
		
		int[] inputArray = new int[20];
		int[] arrSrc = new int[50];

		//populate arrSrc with some values
		for (int i = 0; i < 50; i++){
			arrSrc[i] = i+1;
		}
		
		int max = 49;
		//make input array from random nonreplaceable selection of vals from arrSrc  - based on code in asst
		for (int j = 0; j < 20; j++){
			int ix = random.nextInt(max+1);
			inputArray[j] = arrSrc[ix];
			arrSrc[ix] = arrSrc[max];         
			max--;                          
		}
		
		int runSize = 8;
		int tableSize = 8;
		int[] hashTableFirst = new int[tableSize];
		hashTableFirst = createHash(hashTableFirst,inputArray,runSize,tableSize);

		runSize = 16;
		tableSize = 16;
		int[] hashTableSecond = new int[tableSize];
		hashTableSecond = createHash(hashTableSecond,inputArray,runSize,tableSize);

		runSize = 20;
		tableSize = 32;
		int[] hashTableThird = new int[tableSize];
		hashTableThird = createHash(hashTableThird,inputArray,runSize,tableSize);
		System.out.println("index \t data value");
		for (int j = 0; j < 32; j++){
			System.out.println(j  + "\t" + hashTableThird[j]);
		}

	}//end main
	
}//end class
