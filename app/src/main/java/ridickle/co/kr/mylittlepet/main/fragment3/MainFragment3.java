package ridickle.co.kr.mylittlepet.main.fragment3;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;
import ridickle.co.kr.mylittlepet.R;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class MainFragment3 extends Fragment implements MainF3Presenter.fragment {
    public static final String TAG = "MainFragment3";
    private static MainFragment3 instance;

    private View convertView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Fragment3TabAdapter vpa;
    private MainF3Presenter mPresenter;

    public MainFragment3() {
        // Required empty public constructor
        mPresenter = MainF3PresenterImpl.getInstance(this);
    }

    public static MainFragment3 getInstance() {
        if (instance == null)
            instance = new MainFragment3();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_main_fragment3, container, false);
        mPresenter.loadItem();

        return convertView;
    }

    @Override
    public void updateView() {
        // setting ViewPager
        tabLayout = (TabLayout) convertView.findViewById(R.id.fragment3_tabs);
        viewPager = (ViewPager) convertView.findViewById(R.id.viewPager);

        vpa = new Fragment3TabAdapter(getChildFragmentManager());
        viewPager.setAdapter(vpa);
        tabLayout.setupWithViewPager(viewPager);
    }
}
