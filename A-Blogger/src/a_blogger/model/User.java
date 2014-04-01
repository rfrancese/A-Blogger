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
	private ArrayList<Post> posts = null;
	
	public User(JSONObject obj,String ls){

		Log.v("hhh","hhh NEW USER");
		try {
			id = obj.getInt("id");
			username = obj.getString("username");
			username = obj.getString("email");
			username = obj.getString("name");
			username = obj.getString("surname");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.v("hhh","hhh NEW USER LARAVEL SESSION: " + ls);
		laravel_session = ls;
	}
	
	public ArrayList<Post> Posts(){
		if(posts == null){
			posts = Post.get();
		}
		return posts;
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
