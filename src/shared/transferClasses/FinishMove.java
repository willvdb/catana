package shared.transferClasses;

public class FinishMove {

	public FinishMove(int playerIndex){
		this.playerIndex = playerIndex;
	}

	private String type = "finishMove";
	private int playerIndex;

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

}
