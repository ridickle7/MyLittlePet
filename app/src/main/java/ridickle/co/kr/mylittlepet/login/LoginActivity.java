package ridickle.co.kr.mylittlepet.login;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ridickle.co.kr.mylittlepet.JSONPresenter;
import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.R;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private Context ctx;
    private OAuthLogin mOAuthLoginInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ctx = this;

        MyApplication.setOAuthLoginInstance(ctx);
        mOAuthLoginInstance = MyApplication.getOAuthLoginInstance();

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
                String refreshToken = mOAuthLoginInstance.getRefreshToken(ctx);
                long expiresAt = mOAuthLoginInstance.getExpiresAt(ctx);
                String tokenType = mOAuthLoginInstance.getTokenType(ctx);

                new nLoginTask().execute(accessToken);

//                getApplicationContext().startActivity(new Intent(getApplicationContext(), InitActivity.class));
//                Activity temp = (Activity)ctx;
//                ((Activity) ctx).finish();
            } else {
                String errorCode = mOAuthLoginInstance.getLastErrorCode(ctx).getCode();
                String errorDesc = mOAuthLoginInstance.getLastErrorDesc(ctx);
                Toast.makeText(ctx, "errorCode:" + errorCode + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }
    };

    private class nLoginTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... accessToken) {
            return isNickEqual(accessToken[0]);
        }

        protected void onProgressUpdate(Void... progress) {
        }

        protected void onPostExecute(String result) {
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
}
