package yapp.co.kr.mlp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.facebook.FacebookSdk;

/**
 * Created by home on 2016-01-01.
 */
public class MyApplication extends Application {
    private Activity currentTopActivity;

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        //save device id into shared preference
        //PropertyManager.getInstance().setDeviceId(Secure.getString(getContentResolver(), Secure.ANDROID_ID));

        //for facebook
        FacebookSdk.sdkInitialize(getApplicationContext());

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                currentTopActivity = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {
                if (currentTopActivity != null && currentTopActivity == activity) {
                    currentTopActivity = null;
                }
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
    public static Context getContext() {
        return mContext;
    }

}