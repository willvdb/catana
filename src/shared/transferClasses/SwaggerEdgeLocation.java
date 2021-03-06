package shared.transferClasses;

import shared.model.locations.EdgeDirection;
import shared.model.locations.EdgeLocation;
import swaggerModel.SwaggerModel;

/**
 * This class is used for the Swagger server
 * @author djoshuac
 */
public class SwaggerEdgeLocation {
	private int x;
	private int y;
	private String direction;
	
	public SwaggerEdgeLocation(EdgeLocation edge) {
		edge = edge.getNormalizedLocation();
		this.x = edge.getHexLoc().getX();
		this.y = edge.getHexLoc().getY();
		this.direction = SwaggerModel.edgeDirectionToString(edge.getDir());
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public EdgeDirection getEdgeDirection() {
		return SwaggerModel.stringToEdgeDirection(direction);
	}
}
