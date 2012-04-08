package pool.me.services;

import pool.me.domain.User;

public class Session {
	
	private User u;
	private static boolean profile_editing;
	private static Session instance = new Session();
	
	public Session()
	{}
	
	
	
	public static synchronized Session getInstance()
	{
		return instance;
	}
	
	public User getUser()
	{
		return u;
	}
	
	public void setUser(User u)
	{
		this.u = u;
	} 
	
	protected Object clone() throws CloneNotSupportedException {
		
		throw new CloneNotSupportedException("Clone is not allowed.");
		
	}



	public static boolean isProfile_editing() {
		return profile_editing;
	}



	public static void setProfile_editing(boolean profile_editing) {
		Session.profile_editing = profile_editing;
	}
	
	
	
	
}
