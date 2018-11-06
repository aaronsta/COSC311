/*
 * Aaron Stark
 * COSC 311
 * pp1016
 * FALL 2018
 * https://github.com/aaronsta/COSC311/tree/master/pp1016
 * 
 * 
 */
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
	//for the removal from tail
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

		    node.next = null;
		    tail = node;
		    size--;
		    return stash;
	}

	

	public void traverseAndIncrWait(){
		Link pointer = head;
		while (pointer != null){
			int oldTime = pointer.data.getWaitTime();
			pointer.data.setWaitTime(oldTime + 1);
		/*
			if (pointer.data.getPrecederTimeAfterSwitch() != -1){//make sure it is not the intialized value
				int oldPrecederTime = pointer.data.getPrecederTimeAfterSwitch();
				pointer.data.setPrecederTimeAfterSwitch(oldPrecederTime + 1);
			}
			*/
			pointer = pointer.next;
		}
	}
	
	public void setVirtualIsSwitched(String id){
		Link pointer = head;
		while (pointer != null){
			String retId =  pointer.data.getId() + "";
			if (retId.equals(id)){
				pointer.data.setSwitchedQueues(true);
			}
		
			pointer = pointer.next;
		}
	}
	
	public boolean isInQueue(String id){
		boolean result = false;
		Link pointer = head;
		while (pointer != null){
			if (pointer.data.getId().equals(id)){
				result = true;
			}
		
			pointer = pointer.next;
		}
		return result;
	}
	
	public int traverseAndAddUpWaitTimes(String stopId){
		int totalWaitTime = 0;
		Link pointer = head;
		while (pointer != null){
				//search each node in turn for the id passed in
				System.out.println("traverseAndAddUpWaitTimes " + pointer.data.getId() );
				boolean hasMyId = pointer.data.getId().equals(stopId) ? true : false;
				if (hasMyId == false){
					totalWaitTime += pointer.data.getServerTime();
					System.out.println("added preceding server time " + pointer.data.getServerTime());
				}
				else{
					totalWaitTime += pointer.data.getWaitTime();
					System.out.println("added its own wait time " + pointer.data.getWaitTime());
					break;
				}
				
				pointer = pointer.next;
		}
		System.out.println("total up until is " + totalWaitTime);
		return totalWaitTime;
	}
	
	public void traverseAndSetWouldHaveWaitedTime(String idParam, int wouldHaveWaitedTime){ 
		Link pointer = head;
		while (pointer != null){
				//search each node in turn for the id passed in
				boolean hasMyId = pointer.data.getId().equals(idParam) ? true : false;
				if (hasMyId == true){
					pointer.data.setWouldHaveWaitedTime(wouldHaveWaitedTime);
					System.out.println("after setting wouldHaveWaitedTime to " + wouldHaveWaitedTime + " on " + pointer.data.getId());
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
