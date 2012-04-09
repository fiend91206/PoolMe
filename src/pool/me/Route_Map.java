package pool.me;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Route_Map extends Activity implements OnItemClickListener{

	private String pool_routes[] = {"Atlanta to Seatle", "New York to Chicago", "Miami to Houston", "30332 to five points mall", "gatech to ut austin"};
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.route_map);
		
		Toast.makeText(getApplicationContext(), "This feature is not fully complete yet. Currently working off hard coded data." , Toast.LENGTH_LONG).show();
		
		
		ListView lv = (ListView)findViewById(R.id.map_list);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, pool_routes);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(this);
		
	}

	public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
		// TODO Auto-generated method stub
		
		
		String url = "http://maps.google.com/maps?q=" + pool_routes[pos];
		
		Uri routeMap = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, routeMap);
                startActivity(launchBrowser);
                
		
	}
	
}
