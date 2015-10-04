package shared.model.gamemap;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Random;
import java.util.Collections;

import shared.definitions.PortType;
import shared.model.Resource;
import shared.model.pieces.City;
import shared.model.pieces.Road;
import shared.model.pieces.Settlement;
import shared.model.locations.HexLocation;
import shared.model.locations.EdgeLocation;
import shared.model.locations.EdgeDirection;
import shared.model.locations.VertexLocation;
import shared.model.locations.VertexDirection;

/**
 * Class is a singleton, and contains the data about the game map
 */

public class GameMap {
	
//	public static void main(String args[]) { //THIS IS A TEST FUNCTION TO BE DESTROYED LATER
//		GameMap map = new GameMap(false, false, false, false);
//		System.out.println("whee!");
//	}
	
	
	//private Hex[] hexes;
	
	private HashMap<HexLocation, Hex> hexes;
	private HashMap<EdgeLocation, EdgeValue> edges;
	private HashMap<VertexLocation, VertexValue> vertices;
	
	private ArrayList<Port> ports;
	private ArrayList<Road> roads;
	private ArrayList<Settlement> settlements;
	private ArrayList<City> cities;
	private int radius;
	private HexLocation robber;
	
	/**
	 * Create the gamemap, calling various functions that fill out the params
	 * @pre The game must be starting or the map must be changing
	 * @param randomHexes determines whether or not the hexes are randomized
	 * @param randomChits determines whether or not the chits are randomized
	 * @param randomPorts determines whether or not the ports are randomized
	 * @param loadGame determines whether or not you are loading a game or starting a new one
	 * @post The objects internal values are set to the given params.
	 */
	public GameMap(boolean randomHexes, boolean randomChits, boolean randomPorts, boolean loadGame) {
		
		radius = 2;
		
		if(loadGame == false) {	
			ArrayList<Hex> theHexes = initializeHexList(randomHexes);
			
			ArrayList<Integer> theChits = initializeChitList(randomChits);
			
			//Need something with ports as well
			
			setUpMap(theHexes, theChits);
			
			setUpPorts(randomPorts);
		}
		
	}
	
	/**
	 * Initialize the list of hexes that will be added to the map, maybe randomizing their order
	 * @pre The game must be starting
	 * @param randomHexes determines whether or not the basic grid set up is randomized
	 * @return an array list of hexes
	 * @post the hexes that will eventually be put onto the game map are initialized in an array list
	 */
	private ArrayList<Hex> initializeHexList(boolean randomHexes) {
		
		ArrayList<Hex> gameHexes = new ArrayList<Hex>();
		
		//First Row of Beginner Grid
		gameHexes.add(new Hex(Resource.WOOD));
		gameHexes.add(new Hex(Resource.SHEEP));
		gameHexes.add(new Hex(Resource.WHEAT));
		
		//Second Row
		gameHexes.add(new Hex(Resource.BRICK));
		gameHexes.add(new Hex(Resource.ORE));
		gameHexes.add(new Hex(Resource.BRICK));
		gameHexes.add(new Hex(Resource.SHEEP));
		
		//Third Row
		gameHexes.add(new Hex(Resource.DESERT));
		gameHexes.add(new Hex(Resource.WOOD));
		gameHexes.add(new Hex(Resource.WHEAT));
		gameHexes.add(new Hex(Resource.WOOD));
		gameHexes.add(new Hex(Resource.WHEAT));
		
		//Fourth Row
		gameHexes.add(new Hex(Resource.BRICK));
		gameHexes.add(new Hex(Resource.SHEEP));
		gameHexes.add(new Hex(Resource.SHEEP));
		gameHexes.add(new Hex(Resource.ORE));
		
		//Fifth Row
		gameHexes.add(new Hex(Resource.ORE));
		gameHexes.add(new Hex(Resource.WHEAT));
		gameHexes.add(new Hex(Resource.WOOD));
		
		if(randomHexes == true) {
			long seed = System.nanoTime();
			Collections.shuffle(gameHexes, new Random(seed));
		}
		
		return gameHexes;
	}
	
