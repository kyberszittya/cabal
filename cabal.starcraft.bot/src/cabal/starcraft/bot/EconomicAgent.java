package cabal.starcraft.bot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Stack;

import bwapi.Player;
import bwapi.Unit;
import bwapi.UnitType;

public class EconomicAgent implements Observer, AgentInterface {
	private int optimalMineralGather;
	private int optimalGasGather;
	
	private int currentMinimalGather;
	private int currentGasGather;
	
	private Unit baseLocation;
	
	Map<Unit, Integer> mineral_cluster;
	Unit closestMineral;
	int largestWorkerCount;
	
	LinkedList<Unit> scheduled_workers;
	LinkedList<Unit> available_workers;
	
	// BWAPI specific
	Player avatar;
	
	public EconomicAgent(Player avatar){
		optimalMineralGather = 15;
		optimalGasGather = 3;
		
		mineral_cluster = new HashMap<Unit, Integer>();
		scheduled_workers = new LinkedList<Unit>();
		closestMineral = null;
		
		this.avatar = avatar;
		System.out.println("Economic agent created");
	}
	
	public void setBaseLocation(Unit base_loc){
		this.baseLocation = base_loc;
	}
	
	public void setAvailableMinerals(List<Unit> minerals){
		System.out.println("Current count of minerals: "+minerals.size());
		System.out.println("Setting minerals");
		for (int j=0; j < minerals.size(); j++){
			System.out.println(minerals.get(j));
		}
		double minDistance = Double.POSITIVE_INFINITY;
		try{
			for (Unit u: minerals){
				minDistance = Math.min(minDistance, u.getDistance(baseLocation));
				closestMineral = u;
			}
			System.out.println("Minimal distance: "+minDistance+closestMineral);
			for (Unit m: minerals){
				if (m.getDistance(baseLocation) - minDistance < 50){
					mineral_cluster.put(m, 0);
					System.out.print("Put new mineral resource: ");
					System.out.println(minDistance);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public Unit selectWorker(){
		Random rnd = new Random();
		return available_workers.get(rnd.nextInt(available_workers.size()));
	}
	
	@Override
	public void step() {
		if (avatar.minerals() >= 50 &&
				avatar.supplyUsed()<
				avatar.supplyTotal(avatar.getRace())-1&&
				!baseLocation.isTraining()){
			baseLocation.build(UnitType.Terran_SCV);
		}
		if (avatar.supplyUsed() <
				avatar.supplyTotal(avatar.getRace())*0.8){
			//selectWorker().build(UnitType.Terran_Supply_Depot, );
		}
		for (Unit w: scheduled_workers){
			if (w.isIdle()&&w.canGather()){
				System.out.println("Sending scheduled worker");
				w.gather(closestMineral, false);
				scheduled_workers.remove(w);
				available_workers.add(w);
			}
		}
	}
	
	public void newWorker(Unit unit){
		try{
			if (unit.canGather() && unit.isIdle() && closestMineral!=null){
				unit.gather(closestMineral, false);
			}
			else {
				scheduled_workers.push(unit);
			}
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}

}
