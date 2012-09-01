package pool.me;

import java.util.ArrayList;

import pool.me.domain.Carpool;
import pool.me.domain.User;
import pool.me.services.Database;
import pool.me.services.Session;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class My_Pool extends Activity{
	private Database db;
	private User u;
	private TextView start, sdeptTime, end, edeptTime, cap;
	private ListView members;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_pool);
		u = Session.getInstance().getUser();
		db = new Database(getApplicationContext());
		ArrayList<Carpool> cp = u.getPools();
		
		start = (TextView)findViewById(R.id.start_addr);
		sdeptTime = (TextView)findViewById(R.id.start_depart);
		end = (TextView)findViewById(R.id.end_city);
		edeptTime = (TextView)findViewById(R.id.end_depart);
		cap = (TextView)findViewById(R.id.capacityText);
		members = (ListView)findViewById(R.id.mp_members);
		
		if((cp==null)||(cp.size()<1))
		{
			Log.i("user", "no pools");
			Log.v("email", u.getEmailAddress());
			u.setPools(db.getMyPools(u.getEmailAddress()));
			cp =  u.getPools();
		} 
		
		if (!cp.isEmpty()){
			Carpool c = cp.get(0);
			start.setText("Start City: " + c.getStartLocation());
			sdeptTime.setText("Departure Time: " + c.getDeptTime());
			end.setText("End City: " + c.getDestLocation());
			edeptTime.setText("Departure Time: " + c.getRetTime());
			cap.setText("Capacity: " + c.getCapacity());
			cp.add(c);
			u.setPools(cp);
			
			ArrayList<String> memberList = c.getMembersEmail();
			ArrayAdapter<String> memberAdapater =
					new ArrayAdapter<String>(this, R.layout.list_item, memberList);
			members.setAdapter(memberAdapater);
		}else {
			start.setText("No carpool information found.");
			sdeptTime.setText("Use the FindPool feature on the ");
			end.setText("main page to add a new carpool.");
		}
	}
}
