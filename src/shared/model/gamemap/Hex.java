	package shared.model.gamemap;

import java.util.List;
import java.util.HashMap;


import shared.model.Resource;
import shared.model.locations.HexLocation;
import shared.model.locations.EdgeDirection;
import shared.model.locations.VertexDirection;

/**
 * Contains data about a given hex on the game map.
 */
public class Hex {
	private HexLocation location;
	private Resource resource;
	private int diceNumber;
	
	private HashMap<EdgeDirection, EdgeValue> edges;
	private HashMap<VertexDirection, VertexValue> vertices;
	
	/**
	 * @pre diceNumber must be in the range [2,12], vertices should be of size 6
	 * @param location The HexLocation of this hex
	 * @param resource The resource produced by this hex
	 * @param diceNumber The number that, when rolled by the dice, cause this hex to produce resources
	 * @param vertices The array of vertices associated with this Hex.
	 * @post The objects internal values are set to the given params
	 */
	public Hex(Resource resource) {
		//this.location = location;
		this.resource = resource;
		//this.diceNumber = diceNumber;
		//this.vertices = vertices;
	}
	
	public void establishEdges(HashMap<EdgeDirection, EdgeValue> edgeMap) {
		edges = edgeMap;
	}
	
	public void verifyVertices(HashMap<VertexDirection, VertexValue> vertices) {
		this.vertices = vertices;
	}
	
	public HexLocation getLocation() {
		return location;
	}
	public Resource getResource() {
		return resource;
	}
	public int getDiceNumber() {
		return diceNumber;
	}
	public HashMap<VertexDirection, VertexValue> getVertices() {
		return vertices;
	}

	public HashMap<EdgeDirection, EdgeValue> getEdges() {
		return edges;
	}
	
	public void setLocation(HexLocation location) {
		this.location = location;
	}

	public void setDiceNumber(int diceNumber) {
		this.diceNumber = diceNumber;
	}
	
	public List<VertexValue> getAdjacentVertices() {
		// TODO Auto-generated method stub
		return null;
	}
}
