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

    public SharedPreferenceConfig(Context context) {
        this.context=context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_shared_preference),Context.MODE_PRIVATE);

    }

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

}
