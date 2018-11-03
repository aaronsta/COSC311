import java.util.Random;

public class Client {
	private String id;
	private double waitTime;
	private int serverTime;
	private double wouldHaveWaitedTime;
	private boolean switchedQueues;
	private String preceededBySwitchedId;
	private int originalQueue;
	private int currentQueue;
	
	private static Random random = new Random();
	
	Client(String ID){
		id = ID;
		waitTime = 0;
		serverTime = random.nextInt(1 + 5);
		wouldHaveWaitedTime = 0.0;
		switchedQueues = false;
		preceededBySwitchedId = "";
	}
	
	public String getId(){
		return id;
	}
	
	public void setOriginalQueue(int oQ){
		originalQueue = oQ;
	}
	
	public void setCurrentQueue(int cQ){
		currentQueue = cQ;
	}
	
	public void setPreceededBySwitchedId(String sId){
		preceededBySwitchedId = sId;
	}
	
	public String getPreceededBySwitchedId(){
		return preceededBySwitchedId;
	}
	
	public boolean getSwitchedQueues(){
		return switchedQueues;
	}
	
	public void setSwitchedQueues(boolean b){
		switchedQueues = b;
	}
	public double getWaitTime(){
		return waitTime;
	}
	
	public void setWaitTime(double wt){
		waitTime = wt;
	}

	public void setWouldHaveWaitedTime(double t){
		wouldHaveWaitedTime = t;
	}
	
	public double getWouldHaveWaitedTime(){
		return wouldHaveWaitedTime;
	}
	
	public int getServerTime(){
		return serverTime;
	}
	
	public String toString(){
		boolean terseOutput = true;
		String outputStr = "\n id " + this.id + " current wait time " + this.waitTime + " original queue " + this.originalQueue + "  current queue " + this.currentQueue;
		if (terseOutput == true) outputStr += " wouldHaveWaitedTime " + wouldHaveWaitedTime;
		return outputStr;
		/*+ " serverTime " + serverTime  + " wouldHaveWaitedTime " + wouldHaveWaitedTime + " switchedQueues " + switchedQueues*/
	}
}
