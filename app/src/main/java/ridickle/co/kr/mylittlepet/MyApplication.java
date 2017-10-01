package ridickle.co.kr.mylittlepet;

import android.app.Application;
import android.content.Context;

import com.nhn.android.naverlogin.OAuthLogin;

/**
 * Created by ridickle on 2017. 6. 11..
 */

public class MyApplication extends Application {

    public static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION= 1;

    private static OAuthLogin oAuthLoginInstance;
    public static final String ClientId = "08E45VL55DPdit9Rr9zh";
    public static final String ClientSecret = "7OH5D55MYY";
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static OAuthLogin getOAuthLoginInstance() {
        return oAuthLoginInstance;
    }

    public static void setOAuthLoginInstance(Context ctx) {
        oAuthLoginInstance = OAuthLogin.getInstance();
        oAuthLoginInstance.init(
                ctx,
                ClientId
                ,ClientSecret
                ,"MyLittlePet"
                //,OAUTH_CALLBACK_INTENT
                // SDK 4.1.4 버전부터는 OAUTH_CALLBACK_INTENT변수를 사용하지 않습니다.
        );
    }
}