	/**
	 * Initialize the list of chits that will be added to the game map, maybe randomizing them
	 * @pre The game must be starting
	 * @param randomChits determines whether or not the chits are randomized
	 * @return an array list of integers representing the chits
	 * @post the chits that will eventually be put onto the game map are initialized in an array list
	 */
	private ArrayList<Integer> initializeChitList(boolean randomChits) {
		
		ArrayList<Integer> chits = new ArrayList<Integer>();
		
		//First Row for beginner board
		chits.add(new Integer(11));
		chits.add(new Integer(12));
		chits.add(new Integer(9));
		
		//Second Row
		chits.add(new Integer(4));
		chits.add(new Integer(6));
		chits.add(new Integer(5));
		chits.add(new Integer(10));
		
		//Third Row (Except Desert, which has no chits)
		chits.add(new Integer(3));
		chits.add(new Integer(11));
		chits.add(new Integer(4));
		chits.add(new Integer(8));
		
		//Fourth Row
		chits.add(new Integer(8));
		chits.add(new Integer(10));
		chits.add(new Integer(9));
		chits.add(new Integer(3));
		
		//Fifth Row
		chits.add(new Integer(5));
		chits.add(new Integer(2));
		chits.add(new Integer(6));
		
		if(randomChits == true) {
			long seed = System.nanoTime();
			Collections.shuffle(chits, new Random(seed));
		}
		
		return chits;
	}
	
	/**
	 * Initialize a list of ports then add them to the game map
	 * @pre The game must be starting
	 * @param randomPorts determines whether or not the port types are randomized (locations are always the same)
	 * @return nothing, simply adding the ports to the map
	 * @post the ports are added to the map
	 */
	private void setUpPorts(boolean randomPorts) {
		
		ArrayList<PortType> types = new ArrayList<PortType>();
	
		types.add(PortType.THREE);
		types.add(PortType.SHEEP);
		types.add(PortType.THREE);
		types.add(PortType.THREE);
		types.add(PortType.BRICK);
		types.add(PortType.WOOD);
		types.add(PortType.THREE);
		types.add(PortType.WHEAT);
		types.add(PortType.ORE);
		
		if(randomPorts == true) {
			long seed = System.nanoTime();
			Collections.shuffle(types, new Random(seed));
		}
		
		ports = new ArrayList<Port>();
		
		//add ports to ports array list, based on list of port types. The edge locations never change
		
		ports.add(new Port(types.remove(0), new EdgeLocation(new HexLocation(0,2), EdgeDirection.North), null));
		
		ports.add(new Port(types.remove(0), new EdgeLocation(new HexLocation(1,2), EdgeDirection.NorthEast),
				new EdgeLocation(new HexLocation(2,2), EdgeDirection.North)));
		
		ports.add(new Port(types.remove(0), new EdgeLocation(new HexLocation(2,2), EdgeDirection.SouthEast),
				new EdgeLocation(new HexLocation(2,1), EdgeDirection.NorthEast)));
		
		ports.add(new Port(types.remove(0), new EdgeLocation(new HexLocation(2,0), EdgeDirection.SouthEast), null));
		
		ports.add(new Port(types.remove(0), new EdgeLocation(new HexLocation(1,-1), EdgeDirection.South),
				new EdgeLocation(new HexLocation(0,-2), EdgeDirection.SouthEast)));
		
		ports.add(new Port(types.remove(0), new EdgeLocation(new HexLocation(-1,-2), EdgeDirection.South),
				new EdgeLocation(new HexLocation(0,-2), EdgeDirection.SouthWest)));
		
		ports.add(new Port(types.remove(0), new EdgeLocation(new HexLocation(-2,-2), EdgeDirection.SouthWest), null));
		
		ports.add(new Port(types.remove(0), new EdgeLocation(new HexLocation(-2,0), EdgeDirection.SouthWest),
				new EdgeLocation(new HexLocation(-2,-1), EdgeDirection.NorthWest)));
		
		ports.add(new Port(types.remove(0), new EdgeLocation(new HexLocation(-2,0), EdgeDirection.North),
				new EdgeLocation(new HexLocation(0,-2), EdgeDirection.NorthWest)));

	}
	
	
	/**
	 * Takes the lists of hexes and chits and adds them to the game map
	 * @pre the game is being initialized
	 * @param theHexes a list of hexes
	 * @param theChits a list of chits
	 * @post all the hexes are added to the map
	 */
	private void setUpMap(ArrayList<Hex> theHexes, ArrayList<Integer> theChits) {
		hexes = new HashMap<HexLocation, Hex>();
		edges = new HashMap<EdgeLocation, EdgeValue>();
		vertices = new HashMap<VertexLocation, VertexValue>();
		
		for(int i = -2; i <= 2; i++) {
			for(int j = -2; j <= 2; j++) {
				if(((i > 0) && !(j > 0)) || (!(i > 0) && (j > 0))) { //If either i or j (but not both) is greater than 0...
					if((Math.abs(i) + Math.abs(j)) <= radius) { //If the sum of the absolute values is less than or equal to the radius...
						Hex newHex = addToMap(i, j, theHexes, theChits);
						HashMap<EdgeDirection, EdgeValue> newEdges = addEdges(i, j);
						HashMap<VertexDirection, VertexValue> newVertices = addVertices(i, j);
						newHex.establishEdges(newEdges);
						newHex.verifyVertices(newVertices);
					}
				}
				else
				{
					Hex newHex = addToMap(i, j, theHexes, theChits);
					HashMap<EdgeDirection, EdgeValue> newEdges = addEdges(i, j);
					HashMap<VertexDirection, VertexValue> newVertices = addVertices(i, j);
					newHex.establishEdges(newEdges);
					newHex.verifyVertices(newVertices);
				}
			}
		}
	}
	
