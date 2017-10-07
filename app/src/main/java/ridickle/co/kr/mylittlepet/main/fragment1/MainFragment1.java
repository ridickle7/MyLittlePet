package ridickle.co.kr.mylittlepet.main.fragment1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.main.MainPresenter;
import ridickle.co.kr.mylittlepet.main.MainPresenterImpl;

public class MainFragment1 extends Fragment implements MainPresenter.Fragment1 {
    static MainFragment1 instance;
    MainPresenter mPresenter;
    TabLayout mF1TabLayout;

    private ViewPager mF1ViewPager;
    private Fragment1TabAdapter vpa;


    public MainFragment1() {
        // Required empty public constructor
        mPresenter = MainPresenterImpl.newInstance();
    }

    public static MainFragment1 newInstance() {
        if(instance == null)
            instance = new MainFragment1();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_main_fragment1, container, false);
        mPresenter.uiSetting(convertView, this);

        return convertView;
    }

    @Override
    public void viewSetting() {
    }

    @Override
    public void settingUI(View convertView) {
        mF1TabLayout = (TabLayout) convertView.findViewById(R.id.f1Tab);
        mF1ViewPager = (ViewPager) convertView.findViewById(R.id.f1viewPager);

        mPresenter.fragmentSetting(mF1TabLayout, null, this, MainPresenterImpl.MAINACTIVITY_FRAGMENT1);

        vpa = new Fragment1TabAdapter(getChildFragmentManager());
        mF1ViewPager.setAdapter(vpa);
        mF1TabLayout.setupWithViewPager(mF1ViewPager);
    }
}
