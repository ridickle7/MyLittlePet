package yapp.co.kr.mlp.component;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import yapp.co.kr.mlp.Helper.CustomerHelper;
import yapp.co.kr.mlp.Helper.MySingleton;
import yapp.co.kr.mlp.Helper.NetworkHelper;
import yapp.co.kr.mlp.R;

public class LoginActivity extends ActionBarActivity {

    public static int startCount = 0;
    LoginManager mLoginManager;
    CustomerHelper mCustomHelper;

    String id, flag;
    private TextView info;
    //private LoginButton loginButton;
    private OAuthLoginButton mOAuthLoginButton;

    private OAuthLogin mOAuthLoginModule;
    private ImageButton login, loginManager_facebook;

    private String OAUTH_CLIENT_ID = "JQf4sUkhKOfDI6qSCtQd";
    private String OAUTH_CLIENT_SECRET = "6jbaKxkkGH";
    private String OAUTH_CLIENT_NAME = "MLP";

    private CallbackManager callbackManager;
    RequestQueue mQueue2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("에러");
        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);

        mCustomHelper = CustomerHelper.getInstance();

        info = (TextView) findViewById(R.id.info);
        login = (ImageButton) findViewById(R.id.login);
        //loginButton = (LoginButton) findViewById(R.id.login_button);
        //loginButton.setBackgroundResource(R.mipmap.login_facebook);
        //loginButton.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        //loginButton.setText("");

        mOAuthLoginButton = (OAuthLoginButton) findViewById(R.id.buttonOAuthLoginImg);
        mOAuthLoginButton.setBackgroundResource(R.mipmap.login_naver);

            facebook_login();
            naver_login();
    }

    public void facebook_login() {
        mLoginManager = LoginManager.getInstance();
        AccessTokenTracker mFbTokenTracker;

        mFbTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                final AccessToken token = AccessToken.getCurrentAccessToken();
                if(token != null){
                    NetworkHelper.getInstance().loginFacebookToken(getApplicationContext(), token.getToken(), new NetworkHelper.OnResultListener<String>() {
                        @Override
                        public void onSuccess(String result) {
                            if (result.equals("OK")) {
                                mCustomHelper.setUserId(token.getUserId());
                                mCustomHelper.setLoginType(mCustomHelper.LOGIN_TYPE_FACEBOOK);

                                //임시로 넣은 이미지
                                Bitmap bigPictureBitmap1 = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.boast_ex1);
                                Bitmap image_bitmap1 = Bitmap.createScaledBitmap(bigPictureBitmap1, 80, 80, false);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                image_bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] byteArray = stream.toByteArray();

                                String saveThis = Base64.encodeToString(byteArray, Base64.DEFAULT);
                                mCustomHelper.setProfile(saveThis);
                                goActivity(MainActivity.class);
                            } else if (result.equals("NOT_REGISTERED")) { //닉네임을 설정하지 않은사람이거나, 처음 가입한 사람
                                //((LoginActivity)getActivity()).pushOnNickSetFragment(token);
                                goActivity(EditActivity.class);
                            }
                        }

                        @Override
                        public void onFail(int code) {
                            //                           Toast.makeText(getContext(), "흑 페북 연동 실패 ㅠ", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        };

        loginManager_facebook = (ImageButton) findViewById(R.id.loginManager_facebook);
        loginManager_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그인 시도
                mLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        //로그인 성공 -> tracker 불림
                        //Toast.makeText(getApplicationContext(), "페이스북 로그인으로 입장합니다.", Toast.LENGTH_SHORT).show();
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,
                                            GraphResponse response) {
                                        // Application code
                                        //Log.v("LoginActivity", object.toString());    jsonobject 내용물??
                                        Log.d("LoginActivity", response.toString());  //object 내용물

                                        try {
                                            id = object.getString("id");
                                            //info.setText("id : " + id);
                                            //Log.v("LoginActivity", "id : " + id + " 성별 : " + gender + "");
                                            if (id != null) {
                                                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                // 여기서 해당 토큰이 있는지 확인하는 코드 필요



                                                Intent intent2 = new Intent(getApplicationContext(), EditActivity.class);

                                                //shraedpreference에 데이터를 넣습니다.
                                                mCustomHelper.setUserId(id);
                                                mCustomHelper.setLoginType("facebook");

                                                intent2.putExtra("user_id", mCustomHelper.getUserId());
                                                intent2.putExtra("flag", mCustomHelper.getLoginType());

                                                Log.d("LoginActivity", "id : " + id);
                                                //loginButton.setBackgroundResource(R.mipmap.logout_facebook);

                                                //만약 x서버 DB를 검색해서 해당과 동일한 id가 가입을 했을경우 -> 걍 넘어가!!!
                                                //startActivity(intent);

                                                //만약 아닐경우 -> 먼저 마이페이지 작성란으로 넘어간다.
                                                startActivity(intent2);

                                                finish();
                                            }
                                        } catch (JSONException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id");
                        Log.d("LoginActivity", parameters.toString());
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), "페이스북 로그인으로 입장합니다.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(getApplicationContext(), "" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                mLoginManager.logInWithPublishPermissions(LoginActivity.this, null);
            }
        });
