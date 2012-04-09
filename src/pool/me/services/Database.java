package pool.me.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pool.me.domain.Carpool;
import pool.me.domain.User;
import pool.me.domain.User.CarAudio;

import android.util.Log;


/**
 * This class provides methods to connect to the database
 * for information on the users, car pools, etc
 * @author Vegeta9000
 *
 */
public class Database {
	private String userURL, carpoolURL;
	
	public Database(){
		userURL = "http://m.cip.gatech.edu/developer/vegeta9000/widget/pool_me/user.php";
		carpoolURL = "http://m.cip.gatech.edu/developer/vegeta9000/widget/pool_me/carpool.php";
	}
	
	public User getUser(String email){
		User u = null;
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(userURL + "?func=getUser&email=" + email);
		
		try{
			jd = ja.getJSONObject(0);
			
			u = new User(jd.getString("Email"), jd.getString("Password"));
			u.setAboutMe(jd.getString("AboutMe"));
			u.setContactNumber(jd.getInt("PhoneNumber"));
			int[] depttime = {jd.getInt("DepartureTime")};
			u.setDepartureTime(depttime);
			u.setFirstName(jd.getString("FirstName"));
			u.setLastName(jd.getString("LastName"));
			
			//Check what radio pref enum to use based on info from database.
			ArrayList<CarAudio> radioPrefs = new ArrayList<CarAudio>();
			String rp = jd.getString("RadioPrefs");
			if (rp.compareToIgnoreCase("SILENCE") == 0 ){
				radioPrefs.add(User.CarAudio.SILENCE);
			}else if (rp.compareToIgnoreCase("ROCK") == 0){
				radioPrefs.add(User.CarAudio.ROCK);
			}else if (rp.compareToIgnoreCase("CLASSICAL") == 0){
				radioPrefs.add(User.CarAudio.CLASSICAL);
			}else if (rp.compareToIgnoreCase("POP") == 0){
				radioPrefs.add(User.CarAudio.POP);
			}else if (rp.compareToIgnoreCase("RAP") == 0){
				radioPrefs.add(User.CarAudio.RAP);
			}else if (rp.compareToIgnoreCase("NEWS") == 0){
				radioPrefs.add(User.CarAudio.NEWS);
			}else if (rp.compareToIgnoreCase("SPORTS") == 0){
				radioPrefs.add(User.CarAudio.SPORTS);
			}else if (rp.compareToIgnoreCase("CHATTING") == 0){
				radioPrefs.add(User.CarAudio.CHATTING);
			}
			u.setRadioPrefs(radioPrefs);
			
			int[] rettime = {jd.getInt("ReturnTime")};
			u.setReturnTime(rettime);
			
			u.setSourceLocation(jd.getString("SourceLocation"));
			u.setDestLocation(jd.getString("DestLocation"));
			u.setWillingToDrive(jd.getBoolean("Driver"));
		}catch(JSONException e1){}
		
		return u;
	}
	
	public void addUser(User u){
		String url = userURL + "?func=addUser";
		JSONArray ja = null;
		JSONObject jd = null;
		
		url += "&fname=" + u.getFirstName();
		url += "&lname=" + u.getLastName();
		url += "&pnum=" + u.getContactNumber();
		url += "&about=" + u.getAboutMe();
		url += "&driver=" + u.isWillingToDrive();
		url += "&radiopref=" + u.getRadioPrefs().get(0).toString();
		url += "&email=" + u.getEmailAddress();
		url += "&fbuname=" + u.getFacebookProfile().toString();
		url += "&startloc=" + u.getSourceLocation();
		url += "&destloc=" + u.getDestLocation();
		url += "&starttime=" + u.getDepartureTime()[0];
		url += "&returntime=" + u.getReturnTime()[0];
		url += "&pwd=" + u.getPass();
		
		ja = connect(url);
	}
	
