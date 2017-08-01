package cabal.starcraft.bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import bwapi.DefaultBWListener;
import bwapi.Game;
import bwapi.Mirror;
import bwapi.Player;
import bwapi.Unit;
import bwapi.UnitType;
import cabal.starcraft.bot.monitoring.MonitorGame;

public class TacticalAgentContext extends DefaultBWListener {
	// BWAPI specific
	private Mirror mirror = new Mirror();
	
	private Game game;
	
	private Player avatar;
	
	private MonitorGame game_monitor;	
	
	// Agent specific
	private List<TacticalAgent> tacticalAgents;
	private List<EconomicAgent> economicAgents;
	private List<AgentInterface> agentList;
	// Event oriented approach	
	private AgentEventNotifier agentEventSource;
	
	public TacticalAgentContext(){		
		tacticalAgents = new ArrayList<TacticalAgent>();
		economicAgents = new ArrayList<EconomicAgent>();
		agentList = new ArrayList<AgentInterface>();
		agentEventSource = new AgentEventNotifier();		
		game_monitor = new MonitorGame();
	}
	
	public void addTacticalAgent(TacticalAgent agent){
		tacticalAgents.add(agent);
		agentEventSource.addObserver(agent);
	}
	
	public void addEconomicAgent(EconomicAgent agent){
		economicAgents.add(agent);
		agentList.add(agent);
		System.out.println("Economic agent added");
	}
	
	public void initializeSystem(){
		try{
			EconomicAgent econ1 = new EconomicAgent(avatar);
			// Get command center
			for (Unit u: avatar.getUnits()){
				if (u.getType()==UnitType.Terran_Command_Center||
						u.getType()==UnitType.Protoss_Nexus||
						u.getType()==UnitType.Zerg_Hatchery){
					econ1.setBaseLocation(u);
					System.out.println("Added start location");
				}
			}
			
			List<Unit> minerals = new ArrayList<Unit>();
			for (Unit m: game.neutral().getUnits()){
				if (m.getType().isMineralField()){
					minerals.add(m);
					
				}
			}
			System.out.println("Current count of minerals: "+minerals.size());
			econ1.setAvailableMinerals(minerals);
			System.out.println("Adding agents");
			addEconomicAgent(econ1);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void startup(){
		mirror.getModule().setEventListener(this);
		
		mirror.startGame();
		
	}
	
	@Override
	public void onStart() {
		game = mirror.getGame();
		avatar = game.self();
		System.out.println("Game started");
		initializeSystem();
	}
	
	public void DataCollection(){
	}
	
	@Override
	public void onUnitCreate(Unit created_unit) {
		try {
			if (created_unit.getType().isWorker()){
				economicAgents.get(0).newWorker(created_unit);
				game.drawTextScreen(10, 20, 
						game_monitor.logNewUnitBuilt(created_unit,
								game.elapsedTime()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onFrame() {
		game.drawTextScreen(10, 10, "CABAL multiagent system");
		for (AgentInterface a: agentList){
			a.step();
			
		}
	}
	
	
	
}
