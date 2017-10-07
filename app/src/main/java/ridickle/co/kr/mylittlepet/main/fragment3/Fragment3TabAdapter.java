package ridickle.co.kr.mylittlepet.main.fragment3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class Fragment3TabAdapter extends FragmentPagerAdapter {
    private Fragment[] fragmentList = {Fragment3Tab1.newInstance(), Fragment3Tab2.newInstance()};
    private String[] fragmentTitle = {"거리순", "최신순"};

    public Fragment3TabAdapter(FragmentManager fm) {
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
