package ridickle.co.kr.mylittlepet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import ridickle.co.kr.mylittlepet.main.MainActivity;


public class LoginActivity extends AppCompatActivity {
    private static Context ctx;
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
        mOAuthLoginButton.setBgResourceId(R.mipmap.login_full_green);
    }

    /**
     * OAuthLoginHandler를 startOAuthLoginActivity() 메서드 호출 시 파라미터로 전달하거나 OAuthLoginButton
     객체에 등록하면 인증이 종료되는 것을 확인할 수 있습니다.
     */

    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                String accessToken = mOAuthLoginInstance.getAccessToken(ctx);
                String refreshToken = mOAuthLoginInstance.getRefreshToken(ctx);
                long expiresAt = mOAuthLoginInstance.getExpiresAt(ctx);
                String tokenType = mOAuthLoginInstance.getTokenType(ctx);

                ctx.startActivity(new Intent(ctx, MainActivity.class));
                Activity temp = (Activity)ctx;
                temp.finish();
            }

            else {
                String errorCode = mOAuthLoginInstance.getLastErrorCode(ctx).getCode();
                String errorDesc = mOAuthLoginInstance.getLastErrorDesc(ctx);
                Toast.makeText(ctx, "errorCode:" + errorCode + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        };
    };
}
