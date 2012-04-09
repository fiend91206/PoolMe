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
import org.apache.http.message.BasicNameValuePair;
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
		userURL = "http://m.cip.gatech.edu/developer/vegeta9000/widget/api/pool_me/user";
		carpoolURL = "http://m.cip.gatech.edu/developer/vegeta9000/widget/api/pool_me/carpool";
	}
	
	public User getUser(String email){
		User u = null;
		JSONArray ja = null;
		JSONObject jd = null;
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		
		nvp.add(new BasicNameValuePair("email", email));
		ja = connect(userURL + "/getUser", nvp);
		
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
		String url = userURL + "/addUser";
		JSONArray ja = null;
		JSONObject jd = null;
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		
		nvp.add(new BasicNameValuePair("fname", u.getFirstName()));
		nvp.add(new BasicNameValuePair("lname", u.getLastName()));
		nvp.add(new BasicNameValuePair("pnum", new Long(u.getContactNumber()).toString()));
		nvp.add(new BasicNameValuePair("about", u.getAboutMe()));
		nvp.add(new BasicNameValuePair("driver", new Boolean(u.isWillingToDrive()).toString()));
		nvp.add(new BasicNameValuePair("radiopref", u.getRadioPrefs().get(0).toString()));
		nvp.add(new BasicNameValuePair("email", u.getEmailAddress()));
		nvp.add(new BasicNameValuePair("fbuname", u.getFacebookProfile().toString()));
		nvp.add(new BasicNameValuePair("startloc", u.getSourceLocation()));
		nvp.add(new BasicNameValuePair("destloc", u.getDestLocation()));
		nvp.add(new BasicNameValuePair("starttime", new Integer(u.getDepartureTime()[0]).toString()));
		nvp.add(new BasicNameValuePair("returntime", new Integer(u.getReturnTime()[0]).toString()));
		nvp.add(new BasicNameValuePair("pwd", u.getPass()));
		
		ja = connect(url, nvp);
	}
	
	public void updateFName(String fname, String email){
		String url = userURL + "/updateFName";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		
		nvp.add(new BasicNameValuePair("email", email));
		nvp.add(new BasicNameValuePair("fname", fname));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url, nvp);
	}
	
	public void updateLName(String lname, String email){
		String url = userURL + "/updateLName";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("email", email));
		
		nvp.add(new BasicNameValuePair("lname", lname));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url, nvp);
	}
	
	public void updatePNum(Long pnum, String email){
		String url = userURL + "/updatePNum";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("email", email));
		
		nvp.add(new BasicNameValuePair("pnum", pnum.toString()));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url,nvp);
	}
	
	public void updateAboutMe(String about, String email){
		String url = userURL + "/updateAboutMe";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("email", email));
		
		nvp.add(new BasicNameValuePair("about", about));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url,nvp);
	}
	
	public void updateDriver(Boolean drive, String email){
		String url = userURL + "/updateDriver";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("email", email));
		
		nvp.add(new BasicNameValuePair("driver", drive.toString()));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url,nvp);
	}
	
	public void updateRadioPref(String radiopref, String email){
		String url = userURL + "/updateRadioPref";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("email", email));
		
		nvp.add(new BasicNameValuePair("radiopref", radiopref));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url, nvp);
	}
	
	public void updateEmail(String email, String oldemail){
		String url = userURL + "/updateEmail";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("email", email));
		
		nvp.add(new BasicNameValuePair("oldemail", oldemail));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url,nvp);
	}
	
	public void updateFBName(String name, String email){
		String url = userURL + "/updateFBName";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("email", email));
		
		nvp.add(new BasicNameValuePair("fbuname", name));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url, nvp);
	}
	
	public void updateStartLoc(String startloc, String email){
		String url = userURL + "/updateStartLoc";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("email", email));
		
		nvp.add(new BasicNameValuePair("startloc", startloc));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url,nvp);
	}
	
	public void updateDestLoc(String destloc, String email){
		String url = userURL + "/updateDestLoc";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("email", email));
		
		nvp.add(new BasicNameValuePair("destloc", destloc));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url, nvp);
	}
	
	public void updateStartTime(String starttime, String email){
		String url = userURL + "/updateStartTime";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("email", email));
		
		nvp.add(new BasicNameValuePair("starttime", starttime));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url,nvp);
	}
	
	public void updateReturnTime(String returntime, String email){
		String url = userURL + "/updateReturnTime";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("email", email));
		
		nvp.add(new BasicNameValuePair("returntime", returntime));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url,nvp);
	}
	
	public void updatePwd(String pwd, String email){
		String url = userURL + "/updatePWD";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("email", email));
		
		nvp.add(new BasicNameValuePair("pwd", pwd));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url,nvp);
	}
	
	public boolean authenticate(String email, String pwd){
		boolean auth = false;
		String url = userURL + "/authenticate";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("email", email));
		
		nvp.add(new BasicNameValuePair("pwd", pwd));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url,nvp);
		
		try{
			Log.d("DatabaseTest", ja.toString());
			
			//System.out.println(ja.toString());
			jd = ja.getJSONObject(0);			
			auth = jd.getBoolean("auth");
		}catch(JSONException e1){}
		
		return auth;
	}
	
	public Carpool getPool(Integer id){
		Carpool cp = new Carpool();
		JSONArray ja = null;
		JSONObject jd = null;

		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("email", id.toString()));
		
		
		ja = connect(carpoolURL + "/getPool", nvp);
		
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
		
		String url = carpoolURL + "/getNumPools";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		
		ja = connect(url, nvp);
		
		try{
			jd = ja.getJSONObject(0);
			
			cp.setId(jd.getInt(jd.names().getString(0)) + 1);
			
		}catch(JSONException e1){}
		
		url = carpoolURL + "/addPool";
		nvp.add(new BasicNameValuePair("id", new Integer(cp.getId()).toString()));
		nvp.add(new BasicNameValuePair("owneremail", cp.getDriverEmail()));
		nvp.add(new BasicNameValuePair("memberemail", cp.getMembersEmail().get(0)));
		nvp.add(new BasicNameValuePair("capcaity", new Integer(cp.getCapacity()).toString()));
		nvp.add(new BasicNameValuePair("depttime", cp.getDeptTime()));
		nvp.add(new BasicNameValuePair("returntime", cp.getRetTime()));
		
		ja = connect(url, nvp);
	}
	
	public void updateID(Integer newid, Integer oldid){
		String url = carpoolURL + "/updateID"; 
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("id", newid.toString()));
		nvp.add(new BasicNameValuePair("oldid", oldid.toString()));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url,nvp);
	}
	
	public void updateOwnerEmail(String email, Integer id){
		String url = carpoolURL + "/updateOwnerEmail";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("id", id.toString()));
		nvp.add(new BasicNameValuePair("email", email));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url,nvp);
	}
	
	public void updateMemberEmail(String email, Integer id){
		String url = carpoolURL + "/updateMemberEmail";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("id", id.toString()));
		nvp.add(new BasicNameValuePair("email", email));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url,nvp);
	}
	
	public void updateCapacity(Integer capacity, Integer id){
		String url = carpoolURL + "/updateCapacity";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("id", id.toString()));
		nvp.add(new BasicNameValuePair("capacity", capacity.toString()));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url,nvp);
	}
	
	public void updateDeptTime(String dept, Integer id){
		String url = carpoolURL + "updateDeptTime";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("id", id.toString()));
		nvp.add(new BasicNameValuePair("depttime", dept));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url,nvp);
	}
	
	public void updateReturnTime(String ret, Integer id){
		String url = carpoolURL + "/updateDeptTime";
		ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
		nvp.add(new BasicNameValuePair("id", id.toString()));
		nvp.add(new BasicNameValuePair("returntime", ret));
		
		JSONArray ja = null;
		JSONObject jd = null;
		
		ja = connect(url,nvp);
	}
	
	private JSONArray connect(String url, ArrayList<NameValuePair> nvp){
		JSONArray jArray = null;
		String result = null;
		InputStream is = null;
		StringBuilder sb=null;
		
		
		//http post
		try{
		     HttpClient httpclient = new DefaultHttpClient();
		     HttpPost httppost = new HttpPost(url);
		     httppost.setEntity(new UrlEncodedFormEntity(nvp));
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
		
		Log.e("DBRESULTS", result);
		//parsing data
		try{
			jArray = new JSONArray(result);
		}catch(JSONException e1){
			Log.e("DBERROR", e1.toString());
		}
		
		return jArray;
	}

}
