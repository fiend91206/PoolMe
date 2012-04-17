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

public class Create_Account extends Activity implements OnClickListener{
	
	public static final int MIN_PASS_LEN = 2;
	private View  cancelButton, nextButton;
	private User u;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		setContentView(R.layout.create_account);
		
		nextButton = findViewById(R.id.next);
		cancelButton = findViewById(R.id.cancel);
		
		cancelButton.setOnClickListener(this);		
		nextButton.setOnClickListener(this);
		
		u = new User();
		Session s = Session.getInstance();
		s.setUser(u);
	}
	
	
	/*Validate information from the first page*/
	public ArrayList<String> populateUser_first(User u)
	{
		
		ArrayList<String> emptyField = new ArrayList<String>();
		String s;
		
		s = ((EditText)findViewById(R.id.first_name)).getText().toString();
		if((s.length() < 1)||(s == null))
		{
			emptyField.add("First Name");
		} else {
			u.setFirstName(s);
		}
		
		s = ((EditText)findViewById(R.id.last_name)).getText().toString();
		if((s.length() < 1)||(s == null))
		{
			emptyField.add("Last Name");
		} else {
			u.setLastName(s);
		}
		
		s = ((EditText)findViewById(R.id.email)).getText().toString();
		if((s.length() < 1)||(s == null))
		{
			emptyField.add("Email");
		} else {
			u.setEmailAddress(s);
		}

		String pass2;
		s = ((EditText)findViewById(R.id.password)).getText().toString();
		pass2 = ((EditText)findViewById(R.id.passwordConfirm)).getText().toString();
		
		if (s.equals("")||pass2.equals(""))
		{
			emptyField.add("Password field is empty");
		}  else if(!s.equals(pass2))
		{
			emptyField.add("Password fields do not match");
		} else if ((s.length()<MIN_PASS_LEN)||(pass2.length()<MIN_PASS_LEN))
		{
			emptyField.add("Password must be " + MIN_PASS_LEN + " characters long");
		}
		else {
			u.setPass(s);
		}
		
		
		if(emptyField.size() == 0)
		{
			return null;
		} else {
			return emptyField;
		}
		
		
		
	}
	
	

	public void onClick(View v) {
		if(v == nextButton)
		{
			
			ArrayList<String> arr = populateUser_first(u);
			
			if(arr == null)
			{
				startActivity(new Intent(this,Create_Account_2.class));
			} else
			{
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
			
					
		}else if(v == cancelButton)
		{
			super.onBackPressed();
		}
		
	}

}

