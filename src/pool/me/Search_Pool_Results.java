package pool.me;

import java.util.ArrayList;

import pool.me.domain.Carpool;
import pool.me.services.Database;
import pool.me.services.Session;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Search_Pool_Results extends Activity implements OnItemClickListener{
	private Database db;
	private ListView carpoolMatches;
	private ArrayList<Carpool> matches; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_pool_results);
		
		db = new Database(getApplicationContext());
		carpoolMatches = (ListView)findViewById(R.id.pool_matches);
		matches = db.getAllPools();
		ArrayList<String> cm = new ArrayList<String>();
		for (int i = 0; i < matches.size(); i++){
			if (!matches.get(i).equals(""));
				cm.add(matches.get(i).getMatchString());
		}
		
		ArrayAdapter<String> matchesAdapater =
				new ArrayAdapter<String>(this, R.layout.list_item, cm);
		carpoolMatches.setAdapter(matchesAdapater);
		carpoolMatches.setOnItemClickListener(this);
	}
	
	public void onItemClick(View v) {
	}

	public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
		StringBuilder sb = new StringBuilder((String) ((TextView)v).getText());
		int index = sb.indexOf(":") + 2;
		int lindex = sb.indexOf("\n");
		Log.v("sb", sb.toString());
		Log.i("index", "" + index);
		Log.v("lindex", ""+ lindex);
		String text = sb.substring(index, lindex);
		Log.v("text", text);
		int id = Integer.parseInt(text);
		Log.v("id", "" + id);
		Toast t;
		if (db.updateMemberEmail(Session.getInstance().getUser().getEmailAddress(), id) == true){
			t = Toast.makeText(getApplicationContext(), "Successfully added to Carpool", Toast.LENGTH_SHORT);
			t.show();
			startActivity(new Intent(this,Main.class));
			finish();
		}else {
			t = Toast.makeText(getApplicationContext(), "You are already apart of this carpool", Toast.LENGTH_SHORT);
			t.show();
		}		
	}
}
