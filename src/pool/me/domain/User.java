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
	private Checkpoint sourceLocation, destLocation; //Pickup and dropoff locations	
	private ArrayList<Carpool> pools;
	
	/*Profile Information*/
	private String emailAddress;
	private int contactNumber;
	
	private String aboutMe;
	private Images picture;	
	private int rewardPoints;
	private ArrayList<CarAudio> radioPrefs; 

	private boolean willingToDrive;
	
	
	
	
	/*Social Network Integration (Only Placeholders for now)*/
	private Object facebookProfile;
	private Object linkedinProfile;

}
