package ridickle.co.kr.mylittlepet.main.fragment1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ridickle.co.kr.mylittlepet.R;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class MainFragment1 extends Fragment implements MainF1Presenter.fragment {
    public static final String TAG = "MainFragment3";
    private static MainFragment1 instance;

    private View convertView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Fragment1TabAdapter vpa;
    private MainF1Presenter mPresenter;

    public MainFragment1() {
        // Required empty public constructor
        mPresenter = MainF1PresenterImpl.getInstance(this);
    }

    public static MainFragment1 getInstance() {
        if (instance == null)
            instance = new MainFragment1();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_main_fragment1, container, false);
        mPresenter.loadItem();

        return convertView;
    }

    @Override
    public void updateView() {
        // setting ViewPager
        tabLayout = (TabLayout) convertView.findViewById(R.id.fragment1_tabs);
        viewPager = (ViewPager) convertView.findViewById(R.id.viewPager);

        vpa = new Fragment1TabAdapter(getChildFragmentManager());
        viewPager.setAdapter(vpa);
        tabLayout.setupWithViewPager(viewPager);
    }
}
