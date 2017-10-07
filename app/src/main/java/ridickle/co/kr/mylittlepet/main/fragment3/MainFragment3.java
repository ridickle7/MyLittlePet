package ridickle.co.kr.mylittlepet.main.fragment3;

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

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class MainFragment3 extends Fragment implements MainPresenter.Fragment3 {
    static MainFragment3 instance;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Fragment3TabAdapter vpa;
    private MainPresenter mPresenter;

    public MainFragment3() {
        // Required empty public constructor
        mPresenter = MainPresenterImpl.newInstance();
    }

    public static MainFragment3 newInstance() {
        if (instance == null)
            instance = new MainFragment3();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_main_fragment3, container, false);
        mPresenter.uiSetting(convertView, this);

        return convertView;
    }

    @Override
    public void viewSetting() {

    }

    @Override
    public void settingUI(View convertView) {
        // setting ViewPager
        tabLayout = (TabLayout) convertView.findViewById(R.id.fragment3_tabs);
        viewPager = (ViewPager) convertView.findViewById(R.id.viewPager);

        vpa = new Fragment3TabAdapter(getChildFragmentManager());
        viewPager.setAdapter(vpa);
        tabLayout.setupWithViewPager(viewPager);
    }
}
