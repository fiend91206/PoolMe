package pool.me;

import java.util.ArrayList;

import pool.me.domain.User;
import pool.me.services.Database;
import pool.me.services.Session;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Create_Account_2 extends Activity implements OnClickListener{
	
	public static final int MIN_PASS_LEN = 2;
	private View submitButton, backButton;
	private Database db;
	private User u;
	private Spinner radioSpin;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		setContentView(R.layout.create_account_2);	
		
		db = new Database(getApplicationContext());
		
		submitButton = findViewById(R.id.submit_2);
		backButton = findViewById(R.id.ca_back);
		
		radioSpin = (Spinner) findViewById(R.id.radioPrefSpin);
	    ArrayAdapter adapter = ArrayAdapter.createFromResource(
	            this, R.array.radio_prefs, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    radioSpin.setAdapter(adapter);
		
		submitButton.setOnClickListener(this);
		backButton.setOnClickListener(this);
		
		
		Session s = Session.getInstance();
		u = s.getUser();
		
	}	
	
	/*Validate Information from the second page.*/
	public ArrayList<String> populateUser_second(User u)
	{
		
		ArrayList<String> arr = new ArrayList<String>();
		String s;
		
		s = ((EditText)findViewById(R.id.stAddr)).getText().toString();
		if((s.length() < 1)||(s == null))
		{
			arr.add("Street Addres");
		} else {
			u.setSourceLocation(s);
		}
		
		s = ((EditText)findViewById(R.id.phone)).getText().toString();
		if(s.length()>1)
		{
			try
			{
				long n = Long.parseLong(s);
				u.setContactNumber(n);
			} catch(Exception e)
			{
				arr.add("Invalid phone number. Error: " + e.toString());
			}		
						
		}
		
		s = ((EditText)findViewById(R.id.aboutMe)).getText().toString();
		if(!((s == null)||(s.length() < 1)))
		{
			u.setAboutMe(s);
		}
		
		s = radioSpin.getSelectedItem().toString();
		if (((s == null) || (s.length() < 1) || s.equals("Choose a radio preference"))){
			arr.add("Radio Preference");
		}else {
			
		}
		
		if(arr.size() == 0)
		{
			return null;
		} else {
			return arr;
		}
	}

	public void onClick(View v) {
		
		if(v == submitButton)
		{
			ArrayList<String> arr = populateUser_second(u);
			if(arr==null)
			{
				if (db.addUser(u)== true){
					startActivity(new Intent(this, Main.class));
				}else {
					startActivity(new Intent(this, Login.class));
				}
				finish();
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
		
		}else if(v == backButton)
		{
			super.onBackPressed();
		}
	
		
	
	}

}

