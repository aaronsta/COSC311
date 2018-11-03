import java.util.Random;

public class switchingTwoQueues {
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
		boolean verboseOutput = true;
		boolean terseOutput = false;
		double mean = 0.25;
		int rounds = 500;
		QueueLinkedList aList = new QueueLinkedList();
		QueueLinkedList bList = new QueueLinkedList();
		Server aServer = new Server();
		Server bServer = new Server();
		
		for (int i = 0; i < rounds; i++){
				
			int numArrive = getPoissonRandom(mean);
			for (int j = 0; j < numArrive; j++){
				Client c = new Client(""+j+i);
				Client d = new Client(""+j+i+"D");
				
				aList.enqueue(c);
				c.setOriginalQueue(1);
				c.setCurrentQueue(1);
				bList.enqueue(d);
				d.setOriginalQueue(2);
				d.setCurrentQueue(2);
			}

			//handle switching server for Queue 1 
			if (aList.getNumberInList() > 0)	{
				if (aServer.getIsBusy() == false)	{
					aServer.setClientServing(aList.dequeue());
					System.out.println("tick # " + i + " server 1 start service on customer " + aServer.getClientServed().getId() + " server time " + aServer.getClientServed().getServerTime());
					//when we set a new client to the server for Queue 1 , if the client preceeded another client that switched to Queue 2
					//then set "would have waited time" on the client that switched //KEEP
					if (aServer.getClientServed().getPreceededBySwitchedId() != ""){
						System.out.println("after setting to server, was this preceded by a switch id " + aServer.getClientServed().getPreceededBySwitchedId());
						//add wait time for preceeding one plus its server time to get would have waited time
						double wouldHaveWaitedTime = aServer.getClientServed().getWaitTime() + aServer.getClientServed().getServerTime();
						bList.traverseAndSetWouldHaveWaitedTime(aServer.getClientServed().getPreceededBySwitchedId(),wouldHaveWaitedTime);
					}
				}
				else{
					//System.out.println("server busy, not assigning new client");
				}
			}
			
			
//			System.out.println("aList has " + aList.getNumberInList());
			
			//give each newly arrived client 50 50 chance to switch from Queue 1 to Queue 2 //KEEP
			Random rr = new Random();
			for (int k = 0; k < numArrive; k++){
				int aNum = rr.nextInt(10); //supposedly equal prob 0,1,2,3,4,5,6,7,8,9 //KEEP
				boolean isLessThanFive = (aNum < 5);
			//	System.out.println("isLessThanFive " + isLessThanFive);
				if((isLessThanFive == true) && (aList.getNumberInList() > 2)){
				//	System.out.println("right before remove from end " + aList.toString());
					Client nc = aList.switchQueue();
					//System.out.println("removed " + nc.toString() + " and presumably added to bList ");
					nc.setSwitchedQueues(true);
					bList.enqueue(nc);
					nc.setCurrentQueue(2);
					
				}
			}

			
//			System.out.println("isaServerDone " + isaServerDone);
			if (aList.getNumberInList() > 0)	{
				if (aServer.isDoneWithClient(i) == true){
					System.out.println("tick # " + i + " server 1 end service on customer " + aServer.getClientServed().getId());
					aServer.setIsBusy(false);
				}
				else{
					aServer.setIsBusy(true);
				}
			}
			//handle switching server for Queue 2 //KEEP
			if (bList.getNumberInList() > 0)	{
	//			System.out.println("from queue 22");
				if (bServer.getIsBusy() == false)	{
					
					bServer.setClientServing(bList.dequeue());
					System.out.println("tick # " + i + " server 2 start service on customer " + bServer.getClientServed().getId() + " server time " + bServer.getClientServed().getServerTime());
					if (bServer.getClientServed().getSwitchedQueues() == true){
						System.out.println("\t switched client being served");
						numSwitchedServed++;
					}
				}
				else{
		//			System.out.println("server busy, not assigning new client");
				}
			}
			//System.out.println("isaServerDone " + isaServerDone);
			if (bList.getNumberInList() > 0)	{
				if (bServer.isDoneWithClient(i) == true){
					System.out.println("tick # " + i + " server 2 end service on customer " + bServer.getClientServed().getId());
	
					bServer.setIsBusy(false);
				}
				else{
					bServer.setIsBusy(true);
				}
			}
			
			//update wait time on each //KEEP
			aList.traverseAndIncrWait();
			bList.traverseAndIncrWait();
			
			if (verboseOutput == true){
				System.out.println("tick # " + i );
				System.out.println("number arrived " + numArrive);
				System.out.println("queue 1: server busy " + aServer.getIsBusy());
				System.out.println("queue 1: " + aList.getNumberInList());
				System.out.println("queue 1 " + aList.toString());
				System.out.println("queue 2: server busy " + bServer.getIsBusy());
				System.out.println("queue 2: " + bList.getNumberInList());
				System.out.println("queue 2 " + bList.toString());
				System.out.println("==========================================");
			}
			
		}
		System.out.println("Total number switched who were served: " + numSwitchedServed);
		
	}

}
