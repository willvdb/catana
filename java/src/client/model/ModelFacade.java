package client.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import shared.definitions.VertexDirection;

public class ModelFacade {
	private static ClientModel model = null;
	
	/**
	 * Updates the model if the given model's version is newer.
	 * @pre none
	 * @post If the ModelFacade's model is not yet initialized, or the given model has a higher version,
	 * the model is updated to the given model.
	 * @param model - the model to check for an update
	 */
	public static void updateModel(TransferModel lump) {
		if (model.getDataLump() == null) {
			model.update(lump);
		}
		else if (model.getDataLump().getVersion() > ModelFacade.model.getDataLump().getVersion()) {
			model.update(lump);
		}
	}
	
	public static void initialize() {
		model = new ClientModel();
	}
	
	public static List<Hex> getHexes() {
		return model.getDataLump().getMap().getHexes();
	}
	
	
	/**
	* @pre ModelFacade must be initialized and must have a current valid model
	* @param playerIndex - the index of the player in question
	* @param location - location of hex
	* @return true if the given player owns a settlement or city adjacent to that location, false if otherwise
	* @post see return
	*/
	public static boolean canProduceResource(int playerIndex, HexLocation location) {
		Hex thisHex = null;
		Iterator<Entry<HexLocation, Hex>> hexes = model.getHexes().entrySet().iterator();
		while (hexes.hasNext()) {
			Entry<HexLocation, Hex> hex = hexes.next();
			if(hex.getKey().equals(location)){
				thisHex = hex.getValue();
			}
		}
		
		int x = thisHex.getLocation().getX();
		int y = thisHex.getLocation().getY();
	
		VertexLocation west = new VertexLocation(x, y, VertexDirection.West);
		VertexLocation northwest = new VertexLocation(x, y, VertexDirection.NorthWest);
		VertexLocation northeast = new VertexLocation(x, y, VertexDirection.NorthEast);
		VertexLocation east = new VertexLocation(x, y, VertexDirection.East);
		VertexLocation southeast = new VertexLocation(x, y, VertexDirection.SouthEast);
		VertexLocation southwest = new VertexLocation(x, y, VertexDirection.SouthWest);
		
		List<VertexObject> settlements = new ArrayList<VertexObject>();
		
		settlements.add(model.getSettlements().get(west.getNormalizedLocation()));
		settlements.add(model.getSettlements().get(northwest.getNormalizedLocation()));
		settlements.add(model.getSettlements().get(northeast.getNormalizedLocation()));
		settlements.add(model.getSettlements().get(east.getNormalizedLocation()));
		settlements.add(model.getSettlements().get(southeast.getNormalizedLocation()));
		settlements.add(model.getSettlements().get(southwest.getNormalizedLocation()));
		
		List<VertexObject> cities = new ArrayList<VertexObject>();
		
		cities.add(model.getCities().get(west.getNormalizedLocation()));
		cities.add(model.getCities().get(northwest.getNormalizedLocation()));
		cities.add(model.getCities().get(northeast.getNormalizedLocation()));
		cities.add(model.getCities().get(east.getNormalizedLocation()));
		cities.add(model.getCities().get(southeast.getNormalizedLocation()));
		cities.add(model.getCities().get(southwest.getNormalizedLocation()));
		
		for(VertexObject settlement : settlements) {
			if(settlement != null) {
				if(settlement.getOwner() == playerIndex) {
					return true; //HOORAY!
				}
			}
		}
		
		for(VertexObject city : cities) {
			if(city != null) {
				if(city.getOwner() == playerIndex) {
					return true; //HOORAY!
				}
			}
		}
		
		return false; //Awwww... found no settlements or cities for you... how sad....
	}
	

	public int getModelVersion() {
		return model.getDataLump().getVersion();
	}

	public static int whoseTurnIsItAnyway() {
		return model.getDataLump().getTurnTracker().getCurrentPlayer();
	}

	public static boolean canBuildSettlement(int playerIndex, VertexLocation vertLoc) {
		
		if(playerIndex != ModelFacade.whoseTurnIsItAnyway()) {
			return false;
		}
		else if(model.getSettlements().get(vertLoc.getNormalizedLocation()) != null) {
			return false;
		}
		
		else if(model.getCities().get(vertLoc.getNormalizedLocation()) != null) {
			return false;
		}
		
		
		
		
		/*
		VertexValue vertexValue = model.getMap().getVertices().get(location.getNormalizedLocation());
		Player player = model.getPlayers().get(playerIndex);
		//Check for unplaced settlements
		if(player.getUnplacedSettlements() == 0) {
			return false;
		}
		//Does player have enough resources?
		ResourceList rList = player.getResources();
		if(rList.getBrick() == 0 || rList.getSheep() == 0 || rList.getWheat() == 0 || rList.getWood() == 0) {
			return false;
		}
		//Is the distance rule obeyed?
		for(VertexValue vertex : model.getMap().getAdjacentVertices(vertexValue.getLocation())) {
			if(vertex.getSettlement() != null || vertex.getCity() != null) {
				System.out.println(vertex);
				return false;
			}
		}
		//Is there an adjacent road?
		for(EdgeValue edge : model.getMap().getNearbyEdges(vertexValue.getLocation())) {
			if(edge.getRoad() == null) {}
			else if(edge.getRoad().getPlayerIndex() == playerIndex) {
				return true;
			}
		}
		return false;
		*/
		
		return false;
	}

	public static boolean canBuildCity(int playerIndex, VertexLocation vertLoc) {
		// TODO Auto-generated method stub
		
		if(playerIndex != ModelFacade.whoseTurnIsItAnyway()) {
			return false;
		}
		
		return false;
	}

	public static boolean canBuildRoad(int playerIndex, EdgeLocation edgeLoc) {
		// TODO Auto-generated method stub
		
		if(playerIndex != ModelFacade.whoseTurnIsItAnyway()) {
			return false;
		}
		
		return false;
	}

	/**
	 * Queries the hex location of the robber.
	 * @pre ModelFacade.updateModel() must have been called with a valid model
	 * @post see return
	 * @return the hex location of the robber
	 */
	public static HexLocation findRobber() {
		return model.getDataLump().getMap().getRobber();
	}
}
