package pool.me;

import pool.me.domain.Route;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{
	
	private View regularLogin, createAccount;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		regularLogin = findViewById(R.id.login);
		createAccount = findViewById(R.id.createAccount);
		
		regularLogin.setOnClickListener(this);
		createAccount.setOnClickListener(this);
		
	}
	
	public void onClick(View v) {
		
		if(v == this.regularLogin)
		{
			//Authentication code goes here.
			startActivity(new Intent(this, Main.class));
			
		} else if(v == this.createAccount)
		{
			startActivity(new Intent(this, Create_Account.class));
		}
		
		
	}

	
	

}
