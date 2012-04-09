package pool.me;

import java.util.ArrayList;

import pool.me.domain.Carpool;
import pool.me.domain.User;
import pool.me.services.Session;
import android.app.Activity;
import android.os.Bundle;

public class My_Pool extends Activity{
	
	private User u;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_pool);
		u = Session.getInstance().getUser();
		
		ArrayList<Carpool> cp = u.getPools();
		if((cp==null)||(cp.size()<1))
		{
			
		} else {
			
		}
		
	}

}