	public void updateFName(String fname, String email){
		String url = userURL + "?func=updateFName&email=" + email;
		url += "&fname=" + fname;
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updateLName(String lname, String email){
		String url = userURL + "?func=updateLName&email=" + email;
		url += "&lname=" + lname;
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updatePNum(int pnum, String email){
		String url = userURL + "?func=updatePNum&email=" + email;
		url += "&pnum=" + pnum;
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updateAboutMe(String about, String email){
		String url = userURL + "?func=updateAboutMe&email=" + email;
		url += "&about=" + about;
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updateDriver(Boolean drive, String email){
		String url = userURL + "?func=updateDriver&email=" + email;
		url += "&driver=" + drive.toString();
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updateRadioPref(String radiopref, String email){
		String url = userURL + "?func=updateRadioPref&email=" + email;
		url += "&radiopref=" + radiopref;
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updateEmail(String email, String oldemail){
		String url = userURL + "?func=updateEmail&email=" + email;
		url += "&oldemail=" + oldemail;
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updateFBName(String name, String email){
		String url = userURL + "?func=updateFBName&email=" + email;
		url += "&fbuname=" + name;
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updateStartLoc(String startloc, String email){
		String url = userURL + "?func=updateStartLoc&email=" + email;
		url += "&startloc=" + startloc;
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updateDestLoc(String destloc, String email){
		String url = userURL + "?func=updateDestLoc&email=" + email;
		url += "&destloc=" + destloc;
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updateStartTime(String starttime, String email){
		String url = userURL + "?func=updateStartTime&email=" + email;
		url += "&starttime=" + starttime;
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updateReturnTime(String returntime, String email){
		String url = userURL + "?func=updateReturnTime&email=" + email;
		url += "&returntime=" + returntime;
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updatePwd(String pwd, String email){
		String url = userURL + "?func=updatePWD&email=" + email;
		url += "&pwd=" + pwd;
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public boolean authenticate(String email, String pwd){
		boolean auth = false;
		String url = userURL + "?func=authenticate&email=" + email;
		url += "&pwd=" + pwd;
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
		
		try{
			Log.d("DatabaseTest", ja.toString());
			
			//System.out.println(ja.toString());
			jd = ja.getJSONObject(0);			
			auth = jd.getBoolean("auth");
		}catch(JSONException e1){}
		
		return auth;
	}
	
	public Carpool getPool(int id){
		Carpool cp = new Carpool();
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(carpoolURL + "?func=getPoolr&id=" + id);
		
		try{
			jd = ja.getJSONObject(0);
			
			cp.setCapacity(jd.getInt("capacity"));
			cp.setDriverEmail(jd.getString("owneremail"));
			
			ArrayList<String> memEmail = new ArrayList<String>();
			memEmail.add(jd.getString("memberemail"));
			cp.setMembersEmail(memEmail);
			
			cp.setDeptTime(jd.getString("depttime"));
			cp.setRetTime(jd.getString("returntime"));
			
//			cp.setRoute(new Route());
		}catch(JSONException e1){}
		
		return cp;
	}
	
	public void addPool(Carpool cp){
		JSONArray ja = null;
		JSONObject jd = null;
		
		String url = carpoolURL + "?func=getNumPools";
		ja = connect(url);
		
		try{
			jd = ja.getJSONObject(0);
			
			cp.setId(jd.getInt(jd.names().getString(0)) + 1);
			
		}catch(JSONException e1){}
		
		url = carpoolURL + "?func=addPool&id=" + cp.getId();
		url += "&owneremail=" + cp.getDriverEmail();
		url += "&memberemail=" + cp.getMembersEmail().get(0);
		url += "&capcaity=" + cp.getCapacity();
		url += "&depttime=" + cp.getDeptTime();
		url += "&returntime=" + cp.getRetTime();
		
		ja = connect(url);
	}
	
	public void updateID(int newid, int oldid){
		String url = carpoolURL + "?func=updateID&id=" + newid + "&oldid=" + oldid; 
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updateOwnerEmail(String email, int id){
		String url = carpoolURL + "?func=updateOwnEmail&id=" + id + "&owneremail=" + email;
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updateMemberEmail(String email, int id){
		String url = carpoolURL + "?func=updateMemEmail&id=" + id + "&memberemail=" + email;
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updateCapacity(int capacity, int id){
		String url = carpoolURL + "?func=updateCapacity&id=" + id + "&capacity=" + capacity;
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updateDeptTime(String dept, int id){
		String url = carpoolURL + "?func=updateDeptTime&id=" + id + "&depttime=" + dept;
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	public void updateReturnTime(String ret, int id){
		String url = carpoolURL + "?func=updateDeptTime&id=" + id + "&returntime=" + ret;
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url);
	}
	
	private JSONArray connect(String url){
		JSONArray jArray = null;
		String result = null;
		InputStream is = null;
		StringBuilder sb=null;
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		
		
		//http post
		try{
		     HttpClient httpclient = new DefaultHttpClient();
		     HttpPost httppost = new HttpPost(url);
		     httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		     HttpResponse response = httpclient.execute(httppost);
		     HttpEntity entity = response.getEntity();
		     is = entity.getContent();
		}catch(Exception e){
		     Log.e("log_tag", "Error in http connection"+e.toString());
		}
		
		//convert response to string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		    sb = new StringBuilder();
		    sb.append(reader.readLine() + "\n");

		    String line="0";
		    while ((line = reader.readLine()) != null) {
		    	sb.append(line + "\n");
		    }
		        is.close();
		        result=sb.toString();
		}catch(Exception e){
		        Log.e("log_tag", "Error converting result "+e.toString());
		}
		
		//parsing data
		try{
			jArray = new JSONArray(result);
		}catch(JSONException e1){}
		
		return jArray;
	}

}
