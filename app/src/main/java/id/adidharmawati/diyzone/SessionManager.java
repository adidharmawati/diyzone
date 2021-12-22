package id.adidharmawati.diyzone;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedEditor;
    String SHARED_PREF_NAME = "session";

    public SessionManager(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        sharedEditor = sharedPreferences.edit();
        sharedEditor.apply();
    }

    public void saveSession(int id, String username, String email, String gender, String age){
        sharedEditor.putInt("id", id);
        sharedEditor.putString("nama", username);
        sharedEditor.putString("email", email);
        sharedEditor.putString("gender", gender);
        sharedEditor.putString("age", age);
        sharedEditor.putBoolean("login", true);
        sharedEditor.apply();
    }

    public void removeSession(){
        sharedEditor.clear();
        sharedEditor.apply();
    }

    public Boolean statusLogin(){
        return sharedPreferences.getBoolean("login", false);
    }

    public String getNama(){
        return sharedPreferences.getString("nama", "");
    }
    public String getEmail(){ return sharedPreferences.getString("email", ""); }
    public String getGender(){
        return sharedPreferences.getString("gender", "");
    }
    public String getAge(){
        return sharedPreferences.getString("age", "");
    }

    public int getId(){
        return sharedPreferences.getInt("id", 0);
    }
}
