package cabal.starcraft.bot;

import bwapi.DefaultBWListener;
import bwapi.Game;
import bwapi.Mirror;
import bwapi.Player;

public class TacticalAgentContext extends DefaultBWListener {
	private Mirror mirror = new Mirror();
	
	private Game game;
	
	private Player avatar;
	
	public void startup(){
		mirror.getModule().setEventListener(this);
		mirror.startGame();
	}
	
	@Override
	public void onStart() {
		game = mirror.getGame();
	}
	
	public void DataCollection(){
	}
	
	@Override
	public void onFrame() {
		game.drawTextScreen(10, 10, "CABAL multiagent system");
		
	}
}
