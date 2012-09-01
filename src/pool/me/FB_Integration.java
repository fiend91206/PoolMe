                                       
package pool.me;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import pool.me.domain.User;
import pool.me.services.Session;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.android.*;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.Facebook.*;

public class FB_Integration extends Activity implements DialogListener{

	Facebook facebook = new Facebook("167443106681426");
	AsyncFacebookRunner async;
	JSONObject json;
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        facebook.authorize(this,new String[] { "email" }, this);
    }
   
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebook.authorizeCallback(requestCode, resultCode, data);
        //setContentView(R.layout.main);
    }

    public void onComplete(Bundle values) {
    	//Toast.makeText(getApplicationContext(), facebook.getAccessToken(), Toast.LENGTH_LONG).show();
    	async = new AsyncFacebookRunner(facebook);
        async.request("me", new UserListener(this));
        async.request("me/friends", new FriendListener(this));
        //setContentView(R.layout.main);
    }

	public void onFacebookError(FacebookError e) {
		// TODO Auto-generated method stub
		
	}

	public void onError(DialogError e) {
		// TODO Auto-generated method stub
		
	}

	public void onCancel() {
		// TODO Auto-generated method stub
		
	}

  }
 


 class UserListener implements RequestListener{
	 private Activity a;

	public UserListener(Activity a)
	{
		super();
		this.a = a; 
	}
	 
	 public void onComplete(String response, Object state) {
		try {
			JSONObject json= new JSONObject(response);
			String firstName = json.getString("first_name");
			String lastName = json.getString("last_name");
			String email = json.getString("email");
			User u = new User(email, firstName, lastName);
			u.setFirstName("Shashank");
			u.setLastName("Chamoli");
			u.setContactNumber(77012567);
			u.setAboutMe("Hello. I'm new here too.");
			u.setSourceLocation("900 Hemphil Avenue");
			Session s = Session.getInstance();
			s.setUser(u);
			//a.setContentView(R.layout.main);
			a.startActivity(new Intent(a,Main.class));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void onIOException(IOException e, Object state) {
		// TODO Auto-generated method stub
		
	}

	public void onFileNotFoundException(FileNotFoundException e, Object state) {
		// TODO Auto-generated method stub
		
	}

	public void onMalformedURLException(MalformedURLException e, Object state) {
		// TODO Auto-generated method stub
		
	}

	public void onFacebookError(FacebookError e, Object state) {
		// TODO Auto-generated method stub
		
	}
}
 
 class FriendListener implements RequestListener{
	 @SuppressWarnings("unused")
	private Activity a;

	public FriendListener(Activity a)
	{
		super();
		this.a = a; 
	}
	 
	 public void onComplete(String response, Object state) {
		try {
			JSONObject json= new JSONObject(response);
			String firstName = json.getString("first_name");
			String lastName = json.getString("last_name");
			String email = json.getString("email");
			User u = new User(email, firstName, lastName);
			Session s = Session.getInstance();
			s.setUser(u);
			//a.startActivity(new Intent(a,Create_Account_2.class));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void onIOException(IOException e, Object state) {
		// TODO Auto-generated method stub
		
	}

	public void onFileNotFoundException(FileNotFoundException e, Object state) {
		// TODO Auto-generated method stub
		
	}

	public void onMalformedURLException(MalformedURLException e, Object state) {
		// TODO Auto-generated method stub
		
	}

	public void onFacebookError(FacebookError e, Object state) {
		// TODO Auto-generated method stub
		
	}
}


