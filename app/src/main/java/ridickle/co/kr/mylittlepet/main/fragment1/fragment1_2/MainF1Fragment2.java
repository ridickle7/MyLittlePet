package ridickle.co.kr.mylittlepet.main.fragment1.fragment1_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.main.MainPresenter;
import ridickle.co.kr.mylittlepet.main.MainPresenterImpl;

/**
 * Created by ridickle on 2017. 9. 30..
 */

public class MainF1Fragment2 extends Fragment implements MainPresenter.Fragment1_2 {
    MainPresenter mPresenter;
    static MainF1Fragment2 instance;

    public MainF1Fragment2() {
        // Required empty public constructor
        mPresenter = MainPresenterImpl.newInstance();
    }

    public static MainF1Fragment2 newInstance() {
        if (instance == null)
            instance = new MainF1Fragment2();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_tab1_fragment2, container, false);

        mPresenter.uiSetting(convertView, this);
        return convertView;
    }

    @Override
    public void viewSetting() {

    }

    @Override
    public void settingUI(View view) {

    }
}
