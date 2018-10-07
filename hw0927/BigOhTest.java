/*
 * Aaron Stark
 * COSC 311
 * hw0927
 * FALL 2018
 * https://github.com/aaronsta/COSC311/tree/master/hw0927
 * 
 */
import java.util.Random;
public class BigOhTest {
			
	public static void main(String[] args) {
			int k = 30000;
			int n = 75000;
			int LARGEST_VAL_N = 1000000;
			int size = (int) (LARGEST_VAL_N * 1.5);
			CircularQueue circ = new CircularQueue(size);
			circ.initialize();
			for(int i = 0; i < n; i++){
				circ.insert(3);
			}
			Random random = new Random();
			 int[] opsArr = new int[k];
			 for(int i = 0; i < k; i++){
				 opsArr[i] = random.nextInt(2);
			 }
			 
			 long startTime = System.currentTimeMillis();
			 for(int j = 0; j < k; j++){
				 int op = opsArr[j];
				 if (op == 0){
					 circ.insert(2);
				 }
				 else{
					 circ.delete();
					 
				 }
			 }
			 long endTime = System.currentTimeMillis();
			 long elapsed = (endTime - startTime);
			 System.out.println("operatons K " + k + " n elements " + n + " elapsed time " + elapsed);
			
		}

}
