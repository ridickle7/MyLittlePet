package ridickle.co.kr.mylittlepet;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;
import ridickle.co.kr.mylittlepet.Network.NetworkPresenter;

/**
 * Created by ridickle on 2017. 6. 11..
 */

public class MyApplication extends Application {
    public static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1;
    public static final String TAG = "MyApplication";

    private static Context context;
    private static Properties prop;
    private static TransferUtility transferUtility;
    private static Network_User user;

    public static String tempStr = "";
    public static int width = 0;
    public static int height = 0;

    public static final int CAMERALIBRARY = 0;
    public static final int CAMERACAPTURE = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        prop = new Properties();
        try {
            prop = loadPropties();
        } catch (IOException e) {
            Log.e(TAG, "Exception", e);
        }

        context = getApplicationContext();  // default context 세팅
        NetworkPresenter.getInstance();     // 네트워크 세팅
        amazonSetting();                    // 아마존 세팅

//        Log.d(TAG, "The value in prop file is " + prop.getProperty("ClientId"));
    }

    public static Context getDefaultContext() {
        return context;
    }

    public static Properties getProp() {
        return prop;
    }

    public static TransferUtility getTransferUtility() {
        return transferUtility;
    }

    public static Network_User getUser() {
        return user;
    }

    public static void setUser(Network_User user) {
        MyApplication.user = user;
    }

    public static void setSharedPreference(Network_User user){
        MySharedPreference.getInstance(MyApplication.getDefaultContext()).setData(MySharedPreference.USER_ID, user.get_id());
        MySharedPreference.getInstance(MyApplication.getDefaultContext()).setData(MySharedPreference.USER_NICKNAME, user.getuNickname());
        MySharedPreference.getInstance(MyApplication.getDefaultContext()).setData(MySharedPreference.USER_IMAGEURL, user.getuImageURL());
        MySharedPreference.getInstance(MyApplication.getDefaultContext()).setData(MySharedPreference.USER_INTRODUCE, user.getuIntroduce());
        MySharedPreference.getInstance(MyApplication.getDefaultContext()).setData(MySharedPreference.USER_GENDER, user.getuGender());
        MySharedPreference.getInstance(MyApplication.getDefaultContext()).setData(MySharedPreference.USER_SPECIFY, user.getuSpecify());
        MySharedPreference.getInstance(MyApplication.getDefaultContext()).setData(MySharedPreference.USER_WEIGHT, user.getuWeight());
        MySharedPreference.getInstance(MyApplication.getDefaultContext()).setData(MySharedPreference.USER_ADDRESS, user.getuAddress());
        MySharedPreference.getInstance(MyApplication.getDefaultContext()).setData(MySharedPreference.USER_LATITUDE, user.getuLatitude());
        MySharedPreference.getInstance(MyApplication.getDefaultContext()).setData(MySharedPreference.USER_LONGITUDE, user.getuLongitude());
        MySharedPreference.getInstance(MyApplication.getDefaultContext()).setData(MySharedPreference.USER_FOLLOWINGLIST, user.getuFollowingList().toString());
        MySharedPreference.getInstance(MyApplication.getDefaultContext()).setData(MySharedPreference.USER_TAGLIST, user.getuTagList().toString());
    }

    public static void setHeightWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);

        width = dm.widthPixels;
        height = dm.heightPixels;
    }

    public static ArrayList<String> getTagList(String text) {
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

    public static void sendImage(String fileName, File file) {
        TransferObserver observer = transferUtility.upload(
                "mylittlepet",     /* 업로드 할 버킷 이름 */
                fileName,          /* 버킷에 저장할 파일의 이름 */
                file               /* 버킷에 저장할 파일  */
        );
    }

    public static String getFormattedDate(long dateData){
        String formatted = new SimpleDateFormat("yyyy-MM-dd").format(dateData);

        return formatted;
    }

    private void amazonSetting() {
        // Initialize the Amazon Cognito credentials provider
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "ap-northeast-2:17951caa-78d5-4ce4-896c-1bb0687682b7", // Identity pool ID
                Regions.AP_NORTHEAST_2 // Region
        );

        AmazonS3 s3 = new AmazonS3Client(credentialsProvider);
        transferUtility = new TransferUtility(s3, getApplicationContext());

        // S3 버킷의 Region 이 서울일 경우 아래와 같습니다.
        s3.setRegion(Region.getRegion(Regions.AP_NORTHEAST_2));
        s3.setEndpoint("s3.ap-northeast-2.amazonaws.com");
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

    public static int ifNull(String[] array) {
        if(array == null)
            return 0;
        else
            return array.length;
    }

    public static <T> ArrayList<T> ifNull(ArrayList<T> list) {
        ArrayList<T> arr = new ArrayList<>();

        if(list == null)
            return arr;

        else if(list.size() == 1) {
            arr.add(list.get(0));
            return arr;
        }
        else
            return list;
    }

    public static <T> void setImage(Context context, final View view, T URL){
        // 이미지 처리
        if (!(URL.equals(""))) {
            Glide.with(context.getApplicationContext()).load(URL).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    view.setBackground(resource);
                }
            });
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.setBackground(context.getDrawable(R.mipmap.ic_launcher));
            }
            else{
                view.setBackground(context.getResources().getDrawable(R.mipmap.ic_launcher));
            }
        }
    }

    public static void cameraSelectorSetting(final Context context, LinearLayout view){
        view.findViewById(R.id.cameraLibrary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                ((Activity)context).startActivityForResult(intent, CAMERALIBRARY);
            }
        });

        view.findViewById(R.id.cameraCapture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                ((Activity)context).startActivityForResult(intent, CAMERALIBRARY);
            }
        });
    }
}
