package pool.me;

import java.util.ArrayList;

import pool.me.domain.Carpool;
import pool.me.domain.User;
import pool.me.services.Database;
import pool.me.services.Session;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class My_Pool extends Activity{
	private Database db;
	private User u;
	private TextView start, sdeptTime, end, edeptTime, cap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_pool);
		u = Session.getInstance().getUser();
		db = new Database(getApplicationContext());
		db.getMyPools(u.getEmailAddress());
		ArrayList<Carpool> cp = u.getPools();
		
		start = (TextView)findViewById(R.id.start_addr);
		sdeptTime = (TextView)findViewById(R.id.start_depart);
		end = (TextView)findViewById(R.id.end_city);
		edeptTime = (TextView)findViewById(R.id.end_depart);
		cap = (TextView)findViewById(R.id.capacityText);
		
		
		if((cp==null)||(cp.size()<1))
		{
			u.setPools(db.getMyPools(u.getEmailAddress()));
			cp =  u.getPools();
		} 
		Carpool c = cp.get(0);
		start.append(c.getStartLocation());
		sdeptTime.append(c.getDeptTime());
		end.append(c.getDestLocation());
		edeptTime.append(c.getRetTime());
		cap.append("" + c.getCapacity());
	}
}
