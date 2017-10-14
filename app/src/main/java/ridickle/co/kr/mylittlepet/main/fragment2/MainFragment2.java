package ridickle.co.kr.mylittlepet.main.fragment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;

import ridickle.co.kr.mylittlepet.R;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class MainFragment2 extends Fragment implements MainF2Presenter.fragment {
    static MainFragment2 instance;
    MainF2Presenter mPresenter;

    private View convertView;
    EditText f2Gender, f2Age, f2Address;
    SeekBar f2Weight;
    int seekBarNum = 2;

    public MainFragment2() {
        // Required empty public constructor
        mPresenter = MainF2PresenterImpl.getInstance(this);
    }

    public static MainFragment2 getInstance() {
        if (instance == null)
            instance = new MainFragment2();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        convertView = inflater.inflate(R.layout.fragment_main_fragment2, container, false);
        mPresenter.loadItem();

        return convertView;
    }

    @Override
    public void updateView() {
        f2Gender = (EditText) convertView.findViewById(R.id.f2Gender);
        f2Age = (EditText) convertView.findViewById(R.id.f2Age);
        f2Weight = (SeekBar) convertView.findViewById(R.id.f2Weight);
        f2Address = (EditText) convertView.findViewById(R.id.f2Address);

        f2Weight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean booleanValue) {
                if (progress <= 25) {
                    seekBarNum = 0;
                    seekBar.setProgress(0);
                } else if (progress >= 75) {
                    seekBarNum = 1;
                    seekBar.setProgress(100);
                } else {
                    seekBarNum = 2;
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
