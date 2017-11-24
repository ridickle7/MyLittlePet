package ridickle.co.kr.mylittlepet.main.fragment1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ridickle.co.kr.mylittlepet.main.fragment1.fragment1.Fragment1Tab1;
import ridickle.co.kr.mylittlepet.main.fragment1.fragment2.Fragment1Tab2;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class Fragment1TabAdapter extends FragmentPagerAdapter {
    private Fragment[] fragmentList = {Fragment1Tab1.newInstance(), Fragment1Tab2.newInstance()};
    private String[] fragmentTitle = {"거리순", "최신순"};

    public Fragment1TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList[position];
    }

    @Override
    public int getCount() {
        return fragmentList.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitle[position];
    }
}
