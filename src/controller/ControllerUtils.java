package controller;

public class ControllerUtils {
	
	private static  boolean hasPlayer;
	private static String playerID;
	
	/**
	 * Check if the player has been added to the game
	 * @author pratap
	 * @return
	 */
	public static boolean isPlayerPresent() { return hasPlayer; }
	
	
	/**
	 * Set the player presence based on add or remove player functionality
	 * @author pratap
	 * @param status
	 */
	public static void setPlayerPresence(boolean status)  { hasPlayer = status; }
	
	
	/**
	 * Getter of playerID that was created last. Helps to retrieve ID that was stored locally.
	 * @author pratap
	 * @return
	 */
	public static String getPlayerID() { return playerID;	}
	
	
	/**
	 * Setter of playerID that was created last. Helps to store ID locally.
	 * @author pratap
	 * @param ID
	 */
	public static void setPlayerID(String ID) { playerID = ID;	}

}
