package a_blogger.components.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;


import a_blogger.activity.MainActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;



public class HTTP {
	private HttpClient httpClient;
	private HttpContext localContext;
	private HttpGet requestGet;
	private HttpPost requestPost;
	private CookieStore cookieStore;
	private String method;
	private ArrayList<NameValuePair> data;

	/**
	 * Apre una connessione http
	 * @param method è il metodo della richiesta
	 * @param url è l'url a cui si effettua la richiesta
	 */
	public HTTP(String method, String url){
		httpClient = new DefaultHttpClient();
		localContext = new BasicHttpContext();
	    this.method = method;
	    localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		
	    if(method == "POST"){
    		requestPost = new HttpPost(url);
    		data = new ArrayList<NameValuePair>();
	    } else{
	    	requestGet = new HttpGet(url);
	    }
	}
	
	/**
	 * Setta un' header di una richiesta
	 * @param key Nome dell'header
	 * @param value Valore dell'header
	 * 
	 * Es: setHeader("Accept","application/json")
	 */
	public void setHeader(String key, String value){
		if(method == "POST"){
			requestPost.addHeader(key,value);
		} else {
			requestGet.addHeader(key,value);
		}
	}
	
	/**
	 * Setta un dato da inviare durante una richiesta di tipo POST
	 * @param key
	 * @param val
	 */
	public void setData(String key, String val){
		if(method == "POST"){
			data.add(new BasicNameValuePair(key,val));
		}
	}
	
	/**
	 * Invia la richiesta
	 * @param params
	 * @return Response
	 */
	public Response send() {
		Log.v("hhh","hhh send");
		HttpResponse response = null;
	    try {
			if(method == "POST"){
				if(data.size() > 0){
					try {
						requestPost.setEntity(new UrlEncodedFormEntity(data));
					} 
			        catch (UnsupportedEncodingException e) { }
				}
				response = httpClient.execute(requestPost,localContext);
			} else {
				response = httpClient.execute(requestGet,localContext);
		    }
		} 
		catch (IOException e) { 
			Log.v("hhh","hhh IOException");
			return null; 
		}
	    return new Response(response);
	}
	
	
	
	/**
	 * Controlla se l'utente è connesso ad internet 
	 * @param context getBaseContext()
	 * @return Boolean 
	 */
	public static boolean isOnline() {
	    try{
			ConnectivityManager cm =
		        (ConnectivityManager) MainActivity.ma.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo netInfo = cm.getActiveNetworkInfo();
		    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
		        return true;
		    }
	    } 
	    catch(Exception e) { e.printStackTrace(); }
	    return false;
    }
	
}
