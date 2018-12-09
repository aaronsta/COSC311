
public class PriorityQueue {

	
	
	public static int isStructureValidRecurse(int [] q, int i){
		if (i == q.length) return -1;
		else{
		//	System.out.println(i + " q[i] " + q[i]);
			if ((q[i] == 0) && (q[i+1] != 0)) {
				System.out.println("invalid structure at " + (i));
				return (i);
			}
			return isStructureValidRecurse(q,i+1);
		}
		//return 0;
	}
	
	
	public static int isOrderValidRecurse(int []q, int i){
		if (i == q.length) return -1;
		else{
		//	System.out.println(i + " q[i] " + q[i]);
			int parentIndex = (int)Math.floor((i-1)/2);
			if (q[parentIndex] > q[i]) {
				System.out.println("order violation parent "  + q[parentIndex] + " here " +q[i] + " i " + i);
				return i;
			}
			else{
				return isOrderValidRecurse(q,i+1);
			}
		}
		
	}
	
	public static void showPQ(int [] q){
		
		for(int i = 0; i < q.length; i++){
			System.out.print(q[i] + " ");
		}
		System.out.println("");
	
	}
	public static void main(String[] args) {
		 int[] pq1 = {1,2,3,4,5};
		 System.out.println("pq1");
		 showPQ(pq1);
		 
		 int validPQ1 = isStructureValidRecurse(pq1,0);
		 int orderValidPQ1 = isOrderValidRecurse(pq1,0);
		 System.out.println("validPQ1 " + validPQ1);
		 System.out.println("orderValidPQ1 " + orderValidPQ1);
		 
		 if (validPQ1 == -1 && orderValidPQ1 == -1)
		 {
			 System.out.println("pq1 structure is valid");
		 }
		 else if (validPQ1 != -1) {
			 System.out.println("pq1 structure invalid at position " + validPQ1);		 
		 }
		 else if (orderValidPQ1 != -1){
			 System.out.println("pq1 order invalid at position " + orderValidPQ1);	
		 }
		 System.out.println("=================");
		 int [] pq2 = {1,1,0,1,1};
		 System.out.println("pq2");
		 showPQ(pq2);
		 
		 int validPQ2 = isStructureValidRecurse(pq2,0);
		 int orderValidPQ2= isOrderValidRecurse(pq2,0);
		 
		 System.out.println("validPQ2 " + validPQ2);
		 System.out.println("orderValidPQ2 " + orderValidPQ2);
		 
		 if (validPQ2 == -1 && orderValidPQ2 == -1)
		 {
			 System.out.println("pq2 structure is valid");
		 }
		 else if (validPQ2 != -1) {
			 System.out.println("pq2 structure invalid at position " + validPQ2);		 
		 }
		 else if (orderValidPQ2 != -1){
			 System.out.println("pq2 order invalid at position " + orderValidPQ2);	
		 }
		 
		 System.out.println("=================");
		 int [] pq3 = {1,5,1,2,5,6};
		 System.out.println("pq3");
		 showPQ(pq3);
		 
		 int validPQ3 = isStructureValidRecurse(pq3,0);
		 System.out.println("validPQ3 " + validPQ3);
			
		 int orderValidPQ3= isOrderValidRecurse(pq3,0);
		 System.out.println("orderValidPQ3 " + orderValidPQ3);
	
		 if (validPQ3 == -1 && orderValidPQ3 == -1)
		 {
			 System.out.println("pq3 structure is valid");
		 }
		 else if (validPQ3 != -1) {
			 System.out.println("pq3 structure invalid at position " + validPQ3);		 
		 }
		 else if (orderValidPQ2 != -1){
			 System.out.println("pq3 order invalid at position " + orderValidPQ3);	
		 }
		 
		 System.out.println("=================");
		 int [] pq4 = {1,2,2,3,2,2,17,4};
		 System.out.println("pq4");
		 showPQ(pq4);
		 
		 int validPQ4 = isStructureValidRecurse(pq4,0);
		 int orderValidPQ4= isOrderValidRecurse(pq4,0);
		 System.out.println("orderValidPQ4 " + orderValidPQ4);
		 System.out.println("validPQ4 " + validPQ4);
		 
		 if (validPQ4 == -1 && orderValidPQ4 == -1)
		 {
			 System.out.println("pq4 structure is valid");
		 }
		 else if (validPQ4 != -1) {
			 System.out.println("pq4 structure invalid at position " + validPQ4);		 
		 }
		 else if (orderValidPQ4 != -1){
			 System.out.println("pq4 order invalid at position " + orderValidPQ4);	
		 }
		 
		 System.out.println("=================");
		 int [] pq5 = {1,2,2,0,2,2,17,4};
		 System.out.println("pq5");
		 showPQ(pq5);
		 
		 int validPQ5 = isStructureValidRecurse(pq5,0);
		 int orderValidPQ5= isOrderValidRecurse(pq5,0);
		 System.out.println("orderValidPQ5 " + orderValidPQ5);
		 System.out.println("validPQ5 " + validPQ5);

		 if (validPQ5 == -1 && orderValidPQ5 == -1)
		 {
			 System.out.println("pq5 structure is valid");
		 }
		 else if (validPQ5 != -1) {
			 System.out.println("pq5 structure invalid at position " + validPQ5);		 
		 }
		 else if (orderValidPQ5 != -1){
			 System.out.println("pq5 order invalid at position " + orderValidPQ5);	
		 }
		 
		 System.out.println("=================");
		 System.out.println("pq6");
		 //			  0  1 2 3 4  5   6  7   8  9  
		 int numArr = 1+ 2+4+8+16+32+64+128+256+512; 
		 int [] pq6 = new int[numArr];
		 int k = 0;
		 for (double i = 0; i < 10; i++){
			 for(double j=0; j < Math.pow(2.0,i); j++){
				 pq6[k] = (int)Math.pow(2.0, i);
				 k++;
				 
			 }
		 }
		// showPQ(pq6);
		 int validPQ6 = isStructureValidRecurse(pq6,0);
		 int orderValidPQ6= isOrderValidRecurse(pq6,0);

		 if (validPQ6 == -1 && orderValidPQ6 == -1)
		 {
			 System.out.println("pq6 structure is valid");
		 }
		 else if (validPQ6 != -1) {
			 System.out.println("pq6 structure invalid at position " + validPQ6);		 
		 }
		 else if (orderValidPQ6 != -1){
			 System.out.println("pq6 order invalid at position " + orderValidPQ6);	
		 }
		 
		 System.out.println("=================");
		 System.out.println("pq7");
		 int [] pq7 = new int[numArr];
		 int k2 = 0;
		 for (double i = 0; i < 10; i++){
			 for(double j=0; j < Math.pow(2.0,i); j++){
				 pq7[k2] = (int)Math.pow(2.0, i);
				 k2++;
				 
			 }
		 }
		 pq7[500] = 1;
		 
		// showPQ(pq7);
		 int validPQ7 = isStructureValidRecurse(pq7,0);
		 int orderValidPQ7= isOrderValidRecurse(pq7,0);
		 if (validPQ7 == -1 && orderValidPQ7 == -1)
		 {
			 System.out.println("pq7 structure is valid");
		 }
		 else if (validPQ7 != -1) {
			 System.out.println("pq7 structure invalid at position " + validPQ7);		 
		 }
		 else if (orderValidPQ7 != -1){
			 System.out.println("pq7 order invalid at position " + orderValidPQ7);	
		 }
		 System.out.println("=================");
		 int [] pq8 = {0,2,2,0,2,2,17,4};
		 System.out.println("pq8");
		 showPQ(pq8);
		 
		 int validPQ8 = isStructureValidRecurse(pq8,0);
		 int orderValidPQ8= isOrderValidRecurse(pq8,0);
	
		 if (validPQ8 == -1 && orderValidPQ8 == -1)
		 {
			 System.out.println("pq8 structure is valid");
		 }
		 else if (validPQ8 != -1) {
			 System.out.println("pq8 structure invalid at position " + validPQ8);		 
		 }
		 else if (orderValidPQ8 != -1){
			 System.out.println("pq8 order invalid at position " + orderValidPQ8);	
		 }
		 
	}

}
