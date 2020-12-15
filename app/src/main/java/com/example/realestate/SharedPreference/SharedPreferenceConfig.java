package com.example.realestate.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.realestate.R;

public class SharedPreferenceConfig {

    SharedPreferences sharedPreferences;
    Context context;
//    SharedPreferences.Editor editor;
//    String SHARED_PREF_NAME = "session";
//    String SESSION_KEY = "session_user";

 /*   public SharedPreferenceConfig(Context context) {
        this.context=context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_shared_preference),Context.MODE_PRIVATE);

    }*/

    public void login_status(boolean status) {
        //save session of user whenever user is logged in
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_status_shared_preference),status);

        editor.commit();
    }
    public boolean read_login_status(){
        boolean status=false;
        status=sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status_shared_preference),false);
        return status;
    }


    public  boolean getBooleanFromSP(String key,Context context) {
// TODO Auto-generated method stub
        SharedPreferences preferences = context.getSharedPreferences(" SHARED_PREFERENCES_NAME ", android.content.Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }//getPWDFromSP()

    public  String getEmailOfUSerFromSP(String key,Context context){
        SharedPreferences preferences = context.getSharedPreferences(" SHARED_PREFERENCES_NAME ", android.content.Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }
    public  String getPasswordOfUSerFromSP(String key,Context context){
        SharedPreferences preferences = context.getSharedPreferences(" SHARED_PREFERENCES_NAME ", android.content.Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    public  void savePawordOfUserInSP(String key, String value,Context context){
        SharedPreferences preferences = context.getSharedPreferences(" SHARED_PREFERENCES_NAME ", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =      preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }//savePWDIn
    public  void saveEmailOfUSerInSP(String key, String value,Context context){
        SharedPreferences preferences = context.getSharedPreferences(" SHARED_PREFERENCES_NAME ", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =      preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }//savePWDIn

    public  void saveBooleanInSP(String key, boolean value,Context context){
        SharedPreferences preferences = context.getSharedPreferences(" SHARED_PREFERENCES_NAME ", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =      preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }//savePWDIn

    public void  clearSharedPrefrence(Context context){
        SharedPreferences preferences = context.getSharedPreferences(" SHARED_PREFERENCES_NAME ", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =      preferences.edit();
         editor.clear();
        editor.apply();

    }



    public  String getNameOfUSerFromSP(String key,Context context){
        SharedPreferences preferences = context.getSharedPreferences(" SHARED_PREFERENCES_NAME ", android.content.Context.MODE_PRIVATE);
        return preferences.getString(key, "name");
    }


    public  void saveNameOfUSerInSP(String key, String value,Context context){
        SharedPreferences preferences = context.getSharedPreferences(" SHARED_PREFERENCES_NAME ", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =      preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }//savePWDIn





    public  String getLocationOfUSerFromSP(String key,Context context){
        SharedPreferences preferences = context.getSharedPreferences(" SHARED_PREFERENCES_LOCATION ", android.content.Context.MODE_PRIVATE);
        return preferences.getString(key, "location");
    }


    public  void saveLocationOfUSerInSP(String key, String value,Context context){
        SharedPreferences preferences = context.getSharedPreferences(" SHARED_PREFERENCES_LOCATION ", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =      preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }//savePWDIn


    public  String getidOfUSerFromSP(String key,Context context){
        SharedPreferences preferences = context.getSharedPreferences(" SHARED_PREFERENCES_id ", android.content.Context.MODE_PRIVATE);
        return preferences.getString(key, "id");
    }


    public  void saveidOfUSerInSP(String key, String value,Context context){
        SharedPreferences preferences = context.getSharedPreferences(" SHARED_PREFERENCES_id ", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =      preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }//savePWDIn





    public  String getemailOfUSerFromSP(String key,Context context){
        SharedPreferences preferences = context.getSharedPreferences(" SHARED_PREFERENCES_id ", android.content.Context.MODE_PRIVATE);
        return preferences.getString(key, "email");
    }


    public  void saveemailOfUSerInSP(String key, String value,Context context){
        SharedPreferences preferences = context.getSharedPreferences(" SHARED_PREFERENCES_id ", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =      preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }//savePWDIn
}
