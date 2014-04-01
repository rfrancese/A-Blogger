package a_blogger.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import a_blogger.activity.MainActivity;
import a_blogger.components.auth.Auth;
import a_blogger.components.http.HTTP;
import a_blogger.components.route.Route;
import android.util.Log;


public class Post{
	private int id;
	private String title;
	private String content;
	private int comment_count;
	private String published_at;
	private int category_id;
	
	public Post(JSONObject post){
		try {
			id = post.getInt("id");
			title = post.getString("title");
			content = post.getString("content");
			comment_count = post.getInt("comment_count");
			published_at = post.getString("published_at");
			category_id = post.getInt("category_id");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<Post> get(){
		ArrayList<Post> all = new ArrayList<Post>();
		Log.v("hhh","hhh pre SESSION"); 
		Log.v("hhh","hhh AUTH GET USER: " + Auth.getUser().getName());
		HTTP http = new HTTP("GET",Route.to("posts"));
		http.setHeader("Cookie", Auth.getUser().getLaravelSession());
		JSONArray jsonPosts = http.send().getJsonArray();
		int len = jsonPosts.length();
		for(int i=0;i<len;i++){
			JSONObject post = null;
			try {
				post = jsonPosts.getJSONObject(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			all.add(new Post(post));
		}
		return all;
	}
	
	public int getId(){ return id; }
	public String getTitle(){ return title; }
	public String getContent(){ return content; }
	public int getCommentCount(){ return comment_count; }
	public String getPublishDate(){ return published_at; }
	public int getCategoryId(){ return category_id; }
	
	@Override
	public String toString(){
		return getTitle();	
	}
}
