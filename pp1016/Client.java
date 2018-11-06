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

public class Client {
	private String id;
	private int waitTime;
	private int serverTime;
	private int wouldHaveWaitedTime;
	private int precederTimeAfterSwitch;
	private boolean switchedQueues;
	private String preceededBySwitchedId;
	private String preceederId; //back reference
	private int originalQueue;
	private int currentQueue;
	
	private static Random random = new Random();
	
	Client(String ID){
		id = ID;
		waitTime = 0;
		serverTime = random.nextInt(1 + 5);
		wouldHaveWaitedTime = 0;
		switchedQueues = false;
		preceededBySwitchedId = "";
		precederTimeAfterSwitch = -1;
	}

	public void copyClient(Client c1){
		this.waitTime = c1.getWaitTime();
		this.serverTime = c1.getServerTime();
		this.wouldHaveWaitedTime = c1.getWouldHaveWaitedTime();
		this.switchedQueues = c1.getSwitchedQueues();
		this.preceededBySwitchedId = c1.getPreceededBySwitchedId();
		this.precederTimeAfterSwitch = c1.getPrecederTimeAfterSwitch();
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
	
	public void setPrecederTimeAfterSwitch(int t){
		precederTimeAfterSwitch = t;
	}
	
	public int getPrecederTimeAfterSwitch(){
		return precederTimeAfterSwitch;
	}
	
	public void setPreceededBySwitchedId(String sId){
		preceededBySwitchedId = sId;
	}
	
	public String getPreceededBySwitchedId(){
		return preceededBySwitchedId;
	}
	
	public void setPreceederId(String sId){
		preceederId = sId;
	}
	
	public String getPreceederId(){
		return preceederId;
	}
	
	public boolean getSwitchedQueues(){
		return switchedQueues;
	}
	
	public void setSwitchedQueues(boolean b){
		switchedQueues = b;
	}
	public int getWaitTime(){
		return waitTime;
	}
	
	public void setWaitTime(int wt){
		waitTime = wt;
	}

	public void setWouldHaveWaitedTime(int t){
		wouldHaveWaitedTime = t;
	}
	
	public int getWouldHaveWaitedTime(){
		return wouldHaveWaitedTime;
	}
	
	public int getServerTime(){
		return serverTime;
	}

	public void setServerTime(int sT){
		serverTime = sT;
	}
	
	public String toString(){
		boolean terseOutput = true;
		String outputStr = "\n id " + this.id + " current wait time " + this.waitTime + " original queue " + this.originalQueue + "  current queue " + this.currentQueue + " serverTime " + serverTime;
		if (terseOutput == true) outputStr += " wouldHaveWaitedTime " + wouldHaveWaitedTime;
		return outputStr;
	}
}
