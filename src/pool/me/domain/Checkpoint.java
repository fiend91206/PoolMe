package pool.me.domain;


import com.google.android.maps.GeoPoint;

public class Checkpoint extends GeoPoint implements Comparable{
	
	public static enum PointType  {GENERAL, POOL_START, POOL_END, PICKUP, DROPOFF, SOURCE, DESTINATION, ROUTE_POINT};
	
	private String description;
	private PointType type;
	private int order;
	private boolean reached = false;
	
	public Checkpoint(int latitudeE6, int longitudeE6, PointType type) {
		super(latitudeE6, longitudeE6);
		this.type = type;
		if(type == PointType.GENERAL)
		{
				this.order = -1;			
		}
	}

	public Checkpoint(int latitudeE6, int longitudeE6, PointType type, String description, int order) {
		super(latitudeE6, longitudeE6);
		this.type = type;
		this.description = description;
		if(type == PointType.GENERAL)
		{
				this.order = -1;			
		} else {
			this.order = order;
		}
	}

	public int compareTo(Object another) {
		// TODO Auto-generated method stub
		Checkpoint c = (Checkpoint) another;
		if(this.getOrder() < c.getOrder())
		{
			return -1;
		} else if(this.getOrder() > c.getOrder())
		{
			return 1;
		} else
		{
			return 0;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PointType getType() {
		return type;
	}

	public void setType(PointType type) {
		this.type = type;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isReached() {
		return reached;
	}

	public void setReached(boolean reached) {
		this.reached = reached;
	}
	
	
	
	
	

}
