package cabal.starcraft.bot;

public class TacticalAgentInstance {
	private static TacticalAgentContext agentContext = new TacticalAgentContext();
	
	public static void main(String args[])
	{
		agentContext.startup();
	}
}
