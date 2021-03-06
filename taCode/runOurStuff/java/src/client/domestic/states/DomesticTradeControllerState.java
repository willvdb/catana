package client.domestic.states;

import shared.definitions.ResourceType;
import shared.model.ResourceList;

public interface DomesticTradeControllerState {

	void startTrade();
	
	void decreaseResourceAmount(ResourceType resource);

	void increaseResourceAmount(ResourceType resource);

	void sendTradeOffer(ResourceList offer, int receiver, ResourceList request);

	void setPlayerToTradeWith(int playerIndex);

	void setResourceToReceive(ResourceType resource);

	void setResourceToSend(ResourceType resource);

	void unsetResource(ResourceType resource);

	void cancelTrade();

	void acceptTrade(boolean willAccept);	
}
