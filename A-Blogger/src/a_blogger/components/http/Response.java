package a_blogger.components.http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class Response {
	private HttpResponse response;
	
	public Response(HttpResponse httpresponse){
		this.response = httpresponse;
		Log.v("hhh","hhh RESPONSE : " + getContent());
	}
	public HttpResponse getBasicResponse(){
		return response;
	}
	
	/**
	 * Ritorna lo status di una richiesta Http
	 * @return Integer
	 */
	public Integer getStatus(){
		return this.response.getStatusLine().getStatusCode();
	}
	
	/**
	 * Ritorna il risultato di un richiesta Http in formato String
	 * @return String 
	 */
	public String getContent(){
		try {
			return EntityUtils.toString(this.response.getEntity());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Ritorna il risultato di un richiesta HTTP in formato JSON
	 * @return JSONObject
	 */
	public JSONObject getJson(){
		try {
			return new JSONObject(getContent());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Ritorna il risultato di una richista HTTP in formato JSON
	 * @return
	 */
	public JSONArray getJsonArray() {
		try {
			return new JSONArray(getContent());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
