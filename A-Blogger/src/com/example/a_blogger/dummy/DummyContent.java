package com.example.a_blogger.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a_blogger.model.Post;


/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

	/**
	 * An array of sample (dummy) items.
	 */
	public static ArrayList<Post> ITEMS = Post.get();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, Post> ITEM_MAP = new HashMap<String, Post>();

	static {
		init();
	};
	
	private static void init() {
		ITEMS = Post.get();
		for (Post post: ITEMS) {
			ITEM_MAP.put("" + post.getId(), post);
		}
	}
	
}
