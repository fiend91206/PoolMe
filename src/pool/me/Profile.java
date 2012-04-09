package pool.me;

import pool.me.domain.User;
import pool.me.services.Session;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TextView;

public class Profile extends Activity implements OnClickListener{
	
	private View okButton, editButton;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        
        okButton = findViewById(R.id.profile_ok);
        editButton = findViewById(R.id.profile_edit);
        
        okButton.setOnClickListener(this);
        editButton.setOnClickListener(this);
        
        User u = Session.getInstance().getUser();
        if(u != null)
        {
        	TextView t;
        	t = (TextView)findViewById(R.id.profile_name);
            t.setText("Full Name: " + u.getLastName() + ", " + u.getFirstName());
            
            t = (TextView)findViewById(R.id.profile_email);
            t.setText("Email:" + u.getEmailAddress());
            
            t = (TextView)findViewById(R.id.profile_phone);
            t.setText("Phone Number: " + u.getContactNumber());
            
            t = (TextView)findViewById(R.id.profile_addr);
            t.setText("Street Address: " + u.getSourceLocation());
            
            t = (TextView)findViewById(R.id.profile_aboutMe);
            t.setText("About Me: " + u.getAboutMe());
        } else {
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	builder.setTitle("User Not Found");
			builder.setMessage("Unable to obtain current User information. Please login again.");
			builder.setNeutralButton("Ok", null);
			builder.show();
			
			
			startActivity(new Intent(this, Login.class));
			finish();
        }
        
        
        
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v==okButton)
		{
			super.onBackPressed();
		} else if(v == editButton)
		{
			Session.setProfile_editing(true);
			startActivity(new Intent(this, Create_Account.class));
			
			
		}
	}
}
