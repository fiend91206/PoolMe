package pool.me;

import android.app.Activity;
import android.app.SearchableInfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class Search_Pool extends Activity implements OnClickListener{

	private View cancelButton, searchButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_pool);
		
		cancelButton = findViewById(R.id.sp_cancel);
		searchButton = findViewById(R.id.sp_Search);
		
		cancelButton.setOnClickListener(this);
		searchButton.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v == searchButton)
		{
			String startLoc = ((EditText)findViewById(R.id.sp_start)).getText().toString();
			String destLoc = ((EditText)findViewById(R.id.sp_dest)).getText().toString();
			
			//Search code goes here.
			
			startActivity(new Intent(this, Search_Pool_Results.class));
			
		} else if (v == cancelButton)
		{
			super.onBackPressed();
		}
		
	}
	
	

}
