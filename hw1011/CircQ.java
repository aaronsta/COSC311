
public class CircQ {
	private int[] q;
	private int head;
	private int tail;
	private int size;
	
	CircQ(int sz){
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
	
	public boolean isEmpty(){
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
		//  q[head] = null;
		  head = ( head + 1) % size;
	  }
	  return val;
	  }
	
	public void printQueue() {
		if ( !isEmpty() ){
			if (head < tail){
				for (int i = head; i <= tail -1; i++) {
					System.out.print(q[i] + " ");
				}	
			}
			else {
				for (int i = head; i <= ((tail-1) + size); i++) {
					System.out.print(q[i % size] + " ");
				}	  
			}
		 }
	}
	public static void main(String[] args) {

	}

}
