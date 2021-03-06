package swaggerModel;

import shared.model.Resource;
import shared.model.gamemap.Hex;
import shared.model.locations.HexLocation;

public class SwaggerDesertHex implements SwaggerHex {
	private HexLocation location;
	
	public SwaggerDesertHex(Hex hex) {
		location = hex.getLocation();
	}

	@Override
	public HexLocation getLocation() {
		return location;
	}

	@Override
	public Resource getResource() {
		return Resource.DESERT;
	}

	@Override
	public int getChitNumber() {
		return -1; //has not chit-number
	}
}
