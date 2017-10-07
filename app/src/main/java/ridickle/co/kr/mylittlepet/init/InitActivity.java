package ridickle.co.kr.mylittlepet.init;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.SeekBar;

import ridickle.co.kr.mylittlepet.R;

public class InitActivity extends AppCompatActivity implements InitPresenter.view {
    InitPresenter iPresenter;
    EditText iGender, iAge, iAddress;
    SeekBar iWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iPresenter = InitPresenterImpl.newInstance();
        iPresenter.uiSetting(this);
    }

    @Override
    public void settingUI() {
        iGender = (EditText) findViewById(R.id.iGender);
        iAge = (EditText) findViewById(R.id.iAge);
        iWeight = (SeekBar) findViewById(R.id.iWeight);
        iAddress = (EditText) findViewById(R.id.iAddress);

        iWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean booleanValue) {
                if (progress <= 25) {
                    seekBar.setProgress(0);
                } else if (progress >= 75) {
                    seekBar.setProgress(100);
                } else {
                    seekBar.setProgress(50);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
