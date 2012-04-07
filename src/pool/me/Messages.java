package pool.me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class Messages extends Activity implements OnClickListener{

	private View newMessageButton;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages);
	
       this.newMessageButton =  (ImageButton)findViewById(R.id.newMessageButton);
        
       newMessageButton.setOnClickListener((OnClickListener) this); 
       
	}
	
	public void onClick(View view){
	
		this.startActivity(new Intent(this, New_Message.class));
	}

}