	//RUDIMENTARY!!
	/**
	 * Adds the specific hex to the map
	 * @pre the setUpMap function must be in a situation where adding a hex is allowed
	 * @param x the x coordinate where the hex will be placed on the grid
	 * @param y the y coordinate where the hex will be placed on the grid 
	 * @param theHexes a list of hexes
	 * @param theChits a list of chits
	 * @post A new hex is placed on the map
	 * @return
	 */
	private Hex addToMap(int x, int y, ArrayList<Hex> theHexes, ArrayList<Integer> theChits) {
		
		HexLocation coordinates = new HexLocation(x,y);
		
		theHexes.get(0).setLocation(coordinates); //Set the hex's location to the coordinates
		
		if (theHexes.get(0).getResource() == Resource.DESERT) {
			robber = coordinates;
		}
		else {
			theHexes.get(0).setDiceNumber(theChits.get(0).intValue());
			theChits.remove(0); //Pop off top chit as it is already used
		}
		
		hexes.put(coordinates, theHexes.get(0));
		
		return theHexes.remove(0);
	}
	
	//RUDIMENTARY!!
	/**
	 * Adds edges to the map of edges
	 * @pre there must be a hex newly placed on the map
	 * @param x coordinate of edge
	 * @param y coordinate of edge
	 * @return a map of edges (which will be added to a hex)
	 * @post edges are added to map
	 */
	private HashMap<EdgeDirection, EdgeValue> addEdges(int x, int y) {
		
		HashMap<EdgeDirection, EdgeValue> newEdges = new HashMap<EdgeDirection, EdgeValue>();
		
		HexLocation coordinates = new HexLocation(x,y);
		
		//Create new edges in the six directions
		
		EdgeLocation northwestLocation = new EdgeLocation(coordinates, EdgeDirection.NorthWest);
		EdgeValue northwestEdge = new EdgeValue(northwestLocation);
		
		EdgeLocation northLocation = new EdgeLocation(coordinates, EdgeDirection.North);
		EdgeValue northEdge = new EdgeValue(northLocation);
		
		EdgeLocation northeastLocation = new EdgeLocation(coordinates, EdgeDirection.NorthEast);
		EdgeValue northeastEdge = new EdgeValue(northeastLocation);
		
		EdgeLocation southeastLocation = new EdgeLocation(coordinates, EdgeDirection.SouthEast);
		EdgeValue southeastEdge = new EdgeValue(southeastLocation);
		
		EdgeLocation southLocation = new EdgeLocation(coordinates, EdgeDirection.South);
		EdgeValue southEdge = new EdgeValue(southLocation);
		
		EdgeLocation southwestLocation = new EdgeLocation(coordinates, EdgeDirection.SouthWest);
		EdgeValue southwestEdge = new EdgeValue(southwestLocation);
		
		//Check edges map, add new edges if they don't already exist
		
		if(edges.get(northwestLocation.getNormalizedLocation()) == null) {
			edges.put(northwestLocation.getNormalizedLocation(), northwestEdge);
		}
		
		if(edges.get(northLocation.getNormalizedLocation()) == null) {
			edges.put(northLocation.getNormalizedLocation(), northEdge);
		}
		
		if(edges.get(northeastLocation.getNormalizedLocation()) == null) {
			edges.put(northeastLocation.getNormalizedLocation(), northeastEdge);
		}
		
		if(edges.get(southeastLocation.getNormalizedLocation()) == null) {
			edges.put(southeastLocation.getNormalizedLocation(), southeastEdge);
		}
		
		if(edges.get(southLocation.getNormalizedLocation()) == null) {
			edges.put(southLocation.getNormalizedLocation(), southEdge);
		}
		
		if(edges.get(southwestLocation.getNormalizedLocation()) == null) {
			edges.put(southwestLocation.getNormalizedLocation(), southwestEdge);
		}
		
		//Fill in newEdges with related edges on edges map
		
		newEdges.put(EdgeDirection.NorthWest, edges.get(northwestLocation.getNormalizedLocation()));
		newEdges.put(EdgeDirection.North, edges.get(northLocation.getNormalizedLocation()));
		newEdges.put(EdgeDirection.NorthEast, edges.get(northeastLocation.getNormalizedLocation()));
		newEdges.put(EdgeDirection.SouthEast, edges.get(southeastLocation.getNormalizedLocation()));
		newEdges.put(EdgeDirection.South, edges.get(southLocation.getNormalizedLocation()));
		newEdges.put(EdgeDirection.SouthWest, edges.get(southwestLocation.getNormalizedLocation()));
		
		return newEdges;
	}
	
