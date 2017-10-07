package ridickle.co.kr.mylittlepet.main.fragment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;

import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.main.MainPresenter;
import ridickle.co.kr.mylittlepet.main.MainPresenterImpl;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class MainFragment2 extends Fragment implements MainPresenter.Fragment2 {
    static MainFragment2 instance;
    MainPresenter mPresenter;
    EditText f2Gender, f2Age, f2Address;
    SeekBar f2Weight;

    public MainFragment2() {
        // Required empty public constructor
        mPresenter = MainPresenterImpl.newInstance();
    }

    public static MainFragment2 newInstance() {
        if (instance == null)
            instance = new MainFragment2();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_main_fragment2, container, false);
        mPresenter.uiSetting(convertView, this);

        return convertView;
    }

    @Override
    public void seekBarSetting() {

    }

    @Override
    public void viewSetting() {

    }

    @Override
    public void settingUI(View convertView) {
        f2Gender = (EditText) convertView.findViewById(R.id.f2Gender);
        f2Age = (EditText) convertView.findViewById(R.id.f2Age);
        f2Weight = (SeekBar) convertView.findViewById(R.id.f2Weight);
        f2Address = (EditText) convertView.findViewById(R.id.f2Address);

        f2Weight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
