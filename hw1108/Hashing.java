import java.util.Random;

public class Hashing {

	
	static int linearProbeRecurse(int[] arr, int i, int size){
		int wrappedIndex = i % size;
		//System.out.println("wrappedIndex " + wrappedIndex + " " + arr[wrappedIndex]);
		if(arr[wrappedIndex] != 0){
			System.out.println("inside linearprobe val at pos " + wrappedIndex + " is not 0, look elsewhere");
			
			//System.out.println("wrappedIndex " + (wrappedIndex+1));
			return linearProbeRecurse(arr, wrappedIndex+1,size);
		}
		else {
			//System.out.println("shouldn't we be returning " + wrappedIndex);
			return wrappedIndex;
			//return i;
		}
	}

	static boolean searchUnsortedArr(int[] arr, int j){
		for (int i = 0; i < arr.length; i++){
			if (arr[i] == j ) return true;
		}
		return false;
		
		//return false;
	}
	public static void main(String[] args) {
			
		double ratio = 0.0;
		int runSize = 8;
		//create empty hashTable size int[8]
		//circular wrapping
		int tableSize = 8;
		int[] hashTable = new int[tableSize];
		int count = 0;
		Random random  = new Random();	
		random.setSeed(97);	
		
		int[] inputArray = new int[20];//new int[5];
		int[] arrSrc = new int[50];//new int[32];//new int[8];//new int[32];
		//create randomly generated data, put in inputArray[20]

		//populate arrSrc just with some values
//		for (int i = 0; i < 32; i++){
		for (int i = 0; i < 50; i++){
			arrSrc[i] = i+1;
			System.out.println("arrSrc " + arrSrc[i]);
		}

		
		int max = 49;//31;//7;
		//make input array from random non replaceable selection of vals from arr src
		for (int j = 0; j < 20; j++){
			int ix = random.nextInt(max+1);//generate random int in range 0, ... max (inclusive)
			//System.out.println(arrSrc[ix]);
			inputArray[j] = arrSrc[ix];
			arrSrc[ix] = arrSrc[max];          // remove used value from domain
			max--;                           // shrink array
		}
		
		//output initial inputArray
		for (int j = 0; j < 20; j++){
			System.out.println("inputArray " + j + " "  + inputArray[j]);
		}
		
		
		//int runSize = 8;
		//int tableSize = 8;
		//hash first 8 from inputArray
		for (int i = 0; i < runSize; i++){
			int key = inputArray[i];
			
			System.out.println("i " + i);
			//System.out.println("shrink inputArray size to " + inputArray.length);
			int index = key % tableSize;
			//insert key to hashTable, using linear probe to resolve collision
			System.out.println("key " + key + " index " + index);
			
			if(hashTable[index] != 0){
				int collideIndex = linearProbeRecurse(hashTable,index,tableSize);
				hashTable[collideIndex] = key;
				System.out.println("inserting to collideIndex " + collideIndex);
			}
			else {
				hashTable[index] = key;
				System.out.println("not full , inserting to " + index);
			}
		
			count++;
			ratio = (double)count/(double)tableSize;
			System.out.println("count " + count + " count / tableSize " + ratio);
			if (ratio > 0.75){
				break;
			}
			
		}//end first for
		
		//output first hash table
		for (int j = 0; j < runSize; j++){
			System.out.println("at loc " + j  + " " + hashTable[j]);
		}
		
		if (ratio > 0.75){
			System.out.println("about to start rehashing");
			runSize = 16;
			tableSize = 16;
			int[] hashTableNew = new int[tableSize];
			
			//output source array supposedly after first round?
			
			//System.out.println("source array after first");
			//for (int i = 0; i < 32; i++){
			//	arrSrc[i] = random.nextInt(40) + 10; //crap why was I reloading this after first?
				//System.out.println("src " + arrSrc[i]);
			//}
			
			int loadSize = 16;
			max = 16;//should this be 

			for(int i = 0; i < 16; i++){
				System.out.println("input " + i + " "+ inputArray[i]);
			}
			
			//now hash 16
//			runSize = 16;
//			tableSize = 16;

			count = 0;
			for (int i = 0; i < runSize; i++){
				int key = inputArray[i];
				System.out.println("i " + i);
				//System.out.println("shrink inputArray size to " + inputArray.length);
				int index = key % tableSize;
				//insert key to hashTable, using linear probe to resolve collision
				System.out.println("x2 key " + key + " index " + index);
				
				if(hashTableNew[index] != 0){
					//System.out.println("do we get in here");
					int collideIndex = linearProbeRecurse(hashTableNew,index,tableSize);
					hashTableNew[collideIndex] = key;
					System.out.println("inserting to collideIndex " + collideIndex);
				}
				else {
					hashTableNew[index] = key;
					System.out.println("not full , inserting to " + index);
				}
			
				count++;
				ratio = (double)count/(double)tableSize;
				System.out.println("count " + count + " count / tableSize " + ratio);
				if (ratio > 0.75){
					System.out.println("second hashTable > 75% full, stopping");
					break;
				}
			}

		
			//output final hash table
			for (int j = 0; j < runSize; j++){
				System.out.println("at loc " + j  + " " + hashTableNew[j]);
			}
			//hashTableNew
		
		}//end if ratio
		
		if (ratio > 0.75){
			System.out.println("here we would start final hashTable insertion");
			runSize = 20;
			tableSize = 32;
			int[] hashTableNewNew = new int[tableSize];
			
			for(int i = 0; i < 20; i++){ //this goes into array out of bounds if I go over 20
				System.out.println("input " + i + " "+ inputArray[i]);
			}
			
			count = 0;
			for (int i = 0; i < runSize; i++){
				int key = inputArray[i];
				System.out.println("i " + i);
				//System.out.println("shrink inputArray size to " + inputArray.length);
				int index = key % tableSize;
				//insert key to hashTable, using linear probe to resolve collision
				System.out.println("x2 key " + key + " index " + index);
				
				if(hashTableNewNew[index] != 0){
					//System.out.println("do we get in here");
					int collideIndex = linearProbeRecurse(hashTableNewNew,index,tableSize);
					hashTableNewNew[collideIndex] = key;
					System.out.println("inserting to collideIndex " + collideIndex);
				}
				else {
					hashTableNewNew[index] = key;
					System.out.println("not full , inserting to " + index);
				}
			
				count++;
				ratio = (double)count/(double)tableSize;
				System.out.println("count " + count + " count / tableSize " + ratio);
				if (ratio > 0.75){
					System.out.println("third hashTable > 75% full, stopping");
					break;
				}
			}
			
			//output final hash table
			for (int j = 0; j < 32; j++){
				System.out.println("at loc " + j  + " " + hashTableNewNew[j]);
			}

			
		}

	}//end main
	
}//end class
