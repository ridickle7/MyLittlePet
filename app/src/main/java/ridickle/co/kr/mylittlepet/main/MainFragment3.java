package ridickle.co.kr.mylittlepet.main;

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

public class MainFragment3 extends Fragment implements MainPresenter.Fragment3  {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Fragment3TabAdapter vpa;

    public MainFragment3() {
        // Required empty public constructor
    }

    public static MainFragment3 newInstance() {
        MainFragment3 fragment = new MainFragment3();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_fragment3, container, false);

        // setting ViewPager
        tabLayout = (TabLayout) v.findViewById(R.id.fragment3_tabs);
        viewPager = (ViewPager) v.findViewById(R.id.viewPager);

        vpa = new Fragment3TabAdapter(getChildFragmentManager());
        viewPager.setAdapter(vpa);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }

}
