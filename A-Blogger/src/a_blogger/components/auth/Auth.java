package a_blogger.components.auth;


import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import a_blogger.activity.MainActivity;
import a_blogger.components.http.HTTP;
import a_blogger.components.http.Response;
import a_blogger.components.route.Route;
import a_blogger.components.storage.LocalStorage;
import a_blogger.model.User;
import android.util.Log;

/**
 * Auth class
 * 
 * @author gennaro
 * @version 0.0.1
 *   
 * Gestisce l'autenticazione online
 * dell'utente che sta utilizzando l'app
 *
 */
public class Auth {
	
	/**
	 * Effettua il login online dell'utente
	 * 
	 * @param email
	 * @param password 
	 * @return (Boolean) true se il login va a buon fine, altrimenti false.
	 */
	public static Boolean attempt(String email, String password){
		//Apre una connessione http
		Log.v("hhh","hhh attempt");
		HTTP http = new HTTP("POST",Route.to("auth/login"));
		http.setHeader("Accept", "application/json");
		http.setData("email",email);
		http.setData("password",password);
		Response response = http.send();
		JSONObject res = null;
		if(response.getStatus() == 200) {  //Se la richiesta è andata a buon fine
			Log.v("hhh","hhh attempt status OK");
			
			res = response.getJson(); 
			try {

				Log.v("hhh","hhh attempt SAVING USER");
				String jsonUserData = res.getJSONObject("user").toString(); //Estraggo i dati dell'utente
				Log.v("hhh","hhh attempt SAVING USER : " + jsonUserData);
				LocalStorage.add("User", jsonUserData); //Salvo i dati dell'utente sul dispositivo
				LocalStorage.add("logged", true);
				MainActivity.user = new User(new JSONObject(jsonUserData),getLaravelCookie(response)); //Setto l'utente nella MainActivity
				return true;
			} catch (JSONException e) { }
		}
		return false;
	}
	
	/**
	 * Controllo se l'utente è loggato al sistema
	 * @return
	 */
	public static boolean check(){
		return LocalStorage.get("logged", false);
	}
	
	/**
	 * Effettua il logout
	 */
	public static void logout(){
		LocalStorage.removeAll();
	}
	
	/**
	 * Setta l'utente nella MainActivity e riapro la connessione sul server remoto
	 * @return
	 */
	public static User getUser(){
		if(MainActivity.user == null){

			Log.v("hhh","hhh MainActivity.user IS NULL!!");
			if(check()){ //Controller se l'utente si è già loggato
				//Riapro la sessione sul server rieffettuando il login
				attempt(LocalStorage.get("email", (String) null),LocalStorage.get("password", (String) null));
			}
		}
		Log.v("hhh","hhh MainActivity.user ok");
		return MainActivity.user;
	}
	
	/**
	 * Estraggo il cookie contenente l'ID della sessione del client
	 * @param response
	 * @return
	 */
	public static String getLaravelCookie(Response response){
		Header[] cookies = response.getBasicResponse().getHeaders("Set-Cookie");
		for (Header h : cookies) {
			if(h.getValue().contains("laravel_session")){
				return h.getValue().toString(); 
			}
		}
		return null;
	}
}
