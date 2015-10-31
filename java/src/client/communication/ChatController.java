package client.communication;

import client.base.Controller;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController {

	public ChatController(IChatView view) {
		
		super(view);
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) {
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

