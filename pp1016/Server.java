/*
 * Aaron Stark
 * COSC 311
 * pp1016
 * FALL 2018
 * https://github.com/aaronsta/COSC311/tree/master/pp1016
 * 
 * 
 */
public class Server {
	private Client clientServing;
	private boolean isBusy;
	private int startingTime;
	private int timeRemainingWithClient;
	
	public Server(){
	}
	
	public void setClientServing(Client c){
		clientServing = c;
	}
	
	public Client getClientServed(){
		return clientServing;
	}

	public boolean getIsBusy(){
		return isBusy;
	}

	public void setIsBusy(boolean b){
		isBusy = b;
	}
	

	public boolean isDoneWithClient(int tick){
		boolean result = false;
		if (clientServing != null){
			//on later rounds, calculate elapsed time and compare with client's server time in order to calculate if we are done
			if(isBusy == true){
				int elapsedTimeWithClient = tick - startingTime;
				timeRemainingWithClient = elapsedTimeWithClient;
				if (elapsedTimeWithClient >= clientServing.getServerTime()){
					result = true;
				}
			}
			//on the first round, assign the tick we are on to a member variable
			else{
					startingTime = tick;
					result = false;
				}
		}
			
		return result;
	}

}
