package cabal.starcraft.bot;

public class EconomicResources {
	public double minerals;
	public double gas;
	
	public int workerCount;
	
	synchronized double getMinerals(){
		return minerals;
	}
	
	synchronized void setMinerals(double minerals){
		this.minerals = minerals;
	}
	
	synchronized int getWorkerCount(){
		return workerCount;
	}
}
