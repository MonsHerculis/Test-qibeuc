package skypcreative.com.cuebiq.utils;


import android.content.Context;
import android.content.SharedPreferences;


public class PreferenceManager {

    private static SharedPreferences preferences;

    public static void init(Context context) {
        if (preferences == null)
            preferences = context.getSharedPreferences(Constants.Preferences.PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
    }


    /*public static void saveNome(String nome) {
        preferences.edit().putString(Constants.Preferences.NOME, nome).apply();
    }

    public static String getNome() {
        return preferences.getString(Constants.Preferences.NOME, "");
    }*/



}
