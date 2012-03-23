package pool.me.domain;

import java.util.PriorityQueue;

import android.app.Activity;

public class Route extends Activity{
	
	private PriorityQueue<Checkpoint> points;
	private int route_start_index;
	private int pool_start_index;
	private int pool_end_index;
}
