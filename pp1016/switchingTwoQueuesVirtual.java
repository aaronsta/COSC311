/*
 * Aaron Stark
 * COSC 311
 * pp1016
 * FALL 2018
 * https://github.com/aaronsta/COSC311/tree/master/pp1016
 * 
 * 
 */

import java.util.Random;

public class switchingTwoQueuesVirtual {
	private static int getPoissonRandom(double mean) {
	    Random r = new Random();
	    double L = Math.exp(-mean);
	    int k = 0;
	    double p = 1.0;
	    do {
	        p = p * r.nextDouble();
	        k++;
	    } while (p > L);
	    return k - 1;
	}

	public static void main(String[] args)  {
		int numSwitchedServed = 0;
		int numQueueTwoOne = 0;
		boolean verboseOutput = true;
		boolean terseOutput = false;
		double mean = 0.25;//0.25;
		int rounds = 500;//500;
		QueueLinkedList aList = new QueueLinkedList();
		QueueLinkedList aListVirtual = new QueueLinkedList();
		QueueLinkedList bList = new QueueLinkedList();
		Server aServer = new Server();
		Server aVirtualServer = new Server();
		Server bServer = new Server();
		
		for (int i = 0; i < rounds; i++){
			System.out.println("tick # " + i );	
			int numArrive = getPoissonRandom(mean);
			for (int j = 0; j < numArrive; j++){
				Client c = new Client(""+j+i);
				Client d = new Client(""+j+i+"D");
				
				Client cv = new Client(""+j+i + "_V");
				cv.copyClient(c); //copy data from c to a new Client cv
				
				aList.enqueue(c);
				c.setOriginalQueue(1);
				c.setCurrentQueue(1);
				
				//add cv copy to virtual queue from which nothing will leave
				aListVirtual.enqueue(cv);
				cv.setOriginalQueue(1);
				cv.setCurrentQueue(1);
				
				bList.enqueue(d);
				d.setOriginalQueue(2);
				d.setCurrentQueue(2);
			}

			//handle assigning server for Queue 1 
			if (aList.getNumberInList() > 0)	{
				if (aServer.getIsBusy() == false)	{
					aServer.setClientServing(aList.dequeue());
					System.out.println("tick # " + i + " server 1 start service on customer " + aServer.getClientServed().getId() + " server time " + aServer.getClientServed().getServerTime());
				}
				else{
					//System.out.println("server busy, not assigning new client");
				}
			}
			
			//give each newly arrived client 50 50 chance to switch from Queue 1 to Queue 2 
			Random rr = new Random();
			for (int k = 0; k < numArrive; k++){
				int aNum = rr.nextInt(10); //supposedly equal prob 0,1,2,3,4,5,6,7,8,9 
				boolean isLessThanFive = (aNum < 5);
				if((isLessThanFive == true) && (aList.getNumberInList() > 2)){
					Client nc = aList.switchQueue();
					System.out.println("\t switched " + nc.getId() + " and added to queue 2 ");
					nc.setSwitchedQueues(true);
					bList.enqueue(nc);
					nc.setCurrentQueue(2);
					
					//find the corresponding element in Q1 Virtual and set the isSwitched boolean to true
					String virtualId = nc.getId() + "_V";
					aListVirtual.setVirtualIsSwitched(virtualId);
				}
			}

			//handle busy indicators for server 1
			if (aList.getNumberInList() > 0)	{
				if (aServer.isDoneWithClient(i) == true){
					System.out.println("tick # " + i + " server 1 end service on customer " + aServer.getClientServed().getId());
					aServer.setIsBusy(false);
				}
				else{
					aServer.setIsBusy(true);
				}
			}
			
			//handle server assignment for Queue 1 Virtual
			if (aListVirtual.getNumberInList() > 0)	{
				if (aVirtualServer.getIsBusy() == false)	{
					aVirtualServer.setClientServing(aListVirtual.dequeue());
					System.out.println("tick # " + i + " server 1 Virtual start service on customer " + aVirtualServer.getClientServed().getId() + " server time " + aVirtualServer.getClientServed().getServerTime());
					
					if (aVirtualServer.getClientServed().getSwitchedQueues() == true){
						//check if corresponding item D-V in Q2  is at server yet
						String virId = aVirtualServer.getClientServed().getId();
						int suffixInd = virId.indexOf('_');
						String actualId = virId.substring(0,suffixInd);
						System.out.println("Queue 2 is serving " +bServer.getClientServed().getId() );
					
						//if the corresponding ID is still in Q2, then Q1 beat Q2. 
						//set wouldHaveWaitedTime in Q2 to actual wait time of the corresponding virtual
						if((bList.isInQueue(actualId) == true)){
							System.out.println("id " + actualId + " is still in queue 2, so Q1 beat Q2. set wait time from virtual to Q2");
							int virtualWaitTime = aVirtualServer.getClientServed().getWaitTime();
							bList.traverseAndSetWouldHaveWaitedTime(actualId,virtualWaitTime);
						}
					}
					
					
				}
				else{
					//System.out.println("server busy, not assigning new client");
				}
			}
			
			//check virtual server is busy
			if (aListVirtual.getNumberInList() > 0)	{
				if (aVirtualServer.isDoneWithClient(i) == true){
					System.out.println("tick # " + i + " virtual server 1 end service on customer " + aVirtualServer.getClientServed().getId());
					aVirtualServer.setIsBusy(false);
				}
				else{
					aVirtualServer.setIsBusy(true);
				}
			}
			
			//handle server assignment for Queue 2 
			if (bList.getNumberInList() > 0)	{
				if (bServer.getIsBusy() == false)	{
					
					bServer.setClientServing(bList.dequeue());
					System.out.println("tick # " + i + " server 2 start service on customer " + bServer.getClientServed().getId() + " server time " + bServer.getClientServed().getServerTime());
					if (bServer.getClientServed().getSwitchedQueues() == true){
						System.out.println("\t switched client being served " + bServer.getClientServed().toString());
						
						//check if  Q2 beat Q1: look at corresponding virtual and see if it is still in virtual queue
						String actualId = bServer.getClientServed().getId();
						String virtualId = actualId + "_V";
						if((aListVirtual.isInQueue(virtualId) == true)){
							System.out.println("id " + virtualId + " is still in queue 1 virtual, so Q2 beat Q1. set would have waited by adding up prev wts?");
							//add up all wait times ahead of corresponding virtual added to its current wait time 
							int virtualWaitTime = aListVirtual.traverseAndAddUpWaitTimes(virtualId);
							bServer.getClientServed().setWouldHaveWaitedTime(virtualWaitTime);
							System.out.println("would have waited time on " + bServer.getClientServed().getId() + " is " + bServer.getClientServed().getWouldHaveWaitedTime());
						}
						numSwitchedServed++;
					}
				}
				else{
		//			System.out.println("server busy, not assigning new client");
				}
			}

			//manage server 2 busy indicator
			if (bList.getNumberInList() > 0)	{
				if (bServer.isDoneWithClient(i) == true){
					System.out.println("tick # " + i + " server 2 end service on customer " + bServer.getClientServed().getId());
					bServer.setIsBusy(false);
				}
				else{
					bServer.setIsBusy(true);
				}
			}
			
			//update wait time on all queues
			aList.traverseAndIncrWait();
			bList.traverseAndIncrWait();
			aListVirtual.traverseAndIncrWait();
			
			if (verboseOutput == true){
				
				System.out.println("number arrived " + numArrive);
				System.out.println("queue 1: server busy " + aServer.getIsBusy());
				System.out.println("queue 1: " + aList.getNumberInList());
				System.out.println("queue 1 " + aList.toString());
		
				System.out.println("queue 1 virtual: server busy " + aVirtualServer.getIsBusy());
				System.out.println("queue 1 virtual: " + aListVirtual.getNumberInList());
				System.out.println("queue 1 virtual " + aListVirtual.toString());
				
				System.out.println("queue 2: server busy " + bServer.getIsBusy());
				System.out.println("queue 2: " + bList.getNumberInList());
				System.out.println("queue 2 " + bList.toString());
				System.out.println("==========================================");
			}
			
		}
		System.out.println("Total number switched who were served: " + numSwitchedServed);
		
	}

}