	//RUDIMENTARY!!
	/**
	 * Adds vertices to the map of vertices
	 * @pre there must be a hex newly placed on the map
	 * @param x coordinate of vertex
	 * @param y coordinate of vertex
	 * @return a map of vertices (which will be added to a hex)
	 * @post vertices are added to map
	 */
	private HashMap<VertexDirection, VertexValue> addVertices(int x, int y) {
		
		HashMap<VertexDirection, VertexValue> newVertices = new HashMap<VertexDirection, VertexValue>();
		
		HexLocation coordinates = new HexLocation(x,y);
		
		//Create new vertices in six directions
		
		VertexLocation westLocation = new VertexLocation(coordinates, VertexDirection.West);
		VertexValue westVertex = new VertexValue(westLocation);
		
		VertexLocation northwestLocation = new VertexLocation(coordinates, VertexDirection.NorthWest);
		VertexValue northwestVertex = new VertexValue(northwestLocation);
		
		VertexLocation northeastLocation = new VertexLocation(coordinates, VertexDirection.NorthEast);
		VertexValue northeastVertex = new VertexValue(northeastLocation);
		
		VertexLocation eastLocation = new VertexLocation(coordinates, VertexDirection.East);
		VertexValue eastVertex = new VertexValue(eastLocation);
		
		VertexLocation southeastLocation = new VertexLocation(coordinates, VertexDirection.SouthEast);
		VertexValue southeastVertex = new VertexValue(southeastLocation);
		
		VertexLocation southwestLocation = new VertexLocation(coordinates, VertexDirection.West);
		VertexValue southwestVertex = new VertexValue(southwestLocation);
		
		//check vertices map, adding new vertices if they don't already exist
		
		if(vertices.get(westLocation.getNormalizedLocation()) == null) {
			vertices.put(westLocation.getNormalizedLocation(), westVertex);
		}
		
		if(vertices.get(northwestLocation.getNormalizedLocation()) == null) {
			vertices.put(northwestLocation.getNormalizedLocation(), northwestVertex);
		}
		
		if(vertices.get(northeastLocation.getNormalizedLocation()) == null) {
			vertices.put(northeastLocation.getNormalizedLocation(), northeastVertex);
		}
		
		if(vertices.get(eastLocation.getNormalizedLocation()) == null) {
			vertices.put(eastLocation.getNormalizedLocation(), eastVertex);
		}
		
		if(vertices.get(southeastLocation.getNormalizedLocation()) == null) {
			vertices.put(southeastLocation.getNormalizedLocation(), southeastVertex);
		}
		
		if(vertices.get(southwestLocation.getNormalizedLocation()) == null) {
			vertices.put(southwestLocation.getNormalizedLocation(), southwestVertex);
		}
		
		//Fill in newVertices with related vertices on vertices map
		newVertices.put(VertexDirection.West, vertices.get(westLocation.getNormalizedLocation()));
		newVertices.put(VertexDirection.NorthWest, vertices.get(northwestLocation.getNormalizedLocation()));
		newVertices.put(VertexDirection.NorthEast, vertices.get(northeastLocation.getNormalizedLocation()));
		newVertices.put(VertexDirection.East, vertices.get(eastLocation.getNormalizedLocation()));
		newVertices.put(VertexDirection.SouthEast, vertices.get(southeastLocation.getNormalizedLocation()));
		newVertices.put(VertexDirection.SouthWest, vertices.get(southwestLocation.getNormalizedLocation()));
		
		return newVertices;
	}
	
