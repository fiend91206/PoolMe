package pool.me;

import pool.me.domain.Route;
import pool.me.domain.User;
import pool.me.services.Database;
import pool.me.services.Session;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{
	
	private View regularLogin, facebookLogin, createAccount;
	private Database db;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		db = new Database();
		
		if((Session.getInstance()).equals(null))
		{
			Session s = new Session();
		}
		
		setContentView(R.layout.login);
		regularLogin = findViewById(R.id.login);
		createAccount = findViewById(R.id.createAccount);
		facebookLogin = findViewById(R.id.facebookLogin);
		
		regularLogin.setOnClickListener(this);
		createAccount.setOnClickListener(this);
		facebookLogin.setOnClickListener(this);
		
	}
	
	public void onClick(View v) {
		
		if(v == this.regularLogin)
		{
			//Authentication code goes here.
			String email, pwd;
			email =((EditText)findViewById(R.id.uname)).getText().toString();
			pwd =((EditText)findViewById(R.id.login_password)).getText().toString();
			boolean result = false;
			try {
				result =db.authenticate(email, pwd);
				
			} catch (Exception e)
			{
				Toast t = Toast.makeText(getApplicationContext(), "Error: " + e.toString(), Toast.LENGTH_SHORT);
				t.show();
			} 
			
			if(result)
			{
				User u = new User();
				u = db.getUser(email);
				Session.getInstance().setUser(u);
				startActivity(new Intent(this, Main.class));
				finish();
			} else {
				Toast t = Toast.makeText(getApplicationContext(), "Login Failed. Please try again.", Toast.LENGTH_SHORT);
				t.show();
			}
			
			
			
		} else if(v == facebookLogin)
		{
			startActivity(new Intent(this,FB_Integration.class));
			//finish();
			
		} else if(v == this.createAccount)
		{
			startActivity(new Intent(this, Create_Account.class));
		}
		
		
	}

	
	

}
