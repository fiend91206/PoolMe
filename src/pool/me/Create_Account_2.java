package pool.me;

import java.util.ArrayList;

import pool.me.domain.User;
import pool.me.services.Session;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class Create_Account_2 extends Activity implements OnClickListener{
	
	public static final int MIN_PASS_LEN = 2;
	private View submitButton, backButton;
	
	private User u;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		setContentView(R.layout.create_account_2);	
		
		submitButton = findViewById(R.id.submit_2);
		backButton = findViewById(R.id.ca_back);
		
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
		if(!((s.length() < 1)||(s == null)))
		{
			try
			{
				int n = Integer.parseInt(s);
				u.setContactNumber(n);
			} catch(Exception e)
			{
				arr.add("Invalid phone number");
			}		
			
			
		}
		
		s = ((EditText)findViewById(R.id.aboutMe)).getText().toString();
		if(!((s == null)||(s.length() < 1)))
		{
			u.setAboutMe(s);
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
				startActivity(new Intent(this, Main.class));
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

