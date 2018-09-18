/*
 * Aaron Stark
 * https://github.com/aaronsta/COSC311/tree/master/hw0911
 * COSC 311
 * hw0911
 * FALL 2018
 * 
 * deleteGreaterThanOrEqToAverage and deleteLessThanOrEqToAverage are based on 	https://stackoverflow.com/questions/36587186/delete-multiple-nodes-from-linked-list-java
	under "specifically for this case you want to remove a node." in 	answer from Apr 13 '16 at 2:33
* insertLast is  based on code from the GTG textbook, p. 127 
*  displayList is based on code from the Lafore, Data Structures and Algorithms in Java textbook, p. 204
 */

public class LinkedList {
	private static class Link {
		public double data;
		public Link next;
		
		public Link(double input){
			data = input;
		}
		
		public Link(double input, Link n){
			data= input;
			next = n;
		}
	}
	private Link head;
	private Link tail;
	private int size;
	private double average;
	
	public void LinkedList(){
		head = null;
		tail = null;
		size = 0;
	}
	
	public double getAverage(){
		return average;
	}

	public void insertLast(double dData){
		Link newLink = new Link(dData);
		if (size == 0) {	
			head = newLink; 
		}
		else{
			tail.next = newLink; 
		}
		tail = newLink; 
		size++;
	}
	
	
	public void computeAverage(){
		Link current = head;
		double total = 0.0;
		while (current != null){ 
			total += current.data;
			current = current.next;
		}
		average = total / size;
	}

	
	public LinkedList deleteGreaterThanOrEqToAverage(){
		Link prev = null;
		Link current = head;
		while (current != null) {
		    Link following = current.next;
		    if(current.data >= average){
		        if(prev == null){
		            head = following;
		        } else {
		            prev.next = following;
		        }
		    } else {
		        prev = current;
		    }
		    current = current.next;
		}
		return this;
	}
	
	public LinkedList deleteLessThanOrEqToAverage(){
		Link prev = null;
		Link current = head;
		while (current != null) {
		    Link following = current.next;
		    if(current.data <= average){
		        if(prev == null){ 
		            head = following;
		        } else {
		            prev.next = following;
		        }
		    } else {
		        prev = current; 
		    }
		    current = current.next;
		}
		return this;
	}

	public void displayList(){
		Link current = head;
		while (current != null){ 
			System.out.print(current.data + " ");
			current = current.next;
		}
		System.out.println("");
	}
	

	public static void main(String[] args) {
		LinkedList aList = new LinkedList();
		double [] arrDbl = {100.0,  10.0,  15.0,  20.0,  200.0,  30.0,  40.0,  300.0};
		for(int i = 0; i < arrDbl.length; i++){
			aList.insertLast(arrDbl[i]);
		}
		System.out.println("Starting List:");
		aList.displayList();
		
		System.out.print("Average: ");
		aList.computeAverage();
		System.out.println(aList.getAverage());
		
		aList.deleteLessThanOrEqToAverage();
		System.out.println("Ending List:");
		aList.displayList();
		
	}

}
