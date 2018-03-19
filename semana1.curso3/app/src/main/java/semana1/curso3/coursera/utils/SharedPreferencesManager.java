package semana1.curso3.coursera.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferencesManager {

    private final static String TAG = SharedPreferencesManager.class.getCanonicalName();

    private static final String APP_SETTINGS = "APP_SETTINGS";

    private static final String ID_DATABASE = "ID_DATABASE";

    private static final String USER_ID = "USER_ID";

    private static final String USER = "USER";

    private SharedPreferencesManager() {}

    private static SharedPreferences getSharedPreferences(Context context) {
        try {
            return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
        }
        catch (Exception error) {
            Log.e(TAG, error.toString());
        }
        return null;
    }

    public static String getIdDatabase(Context context) {
        return getSharedPreferences(context).getString(ID_DATABASE, "");
    }

    public static void setIdDatabase(Context context, String value) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(ID_DATABASE, value);
        editor.apply();
    }

    public static String getUserId(Context context) {
        return getSharedPreferences(context).getString(USER_ID, "6953085721");
    }

    public static void setUserId(Context context, String value) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(USER_ID, value);
        editor.apply();
    }

    public static String getUser(Context context) {
        return getSharedPreferences(context).getString(USER, "coursera.danny");
    }

    public static void setUser(Context context, String value) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(USER, value);
        editor.apply();
    }

}