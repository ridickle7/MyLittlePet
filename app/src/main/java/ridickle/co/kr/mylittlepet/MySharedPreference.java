package ridickle.co.kr.mylittlepet;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ridickle on 2017. 10. 7..
 */

public class MySharedPreference {
    private static MySharedPreference instance = new MySharedPreference();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public final static String COOKIE = "cookie";

    public final static String USER_ID = "_uId";
    public final static String USER_TOKENID = "uTokenId";
    public final static String USER_NICKNAME = "uNickname";
    public final static String USER_IMAGEURL = "uImageURL";
    public final static String USER_INTRODUCE = "uIntroduce";
    public final static String USER_GENDER = "uGender";
    public final static String USER_SPECIFY = "uSpecify";
    public final static String USER_WEIGHT = "uWeight";
    public final static String USER_ADDRESS = "uAddress";
    public final static String USER_LATITUDE = "uLatitude";
    public final static String USER_LONGITUDE = "uLongitude";
    public final static String USER_FOLLOWINGLIST = "uFollowingList";
    public final static String USER_TAGLIST = "uTagList";

    public static final String CONTENT_ID = "_cId";
    public final static String EVENT_ID = "_eId";

    //The context passed into the getInstance should be application level context.
    public static synchronized MySharedPreference getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return instance;
    }

    public static MySharedPreference getDefaultInstance(){
        return getInstance(MyApplication.getDefaultContext());
    }

    // 일반 스트링 호출
    public void setData(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    // 일반 스트링 호출
    public void setData(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    // 일반 스트링 호출
    public void setData(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    // 일반 스트링 호출
    public void setData(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    // 쿠키 관리 시
    public void setData(String key, Set value) {
        editor.putStringSet(key, value);
        editor.commit();
    }

    public String getStringData(String key) {
        return sharedPreferences.getString(key, "");
    }

    public Boolean getBooleanData(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public int getIntData(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public float getFloatData(String key) {
        return sharedPreferences.getFloat(key, 0);
    }

    public Set getSetData(String key) {
        return sharedPreferences.getStringSet(key, new HashSet<String>());
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
