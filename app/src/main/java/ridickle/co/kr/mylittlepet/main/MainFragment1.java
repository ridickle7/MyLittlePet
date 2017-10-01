package ridickle.co.kr.mylittlepet.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import ridickle.co.kr.mylittlepet.R;

public class MainFragment1 extends Fragment implements MainPresenter.Fragment1 {
    MainPresenter mPresenter;
    TabLayout mF1TabLayout;
    FrameLayout mF1FrameLayout;

    private ViewPager mF1ViewPager;
    private Fragment1TabAdapter vpa;


    public MainFragment1() {
        // Required empty public constructor
        mPresenter = MainPresenterImpl.newInstance(getActivity(), this);
    }

    public static MainFragment1 newInstance() {
        MainFragment1 fragment = new MainFragment1();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_main_fragment1, container, false);

        mF1TabLayout = (TabLayout) convertView.findViewById(R.id.f1Tab);
        mF1ViewPager = (ViewPager) convertView.findViewById(R.id.f1viewPager);

        mPresenter.fragmentSetting(mF1TabLayout, 1);

        vpa = new Fragment1TabAdapter(getChildFragmentManager());
        mF1ViewPager.setAdapter(vpa);
        mF1TabLayout.setupWithViewPager(mF1ViewPager);

        return convertView;
    }

    @Override
    public void viewSetting() {

    }
}
