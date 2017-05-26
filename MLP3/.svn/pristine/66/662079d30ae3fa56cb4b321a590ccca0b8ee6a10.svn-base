package yapp.co.kr.mlp.Helper;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import yapp.co.kr.mlp.MyApplication;

/**
 * Created by home on 2016-01-01.
 */
public class CustomerHelper {
    private static CustomerHelper instance;
    private static Object obj = new Object(); // 여러쓰레드에서 접근할 필요 없을 시 삭제 고려

    public static CustomerHelper getInstance(){
        synchronized (obj) {
            if(instance == null){
                instance = new CustomerHelper();
            }
            return instance;
        }
    }

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    public CustomerHelper() {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        mEditor = mPrefs.edit();
    }

    private static final String KEY_USER_ID = "key_user_id";
    private static final String KEY_USER_NICKNAME = "key_user_nickname";
    private static final String KEY_LOGIN_TYPE = "key_login_type";
    private static final String KEY_PROFILE = "key_profile";
    private static final String KEY_GCM_REG_ID = "regToken";
    private static final String KEY_DEVICE_ID = "key_device_id";
    private static final String KEY_INTRO_LONG_CLICK = "key_intro_long_click";
    private static final String KEY_IS_GET_PUSH = "key_is_get_push";

    public static final String LOGIN_TYPE_GUEST = "login_type_guest";
    public static final String LOGIN_TYPE_NORMAL = "login_type_normal";
    public static final String LOGIN_TYPE_FACEBOOK = "login_type_facebook";
    public static final String LOGIN_TYPE_KAKAO = "login_type_kakao";
    public static final String LOGIN_TYPE_GOOGLE = "login_type_google";
    public static final String LOGIN_TYPE_NAVER = "login_type_naver";

    //[START] SETTER

    public void setUserId(String userId){
        mEditor.putString(KEY_USER_ID, userId);
        mEditor.commit();
    }

    public void setUserNickname(String nickname){
        mEditor.putString(KEY_USER_NICKNAME, nickname);
        mEditor.commit();
    }


    public void setLoginType(String loginType){
        mEditor.putString(KEY_LOGIN_TYPE, loginType);
        mEditor.commit();
    }

    public void setProfile(String profile){
        mEditor.putString(KEY_PROFILE, profile);
        mEditor.commit();
    }

    public void setDeviceId(String deviceId){
        mEditor.putString(KEY_DEVICE_ID, deviceId);
        mEditor.commit();
    }

    public void setIsLongClickIntroShow(boolean isShow){
        mEditor.putBoolean(KEY_INTRO_LONG_CLICK, isShow);
        mEditor.commit();
    }

    public void setIsGetPush(boolean isGetPush){
        mEditor.putBoolean(KEY_IS_GET_PUSH, isGetPush);
        mEditor.commit();
    }

    //[END] SETTER

    //[START] GETTER

    public String getUserId(){
        return mPrefs.getString(KEY_USER_ID, "");
    }

    public String getUserNickname(){
        return mPrefs.getString(KEY_USER_NICKNAME, "");
    }

    public String getLoginType(){
        return mPrefs.getString(KEY_LOGIN_TYPE, "");
    }

    public String getProfile(){
        return mPrefs.getString(KEY_PROFILE, "");
    }

    public String getDeviceId(){
        return mPrefs.getString(KEY_DEVICE_ID, "");
    }

    public boolean getIsLongClickIntroShow(){
        return mPrefs.getBoolean(KEY_INTRO_LONG_CLICK, false);
    }

    public boolean getIsGetPush(){
        return mPrefs.getBoolean(KEY_IS_GET_PUSH, false);
    }
    //[END] GETTER

    //for gcm
    public void setRegistrationToken(String regId) {
        mEditor.putString(KEY_GCM_REG_ID, regId);
        mEditor.commit();
    }

    public String getRegistrationToken() {
        return mPrefs.getString(KEY_GCM_REG_ID, "");
    }
}