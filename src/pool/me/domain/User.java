package pool.me.domain;

import java.util.ArrayList;

import android.provider.MediaStore.Images;

/**
 * Contains User information and his/her route and carpool information
 * @author Shashank
 *
 */
public class User {
	
	/*------Static Variables-------*/
	public static enum CarAudio {SILENCE, ROCK, CLASSICAL, POP, RAP, NEWS, SPORTS, CHATTING};
	//TODO: Add radio channels here.
	
	/*---End of Static Variables---*/
	
	private String firstName;
	private String lastName;
	
	
	/*Carpool Information*/
	private String sourceLocation; //Pickup and dropoff locations	
	private String destLocation;
	public String getDestLocation() {
		return destLocation;
	}

	public void setDestLocation(String destLocation) {
		this.destLocation = destLocation;
	}

	private ArrayList<Carpool> pools;
	
	/*Profile Information*/
	private String emailAddress;
	private String pass;
	
	private int contactNumber;
	private String aboutMe;
	private Images picture;	
	private int rewardPoints;
	private ArrayList<CarAudio> radioPrefs; 

	private boolean willingToDrive;
	
	private int departureTime[], returnTime[];
	
	
	
	
	/*Social Network Integration (Only Placeholders for now)*/
	private Object facebookProfile;
	
	public User()
	{}
	
	public User(String emailAddress, String pass)
	{
		this.emailAddress = emailAddress;
		this.pass = pass;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(String sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	

	public ArrayList<Carpool> getPools() {
		return pools;
	}

	public void setPools(ArrayList<Carpool> pools) {
		this.pools = pools;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public Images getPicture() {
		return picture;
	}

	public void setPicture(Images picture) {
		this.picture = picture;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public ArrayList<CarAudio> getRadioPrefs() {
		return radioPrefs;
	}

	public void setRadioPrefs(ArrayList<CarAudio> radioPrefs) {
		this.radioPrefs = radioPrefs;
	}

	public boolean isWillingToDrive() {
		return willingToDrive;
	}

	public void setWillingToDrive(boolean willingToDrive) {
		this.willingToDrive = willingToDrive;
	}

	public int[] getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(int[] departureTime) {
		this.departureTime = departureTime;
	}

	public int[] getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(int[] returnTime) {
		this.returnTime = returnTime;
	}

	public Object getFacebookProfile() {
		return facebookProfile;
	}

	public void setFacebookProfile(Object facebookProfile) {
		this.facebookProfile = facebookProfile;
	}
	
	

}
