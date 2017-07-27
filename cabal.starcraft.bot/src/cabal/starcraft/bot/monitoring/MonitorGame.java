package cabal.starcraft.bot.monitoring;

import bwapi.Game;
import bwapi.Player;

public class MonitorGame {
	public void monitorFrame(Game game){
		game.getPlayers().forEach((p)->{
			p.allUnitCount();
			p.getUnits().forEach((u)->{
				System.out.print(u.getPosition().getX());
				System.out.print(" ");
				System.out.println(u.getPosition().getY());
				System.out.println(u.getType());
			});
		});
	}
}
