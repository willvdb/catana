package shared.model.pieces;

/**
 * Representative of a road placed on the board in game
 * @author Steen
 *
 */

public class Road {

	int playerIndex;

	/**	
	 * @pre playerIndex must be in the range [0, 3]
	 * @param playerIndex
	 * @post a new road is made, owned by the player that shares its playerIndex
	 */
	public Road(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}
	
	
	
}
