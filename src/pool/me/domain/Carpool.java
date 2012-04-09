package pool.me.domain;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Contains Carpool information, it's members and the route.
 * @author Shashank
 *	HA HA - -  Paul was here.
 */
public class Carpool {
	
	private ArrayList<String> membersEmail;
	private String driverEmail;
	private Route route;
	private int capacity, id;
	private String deptTime, retTime;
	
	public Carpool(){
		this(new ArrayList<String>(), "", 0, new Route(), 0, "", "");
	}

	public Carpool(ArrayList<String> memberEmail){
		this(memberEmail, "", 0, new Route(), 0, "" , "");
	}
	
	public Carpool(ArrayList<String> memberEmail, String dEmail){
		this(memberEmail, dEmail, 0, new Route(), 0, "", "");
	}
	
	public Carpool(ArrayList<String> memberEmail, String dEmail, int cap){
		this(memberEmail, dEmail, cap, new Route(), 0, "", "");
	}
	
	public Carpool(ArrayList<String> memberEmail, String demail, int cap, Route r){
		this(memberEmail, demail, cap, r, 0, "", "");
	}
	
	public Carpool(ArrayList<String> memberEmail, String demail, int cap, Route r, String d){
		this(memberEmail, demail, cap, r, 0, d, "");
	}
	
	public Carpool(ArrayList<String> memberEmail, String demail, int cap, Route r, int i, String d, String ret){
		membersEmail = memberEmail;
		driverEmail = demail;
		capacity = cap;
		id = i;
		route = r;
		deptTime = d;
		retTime = ret;
	}

	public ArrayList<String> getMembersEmail() {
		return membersEmail;
	}

	public void setMembersEmail(ArrayList<String> membersEmail) {
		this.membersEmail = membersEmail;
	}

	public String getDriverEmail() {
		return driverEmail;
	}

	public void setDriverEmail(String driverEmail) {
		this.driverEmail = driverEmail;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeptTime() {
		return deptTime;
	}

	public void setDeptTime(String deptTime) {
		this.deptTime = deptTime;
	}

	public String getRetTime() {
		return retTime;
	}

	public void setRetTime(String retTime) {
		this.retTime = retTime;
	}
	
	
}
