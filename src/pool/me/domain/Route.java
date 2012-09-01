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
