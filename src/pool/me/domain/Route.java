package pool.me.domain;

import java.util.PriorityQueue;

import android.app.Activity;

/**
 * Represents a single route, which contains a set of checkpoints.
 * @author Shashank
 *
 */
public class Route extends Activity{
	
	private PriorityQueue<Checkpoint> points;
	private int route_start_index;
	//The following two are used to determine when to start validating a carpool.
	private int pool_start_index;
	private int pool_end_index;
	
	public Route()
	{		
	}
	
	public void addCheckpoint(Checkpoint p)
	{
		points.add(p);
	}
	
	public boolean validateRoute()
	{
		boolean validated = false;
		//Code goes here.			
		return validated;
	}
}
