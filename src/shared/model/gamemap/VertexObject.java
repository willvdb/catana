package shared.model.gamemap;

import shared.model.pieces.Settlement;
import shared.model.pieces.City;

/**
 *  An object representing the vertex of a Hex, containing information about what is located at that hex
 */
public class VertexObject {

	private int owner;
	private EdgeLocation location;
	private Settlement settlement;
	private City city;
	
	/**
	 * @pre owner must be in the range [-1,3], and location must be a valid EdgeLocation
	 * @param owner The owner of this vertex; should be -1 if nobody owns it. 
	 * @param location The map location of this vertex
	 * @post The object's stored owner and location are set to the given params.
	 */
	public VertexObject(int owner, EdgeLocation location) {
		this.owner = owner;
		this.location = location;
		this.settlement = null;
		this.setCity(null);
	}

	public int getOwner() {
		return owner;
	}

	public EdgeLocation getLocation() {
		return location;
	}

	public Settlement getSettlement() {
		return settlement;
	}

	public void setSettlement(Settlement settlement) {
		this.settlement = settlement;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	
	
}