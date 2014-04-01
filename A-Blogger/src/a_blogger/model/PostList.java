package a_blogger.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import a_blogger.components.auth.Auth;
import a_blogger.components.http.HTTP;
import a_blogger.components.route.Route;
import android.util.Log;

public class PostList {
	private static ArrayList<Post> all_posts = null;
	private static Map<String, Post> all_posts_mapped = null;
	
	public static ArrayList<Post> all(){
		if(all_posts == null){
			all_posts = new ArrayList<Post>();
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
				all_posts.add(new Post(post));
			}
		}
		return all_posts;
	}
	
	public static Map<String, Post> allMapped(){
		if(all_posts_mapped == null){
			all_posts_mapped = new HashMap<String, Post>();
			
			ArrayList<Post> posts = all();
			
			for (Post post: posts) {
				all_posts_mapped.put("" + post.getId(), post);
			}
		}
		return all_posts_mapped;
	}
	
}