	/**
	 * Find the edges adjacent to the specified edge
	 * @pre a need to find the edges adjacent for a specific edge
	 * @param checkEdge the edge to find the adjacent edges for
	 * @return a list of the adjacent edges
	 * @pre the adjacent edges are found
	 */
	public List<EdgeValue> getAdjacentEdges(EdgeLocation checkEdge) {
		
		List<EdgeValue> adjacentEdges = new ArrayList<EdgeValue>();
		
		HexLocation coordinates = checkEdge.getHexLoc();
		EdgeDirection face = checkEdge.getDir();
		
		EdgeLocation adjacentLocationOne;
		EdgeLocation adjacentLocationTwo;
		
		switch (face) {
		case NorthWest:
			adjacentLocationOne = new EdgeLocation(coordinates, EdgeDirection.SouthWest);
			adjacentLocationTwo = new EdgeLocation(coordinates, EdgeDirection.North);
			break;
		case North:
			adjacentLocationOne = new EdgeLocation(coordinates, EdgeDirection.NorthWest);
			adjacentLocationTwo = new EdgeLocation(coordinates, EdgeDirection.NorthEast);
			break;
		case NorthEast:
			adjacentLocationOne = new EdgeLocation(coordinates, EdgeDirection.North);
			adjacentLocationTwo = new EdgeLocation(coordinates, EdgeDirection.SouthEast);
		case SouthEast:
			adjacentLocationOne = new EdgeLocation(coordinates, EdgeDirection.NorthEast);
			adjacentLocationTwo = new EdgeLocation(coordinates, EdgeDirection.South);
		case South:
			adjacentLocationOne = new EdgeLocation(coordinates, EdgeDirection.SouthEast);
			adjacentLocationTwo = new EdgeLocation(coordinates, EdgeDirection.SouthWest);
		case SouthWest:
			adjacentLocationOne = new EdgeLocation(coordinates, EdgeDirection.South);
			adjacentLocationTwo = new EdgeLocation(coordinates, EdgeDirection.NorthWest);
		default:
			adjacentLocationOne = null;
			adjacentLocationTwo = null;
			break;
		}
		
		adjacentEdges.add(edges.get(adjacentLocationOne.getNormalizedLocation()));
		adjacentEdges.add(edges.get(adjacentLocationTwo.getNormalizedLocation()));
		
		return adjacentEdges;
	}

