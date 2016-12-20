package org.apache.cordova.cookiesync;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import android.webkit.CookieSyncManager;
import android.webkit.CookieManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.content.Context;

/**
    * This class echoes a string called from JavaScript.
    */
public class CookieSync extends CordovaPlugin  {

	private static final String PREFS_NAME = "COOKIES";
	private static final String COOKIE_DOMAIN = "st-myeni.be.eni.com";
	private Context context;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		context = this.cordova.getActivity().getApplicationContext(); 
        if (action.equals("sync")) {
            this.sync(callbackContext);
			return true;
        }
		else if (action.equals("persistCookies")) {
            this.persistCookies(callbackContext);
			return true;
        }
		else if (action.equals("restoreCookies")) {
            this.restoreCookies(callbackContext);
			return true;
        }
        return false;
    }

	/*
	 * Get cookies from cookiemanager and persist them in the Shared preferences. 
	 */
	private void persistCookies(CallbackContext callbackContext){
		try{
			
			SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
			String cookie = CookieManager.getInstance().getCookie(COOKIE_DOMAIN);
			SharedPreferences.Editor editor = sharedPref.edit();
			editor.putString(COOKIE_DOMAIN, cookie);
			editor.commit();
			callbackContext.success();
		}catch(Exception e){
			callbackContext.error("failed to persist cookies");
		}
	}

	/*
	 * get cookies from the Shared preferences and restore them using the cookiemanager. 
	 */
	private void restoreCookies(CallbackContext callbackContext){
		try{
			SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
			String cookie = sharedPref.getString(COOKIE_DOMAIN, "");
			CookieManager.getInstance().setCookie(COOKIE_DOMAIN, cookie);
			CookieSyncManager.getInstance().sync();
			callbackContext.success();
		}catch(Exception e){
			callbackContext.error("failed to restore cookies");
		}
	}

    private void sync(CallbackContext callbackContext) {
        //force the cookies to sync immediatly.
		CookieSyncManager.getInstance().sync();
		callbackContext.success();
    }
}