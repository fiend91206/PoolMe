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
	private String startLocation, destLocation;
	private int capacity, id;
	private String deptTime, retTime;
	
	public Carpool(){
		this(new ArrayList<String>(), "", 0, "", "", 0, "", "");
	}

	public Carpool(ArrayList<String> memberEmail){
		this(memberEmail, "", 0, "", "", 0, "" , "");
	}
	
	public Carpool(ArrayList<String> memberEmail, String dEmail){
		this(memberEmail, dEmail, 0, "","", 0, "", "");
	}
	
	public Carpool(ArrayList<String> memberEmail, String dEmail, int cap){
		this(memberEmail, dEmail, cap, "","", 0, "", "");
	}
	
	public Carpool(ArrayList<String> memberEmail, String demail, int cap, String startLocation, String destLocation){
		this(memberEmail, demail, cap, startLocation, destLocation, 0, "", "");
	}
	
	public Carpool(ArrayList<String> memberEmail, String demail, int cap, String startLocation, String destLocation, String d){
		this(memberEmail, demail, cap,  startLocation, destLocation, 0, d, "");
	}
	
	public Carpool(ArrayList<String> memberEmail, String demail, int cap, String startLocation, String destLocation, int i, String d, String ret){
		membersEmail = memberEmail;
		driverEmail = demail;
		capacity = cap;
		id = i;
		deptTime = d;
		retTime = ret;
		this.startLocation = startLocation;
		this.destLocation = destLocation;
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

	public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public String getDestLocation() {
		return destLocation;
	}

	public void setDestLocation(String destLocation) {
		this.destLocation = destLocation;
	}
	
	
	public void addMember(String memberEmail){
		this.membersEmail.add(memberEmail);
	}
	
	public String toString(){
		return driverEmail + ", " + membersEmail + ", " + startLocation + ", " + destLocation +", " + capacity + ", " + deptTime + ", " + retTime + ", " + id;
	}
	
}
