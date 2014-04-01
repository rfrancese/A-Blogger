package a_blogger.components.storage;


import a_blogger.activity.MainActivity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 
 * Salva dei dati in locale usando la classe SharedPreferences
 * 
 * @author gennaro
 *
 */
public class LocalStorage {
	
	private static final String PREF_NAME = "__aBlogger_PREF__";
	private static final int    PREF_MODE = Context.MODE_PRIVATE;
    
    private static final Context context = MainActivity.ma.getApplicationContext();
    
    public static SharedPreferences getSharedPrefs(){
    	return context.getSharedPreferences(PREF_NAME,PREF_MODE);
    }
    
	public static final SharedPreferences.Editor getEditor(){
		SharedPreferences prefs = context.getSharedPreferences(PREF_NAME,PREF_MODE);
        return prefs.edit();
	}
	
	/**
	 * Salva una Stringa
	 * @param key
	 * @param value
	 */
    public static void add(String key, String value) {
    	final SharedPreferences.Editor editor = getEditor();
        editor.putString(key,value);
        editor.commit();
    }
    
    /**
	 * Salva una Booelan
	 * @param key
	 * @param value
	 */
    public static void add(String key, Boolean value) {
    	final SharedPreferences.Editor editor = getEditor();
        editor.putBoolean(key,value);
        editor.commit();
    }

    /**
     * Ritorna una Stringa o un valore di defaul se
     * la chiave passata come argomento non esiste 
     * @param key
     * @param _default
     * @return
     */
    public static String get(String key, String _default) {
        return getSharedPrefs().getString(key, _default); 
    }
    
    /**
     * Ritorna un Boolean o un valore di defaul se
     * la chiave passatIntegera come argomento non esiste 
     * @param key
     * @param _default
     * @return
     */
    public static Boolean get(String key, Boolean _default) {
        return getSharedPrefs().getBoolean(key, _default); 
    }
    
    /**
     * Rimuove un dato
     * @param key
     */
    public static void remove(String key) {
        final SharedPreferences.Editor editor = getEditor();
        editor.remove(key);
        editor.commit();
    }
    
    /**
     * Rimuove tutti i dati salvati
     */
    public static void removeAll(){ 
    	 final SharedPreferences.Editor editor = getEditor();
         editor.clear();
         editor.commit();
    }
    
}
