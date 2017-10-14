package ridickle.co.kr.mylittlepet.init;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.MySharedPreference;
import ridickle.co.kr.mylittlepet.Network.NetworkPresenter;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;
import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.main.MainActivity;

public class InitActivity extends AppCompatActivity implements InitPresenter.view {
    private static final String TAG = "InitActivity";
    InitPresenter iPresenter;
    ImageView iImage;
    EditText iNickName, iIntroduce, iAge, iSpecify, iAddress;
    RadioButton iMale, iFemale;
    SeekBar iWeight;
    Button iOk;
    int weightFlag = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iPresenter = InitPresenterImpl.newInstance(this);
        iPresenter.uiSetting();
    }

    @Override
    public void settingUI(final float latitude, final float longitude, Address address) {
        iImage = (ImageView) findViewById(R.id.iImage);
        iNickName = (EditText) findViewById(R.id.iNickName);
        iIntroduce = (EditText) findViewById(R.id.iIntroduce);
        iMale = (RadioButton) findViewById(R.id.iMale);
        iFemale = (RadioButton) findViewById(R.id.iFemale);
        iAge = (EditText) findViewById(R.id.iAge);
        iSpecify = (EditText) findViewById(R.id.iSpecify);
        iAddress = (EditText) findViewById(R.id.iAddress);
        iWeight = (SeekBar) findViewById(R.id.iWeight);
        iOk = (Button) findViewById(R.id.iOk);

        iImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        iWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean booleanValue) {
                if (progress <= 25) {
                    seekBar.setProgress(0);
                    weightFlag = 0;
                } else if (progress >= 75) {
                    seekBar.setProgress(100);
                    weightFlag = 1;
                } else {
                    seekBar.setProgress(50);
                    weightFlag = 2;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        iAddress.setText(address.getLocality() + " " + address.getSubLocality() + " " + address.getThoroughfare());

        iOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Network_User user = new Network_User(
                        ""
                        , MySharedPreference.getInstance(MyApplication.getDefaultContext()).getStringData(MySharedPreference.USER_TOKENID)
                        , iNickName.getText().toString()
                        , MyApplication.getProp().getProperty("AWSS3URL") + iNickName.getText().toString() + ".png"
                        , iIntroduce.getText().toString()
                        , iMale.isChecked()
                        , Integer.parseInt(iAge.getText().toString())
                        , iSpecify.getText().toString()
                        , weightFlag
                        , iAddress.getText().toString()
                        , latitude
                        , longitude
                        , null
                        , null
                        , null);
                iPresenter.buttonClick(user);
            }
        });
    }

    //MyApplication.sendImage(iNickName.getText().toString());
    @Override
    public void clickingIv(Bitmap bitmap) {
        iImage.setImageBitmap(bitmap);
    }

    @Override
    public void clickButton(Network_User user) {
        Call<Network_User> call = NetworkPresenter.getRetrofitService().post_user(user);
        call.enqueue(new Callback<Network_User>() {
            @Override
            public void onResponse(Call<Network_User> call, Response<Network_User> response) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                MyApplication.setUser(response.body());
                MyApplication.setSharedPreference(response.body());

                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Network_User> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d(TAG, "requestCode : " + requestCode + "\nresultCode : " + resultCode);
        if (resultCode == RESULT_OK) {
            iPresenter.ivClick(intent);
        } else {

        }
    }
}
