package ridickle.co.kr.mylittlepet;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.nhn.android.naverlogin.OAuthLogin;

import java.util.ArrayList;
import java.util.stream.Stream;

import static android.R.id.input;

/**
 * Created by ridickle on 2017. 6. 11..
 */

public class MyApplication extends Application {

    public static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1;

    private static OAuthLogin oAuthLoginInstance;
    public static final String ClientId = "08E45VL55DPdit9Rr9zh";
    public static final String ClientSecret = "7OH5D55MYY";

    public static int width = 0;
    public static int height = 0;

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
                , ClientSecret
                , "MyLittlePet"
                //,OAUTH_CALLBACK_INTENT
                // SDK 4.1.4 버전부터는 OAUTH_CALLBACK_INTENT변수를 사용하지 않습니다.
        );
    }

    public static void setHeightWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);

        width = dm.widthPixels;
        height = dm.heightPixels;
    }

    public static ArrayList<String> getTagList(String text) {
        int index = text.indexOf("#");
        ArrayList<String> tagList = new ArrayList<>();

        String[] values = text.split("\n");
        for (int i = 0; i < values.length; i++) {
            String[] result = values[i].split("\\s+");
            for (int j = 0; j < result.length; j++) {
                if(result[j].charAt(0) == '#')
                    tagList.add(result[j].substring(1));
            }
        }
        return tagList;
    }
}
