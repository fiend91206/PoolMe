package pool.me;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class Profile extends TabActivity{
	public static TabHost tabHost;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
	}
}
