package ridickle.co.kr.mylittlepet;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.nhn.android.naverlogin.OAuthLogin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by ridickle on 2017. 6. 11..
 */

public class MyApplication extends Application {

    public static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1;
    public static final String TAG = "MyApplication";

    private static OAuthLogin oAuthLoginInstance;
    public static Properties prop;

    public static int width = 0;
    public static int height = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        prop = new Properties();
        try {
            prop = loadPropties();
        } catch (IOException e) {
            Log.e(TAG, "Exception", e);
        }

//        Log.d(TAG, "The value in prop file is " + prop.getProperty("serverAddress"));
    }

    public static OAuthLogin getOAuthLoginInstance() {
        return oAuthLoginInstance;
    }

    public static void setOAuthLoginInstance(Context ctx) {
        oAuthLoginInstance = OAuthLogin.getInstance();
        oAuthLoginInstance.init(
                ctx,
                prop.getProperty("ClientId")
                , prop.getProperty("ClientSecret")
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
                if (result[j].charAt(0) == '#')
                    tagList.add(result[j].substring(1));
            }
        }
        return tagList;
    }

    private Properties loadPropties() throws IOException {
        String[] fileList = {"secret.properties"};
        Properties prop = new Properties();
        for (int i = fileList.length - 1; i >= 0; i--) {
            String file = fileList[i];
            try {
                InputStream fileStream = getAssets().open(file);
                prop.load(fileStream);
                fileStream.close();
            } catch (FileNotFoundException e) {
                Log.e(TAG, "Got exception " + e);
            }
        }
        return prop;
    }
}
