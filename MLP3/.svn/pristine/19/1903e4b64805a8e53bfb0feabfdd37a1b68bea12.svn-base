package yapp.co.kr.mlp.Helper;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by home on 2015-12-19.
 */
public class NetworkHelper {
    private static NetworkHelper instance;
    private static Object obj = new Object();
    Handler mHandler = new Handler(Looper.getMainLooper()); //TEST를 위한 핸들러

    public static NetworkHelper getInstance(){
        synchronized (obj) {
            if(instance == null){
                instance = new NetworkHelper();
            }
            return instance;
        }
    }


    public void loginFacebookToken(Context context, String accessToken, final OnResultListener<String> listener){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onSuccess("OK");
            }
        }, 1000);
    }



    public interface OnResultListener<T>{
        public void onSuccess(T result);
        public void onFail(int code);
    }

    public void login(){
        /*mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (id.equals("ty") && password.equals("1234")) {
                    listener.onSuccess("OK"); //자동로그인 성공
                } else {
                    listener.onFail(0); // 0 : id/pass 불일치
                }
            }
        }, 1000);*/

    }

}
