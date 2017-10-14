package ridickle.co.kr.mylittlepet.main.fragment1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ridickle.co.kr.mylittlepet.main.fragment1.fragment1_1.MainF1Fragment1;
import ridickle.co.kr.mylittlepet.main.fragment1.fragment1_2.MainF1Fragment2;
import ridickle.co.kr.mylittlepet.main.fragment1.fragment1_3.MainF1Fragment3;
import ridickle.co.kr.mylittlepet.main.fragment1.fragment1_4.MainF1Fragment4;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class Fragment1TabAdapter extends FragmentPagerAdapter {
    private Fragment[] fragmentList = {MainF1Fragment1.newInstance(), MainF1Fragment2.newInstance(), MainF1Fragment3.newInstance(), MainF1Fragment4.newInstance()};
    private String[] fragmentTitle = {"a", "b", "c", "d"};

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
