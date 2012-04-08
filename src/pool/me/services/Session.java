package pool.me.services;

import pool.me.domain.User;

public class Session {
	
	private User u;
	
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
}
