package ridickle.co.kr.mylittlepet.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.MySharedPreference;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;
import ridickle.co.kr.mylittlepet.Network.NetworkPresenter;
import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.Util.JSONPresenter;
import ridickle.co.kr.mylittlepet.init.InitActivity;
import ridickle.co.kr.mylittlepet.main.MainActivity;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private Context ctx;
    private static OAuthLogin mOAuthLoginInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ctx = this;

        setOAuthLoginInstance(ctx);

        OAuthLoginButton mOAuthLoginButton = (OAuthLoginButton) findViewById(R.id.buttonOAuthLoginImg);
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
        mOAuthLoginButton.setBgResourceId(R.drawable.login_full_green);
    }

    /**
     * OAuthLoginHandler를 startOAuthLoginActivity() 메서드 호출 시 파라미터로 전달하거나 OAuthLoginButton
     * 객체에 등록하면 인증이 종료되는 것을 확인할 수 있습니다.
     */

    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                String accessToken = mOAuthLoginInstance.getAccessToken(ctx);
//                String refreshToken = mOAuthLoginInstance.getRefreshToken(ctx);
//                long expiresAt = mOAuthLoginInstance.getExpiresAt(ctx);
//                String tokenType = mOAuthLoginInstance.getTokenType(ctx);

                new nLoginTask().execute(accessToken);
            } else {
                String errorCode = mOAuthLoginInstance.getLastErrorCode(ctx).getCode();
                String errorDesc = mOAuthLoginInstance.getLastErrorDesc(ctx);
                Toast.makeText(ctx, "errorCode:" + errorCode + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }
    };

    private class nLoginTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... accessToken) {
            return isNickEqual(accessToken[0]);
        }

        protected void onProgressUpdate(Void... progress) {
        }

        protected void onPostExecute(final String tokenId) {
            Log.d(TAG, tokenId);
            Call<Network_User> call = NetworkPresenter.getRetrofitService().get_usersLogin(tokenId);
            call.enqueue(new Callback<Network_User>() {
                @Override
                public void onResponse(Call<Network_User> call, Response<Network_User> response) {
                    MySharedPreference.getInstance(MyApplication.getDefaultContext()).setData(MySharedPreference.USER_TOKENID, tokenId);
                    if (response.body() != null) {
                        MyApplication.setUser(response.body());
                        MyApplication.setSharedPreference(response.body());

                        getApplicationContext().startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        ((Activity) ctx).finish();
                    } else {
                        getApplicationContext().startActivity(new Intent(getApplicationContext(), InitActivity.class));
                    }
                }

                @Override
                public void onFailure(Call<Network_User> call, Throwable t) {

                }
            });
        }
    }

    private String isNickEqual(String accessToken) {
        String header = "Bearer " + accessToken; // Bearer 다음에 공백 추가
        try {
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            JSONObject jObj = JSONPresenter.getJSONObject(response.toString(), "response");
            return jObj.getString("nickname");
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }

    private void setOAuthLoginInstance(Context ctx) {
        mOAuthLoginInstance = OAuthLogin.getInstance();
        mOAuthLoginInstance.init(
                ctx,
                MyApplication.getProp().getProperty("ClientId")
                , MyApplication.getProp().getProperty("ClientSecret")
                , "MyLittlePet"
                //,OAUTH_CALLBACK_INTENT
                // SDK 4.1.4 버전부터는 OAUTH_CALLBACK_INTENT변수를 사용하지 않습니다.
        );
    }

    public static OAuthLogin getOAuthLoginInstance() {
        return mOAuthLoginInstance;
    }
}