	/**
	 * Find the vertices adjacent to the specified vertex
	 * @pre a need to find the vertices adjacent for a specific vertex
	 * @param checkVertex the vertex to find the adjacent vertices for
	 * @return a list of the adjacent vertices
	 * @pre the adjacent vertices are found
	 */
	public List<VertexValue> getAdjacentVertices(VertexLocation checkVertex) {
		
		List<VertexValue> adjacentVertices = new ArrayList<VertexValue>();
		
		HexLocation coordinates = checkVertex.getHexLoc();
		VertexDirection point = checkVertex.getDir();
		
		VertexLocation adjacentLocationOne;
		VertexLocation adjacentLocationTwo;
		
		switch(point) {
			case NorthWest:
				adjacentLocationOne = new VertexLocation(coordinates, VertexDirection.West);
				adjacentLocationTwo = new VertexLocation(coordinates, VertexDirection.NorthEast);
				break;
			case NorthEast:
				adjacentLocationOne = new VertexLocation(coordinates, VertexDirection.NorthWest);
				adjacentLocationTwo = new VertexLocation(coordinates, VertexDirection.East);
				break;
			case East:
				adjacentLocationOne = new VertexLocation(coordinates, VertexDirection.NorthEast);
				adjacentLocationTwo = new VertexLocation(coordinates, VertexDirection.SouthEast);
				break;
			case SouthEast:
				adjacentLocationOne = new VertexLocation(coordinates, VertexDirection.East);
				adjacentLocationTwo = new VertexLocation(coordinates, VertexDirection.SouthWest);
				break;	
			case SouthWest:
				adjacentLocationOne = new VertexLocation(coordinates, VertexDirection.SouthEast);
				adjacentLocationTwo = new VertexLocation(coordinates, VertexDirection.West);
				break;
			case West:
				adjacentLocationOne = new VertexLocation(coordinates, VertexDirection.SouthWest);
				adjacentLocationTwo = new VertexLocation(coordinates, VertexDirection.NorthWest);
				break;
				
			default:
				adjacentLocationOne = null;
				adjacentLocationTwo = null;
				break;
		}
		
		adjacentVertices.add(vertices.get(adjacentLocationOne.getNormalizedLocation()));
		adjacentVertices.add(vertices.get(adjacentLocationTwo.getNormalizedLocation()));
		
		return adjacentVertices;
	}
	
	public HexLocation getRobber() {
		return robber;
	}

	public void setRobber(HexLocation robber) {
		this.robber = robber;
	}

	public HashMap<HexLocation, Hex> getHexes() {
		return hexes;
	}

	public ArrayList<Port> getPorts() {
		return ports;
	}

	public ArrayList<Road> getRoads() {
		return roads;
	}
	
	/**
	 * @pre EdgeValue must be a valid location to place a road
	 * @param road New location to place road
	 * @post road is added to the map
	 */
	public void addRoad(Road road) {
		roads.add(road);
	}

	public ArrayList<Settlement> getSettlements() {
		return settlements;
	}
	
	/**
	 * @pre settlement must be a valid location to place a settlement
	 * @param settlement The location for the new settlement
	 * @post settlement is added to the map
	 */
	public void addSettlement(Settlement settlement) {
		settlements.add(settlement);
	}

	public ArrayList<City> getCities() {
		return cities;
	}
	
	/**
	 * @pre city must be a valid location to upgrade a settlement
	 * @param city
	 * @post The settlement at the vertex is removed from the map, and a city put in its place
	 */
	public void addCity(City city) {
		cities.add(city);
	}

	public int getRadius() {
		return radius;
	}
	
	public HashMap<EdgeLocation, EdgeValue> getEdges() {
		return edges;
	}
	
	public HashMap<VertexLocation, VertexValue> getVertices() {
		return vertices;
	}
}
