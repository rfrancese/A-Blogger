package a_blogger.model;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class User {
	private int id;
	private String username;
	private String email;
	private String name;
	private String surname;
	private String laravel_session;
	
	public User(JSONObject obj,String ls){

		Log.v("hhh","hhh NEW USER");
		try {
			id = obj.getInt("id");
			username = obj.getString("username");
			email = obj.getString("email");
			name = obj.getString("name");
			surname = obj.getString("surname");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.v("hhh","hhh NEW USER LARAVEL SESSION: " + ls);
		laravel_session = ls;
	}
	
	public int getId(){
		return id;
	}
	public String getUserame(){
		return username;
	}
	public String getEmail(){
		return email;
	}
	public String getName(){
		return name;
	}
	public String getSurname(){
		return surname;
	}
	public String getLaravelSession(){
		return laravel_session;
	}
	

}
