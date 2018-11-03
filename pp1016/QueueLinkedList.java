
public class QueueLinkedList {
	private static class Link {
		//public double data;
		public Client data;
		public Link next;
		
		public Link(Client c){
			data = c;
		}
		
		public Link (Client c, Link n){
			data = c;
			next = n;
		}
		
		
	}
	private Link head;
	private Link tail;
	private int size;
	private double average;
	
	public void SinglyLinkedList(){
		head = null;
		tail = null;
		size = 0;
	}
	
	public void enqueue(Client c){
		Link newLink = new Link(c);
		if (size == 0) {	
			head = newLink; 
		}
		else{
			tail.next = newLink; 
		}
		tail = newLink; 
		size++;
	}

	public Client dequeue(){
		if (head == null) {
			return null;
		}
		Client headData = head.data;
		head = head.next;
		size--;
		return headData;
		
	}
	public int getNumberInList(){
		return size;
	}
	
	//borrows heavily from https://stackoverflow.com/questions/36396132/how-does-remove-tail-from-singly-linked-list-in-java-work
	//for the backwards queue traversal
	public Client switchQueue(){
		 
		 if (head == null || head.next == null){
			 return null;
		 }
		    Link node = head;
		    Link oldNode = null;
		    while(node.next.next != null) {
		        node = node.next;
		        oldNode = node; //penultimate node
		    }
		    Client penult = oldNode.data;
		    Client stash = node.next.data; //Client that is switching
		    //set a reference on the penultimate Client to the Client that is switching
		    penult.setPreceededBySwitchedId(stash.getId());
		    node.next = null;
		    tail = node;
		    size--;
		    return stash;
	}

	public void checkWinnerWouldHaveWaitedTime(){
		//look on switchee to see what is ref - or just pass that in
		
		//back traverse queue 1 from preceder. add up waitTime for each
		
		// in addition to adding REMAINING waitTime of one at server (not sure how to do that bc we do not persist that)
		//I guess we could save that on server
		
	}
	
	public void traverseAndIncrWait(){
		Link pointer = head;
		while (pointer != null){
			double oldTime = pointer.data.getWaitTime();
			pointer.data.setWaitTime(oldTime + 1.0);
			pointer = pointer.next;
		}
	}
	
	//called when the client that previously preceeded the switched client gets assigned to server
	public void traverseAndSetWouldHaveWaitedTime(String idParam, double preceededWaitTime){
		Link pointer = head;
		while (pointer != null){
				//search each node in turn for the id passed in
				boolean hasMyId = pointer.data.getId() == idParam ? true : false;
				if (hasMyId == true){
					pointer.data.setWouldHaveWaitedTime(preceededWaitTime);
//					System.out.println("after setting preceededWaitTime to " + preceededWaitTime + " on " + pointer.data.getId());
					return;
				}
			pointer = pointer.next;
		}
		
	}
	public String toString() {
        String str = "";
        for (Link p = head; p != null; p = p.next) {
        	 str += p.data + ", ";
        }
        return str;
    }
	
	
}
