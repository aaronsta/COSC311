/*
 * Aaron Stark
 * hw1129
 * https://github.com/aaronsta/COSC311/tree/master/hw1129
 * COSC 311, FALL 2018
 
  */

public class PriorityQueue {
	public static int isStructureValidRecurse(int [] q, int i){
		if (i == q.length) return -1;
		else{
			if ((q[i] == 0) && (q[i+1] != 0)) {
			//System.out.println("invalid structure at " + (i));
				return (i);
			}
			return isStructureValidRecurse(q,i+1);
		}
	}
	
	
	public static int isOrderValidRecurse(int []q, int i){
		if (i == q.length) return -1;
		else{
		//	System.out.println(i + " q[i] " + q[i]);
			int parentIndex = (int)Math.floor((i-1)/2);
			if (q[parentIndex] > q[i]) {
			//	System.out.println("order violation parent "  + q[parentIndex] + " here " +q[i] + " i " + i);
				return i;
			}
			else{
				return isOrderValidRecurse(q,i+1);
			}
		}
		
	}
	
	public static void main(String[] args) {
		 int[] pq1 = {1,2,3,4,5};
		 int validPQ1 = isStructureValidRecurse(pq1,0);
		 int orderValidPQ1 = isOrderValidRecurse(pq1,0);
		 
		 if (validPQ1 == -1 && orderValidPQ1 == -1)
		 {
			 System.out.println("pq1 is valid");
		 }
		 else if (validPQ1 != -1) {
			 System.out.println("pq1 structure invalid at position " + validPQ1);		 
		 }
		 else if (orderValidPQ1 != -1){
			 System.out.println("pq1 order invalid at position " + orderValidPQ1);	
		 }

		 int [] pq2 = {1,1,0,1,1};
		 int validPQ2 = isStructureValidRecurse(pq2,0);
		 int orderValidPQ2= isOrderValidRecurse(pq2,0);
		 
		 if (validPQ2 == -1 && orderValidPQ2 == -1)
		 {
			 System.out.println("pq2 is valid");
		 }
		 else if (validPQ2 != -1) {
			 System.out.println("pq2 structure invalid at position " + validPQ2);		 
		 }
		 else if (orderValidPQ2 != -1){
			 System.out.println("pq2 order invalid at position " + orderValidPQ2);	
		 }
		 
		 int [] pq3 = {1,5,1,2,5,6};
		 int validPQ3 = isStructureValidRecurse(pq3,0);
		 int orderValidPQ3= isOrderValidRecurse(pq3,0);
	
		 if (validPQ3 == -1 && orderValidPQ3 == -1)
		 {
			 System.out.println("pq3 is valid");
		 }
		 else if (validPQ3 != -1) {
			 System.out.println("pq3 structure invalid at position " + validPQ3);		 
		 }
		 else if (orderValidPQ2 != -1){
			 System.out.println("pq3 order invalid at position " + orderValidPQ3);	
		 }
		 
		 int [] pq4 = {1,2,2,3,2,2,17,4};
		 int validPQ4 = isStructureValidRecurse(pq4,0);
		 int orderValidPQ4= isOrderValidRecurse(pq4,0);
		 
		 if (validPQ4 == -1 && orderValidPQ4 == -1)
		 {
			 System.out.println("pq4 is valid");
		 }
		 else if (validPQ4 != -1) {
			 System.out.println("pq4 structure invalid at position " + validPQ4);		 
		 }
		 else if (orderValidPQ4 != -1){
			 System.out.println("pq4 order invalid at position " + orderValidPQ4);	
		 }
		 
		 int [] pq5 = {1,2,2,0,2,2,17,4};
		 int validPQ5 = isStructureValidRecurse(pq5,0);
		 int orderValidPQ5= isOrderValidRecurse(pq5,0);

		 if (validPQ5 == -1 && orderValidPQ5 == -1)
		 {
			 System.out.println("pq5 is valid");
		 }
		 else if (validPQ5 != -1) {
			 System.out.println("pq5 structure invalid at position " + validPQ5);		 
		 }
		 else if (orderValidPQ5 != -1){
			 System.out.println("pq5 order invalid at position " + orderValidPQ5);	
		 }
		 
		 int numArr = 1023; 
		 int [] pq6 = new int[numArr];
		 int k = 0;
		 for (double i = 0; i < 10; i++){
			 for(double j=0; j < Math.pow(2.0,i); j++){
				 pq6[k] = (int)Math.pow(2.0, i);
				 k++;
			 }
		 }
		 int validPQ6 = isStructureValidRecurse(pq6,0);
		 int orderValidPQ6= isOrderValidRecurse(pq6,0);

		 if (validPQ6 == -1 && orderValidPQ6 == -1)
		 {
			 System.out.println("pq6 is valid");
		 }
		 else if (validPQ6 != -1) {
			 System.out.println("pq6 structure invalid at position " + validPQ6);		 
		 }
		 else if (orderValidPQ6 != -1){
			 System.out.println("pq6 order invalid at position " + orderValidPQ6);	
		 }
		 
		 int [] pq7 = new int[numArr];
		 int k2 = 0;
		 for (double i = 0; i < 10; i++){
			 for(double j=0; j < Math.pow(2.0,i); j++){
				 pq7[k2] = (int)Math.pow(2.0, i);
				 k2++;
			 }
		 }
		 pq7[500] = 1;
		 
		 int validPQ7 = isStructureValidRecurse(pq7,0);
		 int orderValidPQ7= isOrderValidRecurse(pq7,0);
		 if (validPQ7 == -1 && orderValidPQ7 == -1)
		 {
			 System.out.println("pq7 is valid");
		 }
		 else if (validPQ7 != -1) {
			 System.out.println("pq7 structure invalid at position " + validPQ7);		 
		 }
		 else if (orderValidPQ7 != -1){
			 System.out.println("pq7 order invalid at position " + orderValidPQ7);	
		 }
	
	}

}
