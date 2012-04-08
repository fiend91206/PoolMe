package pool.me;

import java.util.ArrayList;

import pool.me.domain.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class Create_Account extends Activity implements OnClickListener{
	
	private View submitButton, cancelButton;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_account);
		submitButton = findViewById(R.id.submit);
		cancelButton = findViewById(R.id.cancel);
		submitButton.setOnClickListener(this);
		cancelButton.setOnClickListener(this);
	}
	
	public ArrayList<String> populateUser(User u)
	{
		
		ArrayList<String> arr = new ArrayList();
		String s;
		
		s = ((EditText)findViewById(R.id.first_name)).getText().toString();
		if((s.length() < 1)||(s == null))
		{
			arr.add("First Name");
		} else {
			u.setFirstName(s);
		}
		
		s = ((EditText)findViewById(R.id.last_name)).getText().toString();
		if((s.length() < 1)||(s == null))
		{
			arr.add("Last Name");
		} else {
			u.setLastName(s);
		}
		
		s = ((EditText)findViewById(R.id.email)).getText().toString();
		if((s.length() < 1)||(s == null))
		{
			arr.add("Email");
		} else {
			u.setEmailAddress(s);
		}

		String pass2;
		s = ((EditText)findViewById(R.id.password)).getText().toString();
		pass2 = ((EditText)findViewById(R.id.passwordConfirm)).getText().toString();
		
		if (s.equals(null)||pass2.equals(null))
		{
			arr.add("Password field is empty");
		}  else if(!s.equals(pass2))
		{
			arr.add("Password fields do not match");
		}else {
			u.setPass(s);
		}
		
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
			User u = new User();
			ArrayList<String> arr = populateUser(u);
			
			if(arr == null)
			{
				startActivity(new Intent(this, Main.class));
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
			
			
		} else if(v == cancelButton)
		{
			
		}
		
	}

}