//        loginButton.setReadPermissions("public_profile", "user_friends");
//
///*        // Add code to print out the key hash
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "yapp.co.kr.mlp",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {}
//        catch (NoSuchAlgorithmException e) { }*/
//
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
///* 값 확인             info.setText(
//                        "User ID: "
//                                + loginResult.getAccessToken().getUserId()
//                                + "\n" +
//                                "Auth Token: "
//                                + loginResult.getAccessToken().getToken()
//                                + "\n"
//                );*/
//
//                pref.remove();  //초기화
//                GraphRequest request = GraphRequest.newMeRequest(
//                        loginResult.getAccessToken(),
//                        new GraphRequest.GraphJSONObjectCallback() {
//                            @Override
//                            public void onCompleted(
//                                    JSONObject object,
//                                    GraphResponse response) {
//                                // Application code
//                                //Log.v("LoginActivity", object.toString());    jsonobject 내용물??
//                                Log.d("LoginActivity", response.toString());  //object 내용물
//
//                                try {
//                                    id = object.getString("id");
//                                    info.setText("id : " + id);
//                                    //Log.v("LoginActivity", "id : " + id + " 성별 : " + gender + "");
//                                    if (id != null) {
//                                        //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                        Intent intent2 = new Intent(getApplicationContext(), EditActivity.class);
//                                        //intent.putExtra("user_id", id);
//                                        intent2.putExtra("user_id", id);
//
//                                        Log.d("LoginActivity", "id : " + id);
//                                        loginButton.setBackgroundResource(R.mipmap.logout_facebook);
//
//                                        //shraedpreference에 데이터를 넣습니다.
//                                        pref.put(RbPreference.PREF_INTRO_USER_AGREEMENT1, id);
//
//                                        //만약 x서버 DB를 검색해서 해당과 동일한 id가 가입을 했을경우 -> 걍 넘어가!!!
//                                        //startActivity(intent);
//
//                                        //만약 아닐경우 -> 먼저 마이페이지 작성란으로 넘어간다.
//                                        startActivity(intent2);
//
//                                        finish();
//                                    }
//                                } catch (JSONException e) {
//                                    // TODO Auto-generated catch block
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id");
//                Log.d("LoginActivity", parameters.toString());
//                request.setParameters(parameters);
//                request.executeAsync();
//            }
//
//            @Override
//            public void onCancel() {
//                info.setText("Login attempt canceled.");
//            }
//
//            @Override
//            public void onError(FacebookException e) {
//                info.setText("Login attempt failed.");
//            }
//        });
//
//        accessTokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//                // App code
//                final AccessToken token = AccessToken.getCurrentAccessToken();
//                Log.d("DDD", "Current Token : " + currentAccessToken);
//
//                NetworkHelper.getInstance().loginFacebookToken(getApplicationContext(), token.getToken(), new NetworkHelper.OnResultListener<String>(){
//                    @Override
//                    public void onSuccess(String result) {
//                        if(result.equals("OK")){
//                            RbPreference.getInstance(getApplicationContext()).put(RbPreference.PREF_INTRO_USER_AGREEMENT1, id);
//                            RbPreference.getInstance(getApplicationContext()).putType(RbPreference.PREF_MAIN_VALUE, "facebook");
//                            goMainActivity();
//                        } else if(result.equals("NOT_REGISTERED")) { //닉네임을 설정하지 않은사람이거나, 처음 가입한 사람
//                            RbPreference.getInstance(getApplicationContext()).put(RbPreference.PREF_INTRO_USER_AGREEMENT1, "none");
//                            RbPreference.getInstance(getApplicationContext()).putType(RbPreference.PREF_MAIN_VALUE, "none");
//                        }
//                    }
//
//                    @Override
//                    public void onFail(int code) {
//                        //                           Toast.makeText(getContext(), "흑 페북 연동 실패 ㅠ", Toast.LENGTH_SHORT).show();
//                    }
//                });
////                if (oldAccessToken == null) {
////                    // Log in Logic
////
////                }
////                if (currentAccessToken == null) {
////                    // Log out logic
////                    //txtViewID.setText("NULL");
////                    //txtViewToken.setText("NULL");
////                    Log.d("로그아웃 시 실행됩니다 : ", pref.getValue(RbPreference.PREF_INTRO_USER_AGREEMENT1, "none"));
////                    pref.remove();
////                    loginButton.setBackgroundResource(R.mipmap.login_facebook);
////                    loginButton.invalidate();
////                }
//            }
//        };
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //if((id != null) && (gender != null)){
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.putExtra("user_id", id);
//                Log.d("LoginActivity", "id : " + id);
//                startActivity(intent);
//                finish();
//                //}
//                Toast.makeText(getApplicationContext(), "페이스북 인증 과정이 필요합니다. 로그인 하십시요.", Toast.LENGTH_LONG);
//            }
//        });
    }

    public void goActivity(Class input_class){
        Intent in = new Intent(this, input_class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);

        finish();
    }


    public void naver_login() {
        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(
                LoginActivity.this
                , OAUTH_CLIENT_ID
                , OAUTH_CLIENT_SECRET
                , OAUTH_CLIENT_NAME
        );

        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
    }


    //네이버 로그인 관련 핸들러
    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                like();
                //로그인 성공시 처리해야 할것들
                String accessToken = mOAuthLoginModule.getAccessToken(getApplicationContext());
                Log.d("accessToken", accessToken + "");

                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Intent intent2 = new Intent(getApplicationContext(), EditActivity.class);
                //intent.putExtra("user_id", OAUTH_CLIENT_ID);
                intent2.putExtra("user_id", accessToken);

                //만약 x서버 DB를 검색해서 해당과 동일한 id가 가입을 했을경우 -> 걍 넘어가!!!
                //startActivity(intent);

                //만약 아닐경우 -> 먼저 마이페이지 작성란으로 넘어간다.
                startActivity(intent2);
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void like() {
        // 서버에 뉴스피드 정보 요청
        final String URL_address = "https://apis.naver.com/nidlogin/nid/getUserProfile.xml";
        //mQueue2 = Volley.newRequestQueue(this);       //싱글턴 사용 안할 시
        mQueue2 = MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        // Request a string response from the provided URL.
        // 2) Request Obejct인 StringRequest 생성
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_address,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("Response is: ", response.toString());
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError) {
                    Log.d("error", error.toString());
                }
                // 네트워크 연결이 모두 끊어진 경우
                else if (error instanceof NoConnectionError) {
                    Log.d("error", error.toString());
                } else if (error instanceof AuthFailureError) {
                    Log.d("error", error.toString());
                }
                // 서버에러, URL에 해당 자료가 없어도 이곳이 불린다.
                else if (error instanceof ServerError) {
                    Log.d("error", error.toString());
                } else if (error instanceof NetworkError) {
                    Log.d("error", error.toString());
                } else if (error instanceof ParseError) {
                    Log.d("error", error.toString());
                }
            }
        });
        // 3) 생성한 StringRequest를 RequestQueue에 추가
        //mQueue2.add(stringRequest);   //싱글턴 사용 안할 시
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

}
