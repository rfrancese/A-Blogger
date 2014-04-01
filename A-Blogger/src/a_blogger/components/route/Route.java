package a_blogger.components.route;

import a_blogger.components.config.Config;

public class Route {
	
	private static String getBase(){
		return Config.getApiUrl();
	}
	
	public static String to(String path){
		return getBase() + (path.charAt(0) == '/' ? path : '/' + path);	
	}
	
}
