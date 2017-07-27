package cabal.starcraft.bot;

import java.util.Observable;

public class AgentEventNotifier extends Observable {
	public void update(){
		setChanged();
		notifyObservers();
	}
}
