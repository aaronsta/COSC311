
public class CircularQueue {
	private int[] q;
	private int head;
	private int tail;
	private int size;
	
	CircularQueue(int sz){
		q = new int[sz];
		size = sz;
	}
	
	public void initialize(){
		head = 0;
		tail = 0;
	}
	
	private boolean isFull(){
		 return (head == (tail + 1) % size);
	}
	
	private boolean isEmpty(){
		return (head == tail);
	}
	
	/*insert x at tail */
	public void insert(int x) {
		  if ( !isFull() ) {
			  q[tail] = x;
			  tail =  ( tail + 1) % size;
		  }
	}
	
	/* delete from head */
	public int delete() {
	  int val = 0;
	  if ( !isEmpty() ){
		  val = q[head];
		  head = ( head + 1) % size;
	  }
	  return val;
	  }
	
	public void printQueue() {
		  if ( !isEmpty() ){
		   for (int i = head; i <= tail-1; i++) {
		      System.out.print(q[i] + " ");
			}	
		}
	}
	public static void main(String[] args) {
	
		
	}

}
