package pool.me;

import java.util.ArrayList;

import pool.me.domain.Carpool;
import pool.me.services.Session;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class Create_Pool extends Activity implements  OnClickListener{
	
	private View cancelButton, addButton;
	private Carpool c;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_pool);
		EditText e = (EditText)findViewById(R.id.cp_start);
		e.setText(Session.getInstance().getUser().getSourceLocation());
		cancelButton = findViewById(R.id.cp_cancel);
		addButton = findViewById(R.id.cp_add);
		
		cancelButton.setOnClickListener(this);
		addButton.setOnClickListener(this);
		
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == cancelButton)
		{
			super.onBackPressed();
		} else if (v == addButton)
		{
			c = new Carpool();
			c.setDriverEmail(Session.getInstance().getUser().getEmailAddress());
			ArrayList<String> arr = populateCarpool(c);
			if((arr == null) || (arr.size()<1))
			{
				//Add to server code goes here
				Toast t = Toast.makeText(getApplicationContext(), "Carpool Posted to Server", Toast.LENGTH_SHORT);
				t.show();
				startActivity(new Intent(this,Main.class));
			} else {
				String s = "The following fields are required:\n";				
				for(int i=0; i<arr.size(); i++)
				{
					s += arr.get(i) + ",\n";
				}
				s += "Please complete these fields.";
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage(s);
				builder.setNeutralButton("Ok", null);
				builder.show();
			}
		}
		
	}
	
	public ArrayList<String> populateCarpool(Carpool c)
	{
		ArrayList<String> arr = new ArrayList<String>();
		String s;
		s = ((EditText)findViewById(R.id.cp_start)).getText().toString();
		if((s.length() < 1)||(s == null))
		{
			arr.add("Start Location");
		} else {
			c.setStartLocation(s);
		}
		
		s = ((EditText)findViewById(R.id.cp_dest)).getText().toString();
		if((s.length() < 1)||(s == null))
		{
			arr.add("Destination Location");
		} else {
			c.setDestLocation(s);
		}
		
		
		
		s = ((EditText)findViewById(R.id.cp_start_time)).getText().toString();
		if((s.length() < 1)||(s == null))
		{
			arr.add("Start Time");
		} else {
			c.setDeptTime(s);
		}
		
		s = ((EditText)findViewById(R.id.cp_return_time)).getText().toString();
		if((s.length() < 1)||(s == null))
		{
			arr.add("Return Time");
		} else {
			c.setRetTime(s);
		}
		
		s = ((EditText)findViewById(R.id.cp_numseats)).getText().toString();
		try {
			int n = Integer.parseInt(s);
			if((n < 1) || (n > 60))
			{
				arr.add("Invalid Number of seats");
			} else {
				c.setCapacity(n);
			}
		} catch (Exception e) {
			arr.add("Invalid number of seats");
		}
		
		return arr;
		
		
		
	}

}

