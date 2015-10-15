package clientSide.guiThings.roll;

import clientSide.guiThings.base.*;

/**
 * Interface for the roll controller
 */
public interface IRollController extends IController
{
	
	/**
	 * Called when the user clicks the "Roll!" button in the roll view
	 */
	void rollDice();
	
}

