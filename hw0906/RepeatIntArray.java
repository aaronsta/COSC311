/*
 * Aaron Stark
 * COSC 311
 * hw0906 
 * FALL 2018
 * 
 */
public class RepeatIntArray {
	
	private static int [] repeat(int[] a, int factor){
		if (factor <= 0){
			return new int[0];
		}
		int len = a.length;
		int newCount = factor * len;
		int[] retArr = new int[newCount];
		for (int ct = 0; ct < newCount; ct++){
			retArr[ct] = a[ct % len];
		}
		return retArr;
	}
	
	private static void printArray(int[] array) {
	      for (int i = 0; i < array.length; i++)
	         System.out.print(" " + array[i] + " ");
	      System.out.println();
	}
	
	public static void main(String[] args) {
		 int[] a = {1, 2, 3};
		 System.out.println("Array a:");
		 printArray(a);
		 int factor = -2;
		 System.out.println("Factor: " + factor);
		 int[] b = repeat(a, factor);
		 System.out.println("Repeated array b:");
		 printArray(b);
		
	}

}
