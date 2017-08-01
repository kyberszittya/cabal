package cabal.starcraft.bot.monitoring;

import java.util.HashMap;
import java.util.Map;

import bwapi.Game;
import bwapi.Player;
import bwapi.Unit;

public class MonitorGame {
	private Game currentGame;
	
	private Map<Unit, UnitData> loggedUnits;
	
	public MonitorGame(){
		
		loggedUnits = new HashMap<Unit, UnitData>();
	}
		
	public String logNewUnitBuilt(Unit unit, int spawnTime){
		loggedUnits.put(unit, new UnitData(unit, spawnTime));
		System.out.println(loggedUnits.get(unit).toString());
		return loggedUnits.get(unit).toString();
	}
}
