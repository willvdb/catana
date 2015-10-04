package clientSide.tests;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.model.ClientModel;
import shared.model.Resource;
import shared.model.TradeOffer;
import shared.model.locations.EdgeLocation;
import shared.model.locations.HexLocation;
import shared.transferClasses.AcceptTrade;
import shared.transferClasses.AddAIRequest;
import shared.transferClasses.BuildCity;
import shared.transferClasses.BuildRoad;
import shared.transferClasses.BuildSettlement;
import shared.transferClasses.BuyDevCard;
import shared.transferClasses.CreateGameRequest;
import shared.transferClasses.DiscardCards;
import shared.transferClasses.FinishTurn;
import shared.transferClasses.Game;
import shared.transferClasses.JoinGameRequest;
import shared.transferClasses.MaritimeTrade;
import shared.transferClasses.Monopoly;
import shared.transferClasses.Monument;
import shared.transferClasses.RoadBuilding;
import shared.transferClasses.RobPlayer;
import shared.transferClasses.RollNumber;
import shared.transferClasses.SendChat;
import shared.transferClasses.Soldier;
import shared.transferClasses.UserCredentials;
import shared.transferClasses.YearOfPlenty;
import clientSide.exceptions.ServerException;
import clientSide.server.ClientCommunicator;
import clientSide.server.ClientServerFacade;
import clientSide.server.IServer;

public class ServerProxyUnitTests {
	@Before
	public void setup() {
	}
	
	@After
	public void teardown() {
	}
	
	@Test
	public void serverProxyTests_1() {
		System.out.println("Starting ServerProxyTests");
		IServer server = new ClientServerFacade("localhost:8081");
		UserCredentials creds = new UserCredentials("Pigmeaesaa", "canFly");
		try {
			server.register(creds);
			server.login(creds);
			server.getGamesList();
			server.createGame(new CreateGameRequest(false, false, false, "Game name"));
			
			//need a cookie for here on out
			server.joinGame(new JoinGameRequest(0, "red"));
			//server.getModel(-1); Swagger model doesn't match our json
			server.addAI(new AddAIRequest("LARGEST_ARMY"));
			server.listAITypes();
			server.sendChat(new SendChat(0, "Heya!"));
			server.rollDice(new RollNumber(0, 7));
			server.robPlayer(new RobPlayer(0, 1, new HexLocation(0, 0)));
			server.finishTurn(new FinishTurn(0));
			server.buyDevCard(new BuyDevCard(0));
			server.yearOfPlenty(new YearOfPlenty(0, Resource.BRICK, Resource.ORE));
			server.roadBuilding(new RoadBuilding(0, null, null));
			server.soldier(new Soldier(0, 0, null));
			server.monopoly(new Monopoly(0, null));
			server.monument(new Monument(0));
			server.buildRoad(new BuildRoad(0, null, null));
			server.buildSettlement(new BuildSettlement(0, null, null));
			server.buildCity(new BuildCity(0, null, null));
			server.offerTrade(new TradeOffer(0, 0, null, null));
			server.respondToTrade(new AcceptTrade(0, null));
			server.maritimeTrade(new MaritimeTrade(0, 0, null, null));
		}
		catch (ServerException e) {
			System.out.println(e.getReason());
		}
	}
}
