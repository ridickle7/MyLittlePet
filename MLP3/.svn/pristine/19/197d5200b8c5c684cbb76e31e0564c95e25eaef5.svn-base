package yapp.co.kr.mlp.Helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by home on 2015-10-14.
 */

/*
Application이 지속적으로 네트워크 작업을 한다면,
App의 Lifetime동안 존재할 RequestQueue 인스턴스가 있는 것이 효과적이다.
방법1. 하나의 Singleton class를 생성하여 RequestQueue 및 사용할 volley function 들을 캡슐화
방법2. Application의 Subclass를 생성하여 Application의 onCreate()메소드에서 requestQueue를 설정

두번째 방법보다는 첫번째 방법이 S/W의 모듈화에 좋으므로 여기서는 방법1 사용
    요점
        RequestQueue를 Activity context가 아닌 Application의 context를 통해 초기화
            -> 계속해서 재생성되지 않도록 한다.
*/

// RequestQueue와 ImageLoader를 제공하는 Singleton Class

public class MySingleton{
    private static MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context ctx;

    private MySingleton(Context context){
        ctx = context;
        mRequestQueue = getRequestQueue();
        mImageLoader = new ImageLoader (mRequestQueue, new ImageLoader.ImageCache(){
            private final LruCache<String, Bitmap>
                    cache = new LruCache<String, Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url){
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap){
                cache.put(url, bitmap);
            }
        });
    }

    //MySingleton 인스턴스 리턴?
    public static synchronized MySingleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    //RequestQueue 리턴
    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader(){
        return mImageLoader;
    }
}