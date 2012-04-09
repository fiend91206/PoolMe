package pool.me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Find_Pool extends Activity implements OnClickListener{
	
	private View postButton, searchButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_pool);
		postButton = findViewById(R.id.fp_create);
		searchButton = findViewById(R.id.fp_search);
		
		postButton.setOnClickListener(this);
		searchButton.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == postButton)
		{
			startActivity(new Intent(this, Create_Pool.class));
			
		} else if(v == searchButton)
		{
			startActivity(new Intent(this, Search_Pool.class));
		}
		
		
	}

}
