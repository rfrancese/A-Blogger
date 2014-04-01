package a_blogger.model;

import org.json.JSONException;
import org.json.JSONObject;


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
