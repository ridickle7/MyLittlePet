package ridickle.co.kr.mylittlepet;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ridickle on 2017. 10. 7..
 */

public class MySharedPreference {
    private static MySharedPreference instance = new MySharedPreference();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;


    private final String USER_ID = "_uId";
    private final String USER_TOKENID = "uTokenId";
    private final String USER_NICKNAME = "uNickname";
    private final String USER_IMAGEURL = "uImageURL";
    private final String USER_INTRODUCE = "uIntroduce";
    private final String USER_GENDER = "uGender";
    private final String USER_SPECIFY = "uSpecify";
    private final String USER_WEIGHT = "uWeight";
    private final String USER_ADDRESS = "uAddress";
    private final String USER_LATITUDE = "uLatitude";
    private final String USER_LONGITUDE = "uLongitude";
    private final String USER_FOLLOWINGLIST = "uFollowingList";
    private final String USER_TAGLIST = "uTagList";

    //The context passed into the getInstance should be application level context.
    public static synchronized MySharedPreference getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return instance;
    }

    public void setData(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getData(String key, String value) {
        return sharedPreferences.getString(key, "");
    }

    public void delData(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void clearAll() {
        editor.clear();
        editor.commit();
    }
}
