package ridickle.co.kr.mylittlepet.main.fragment3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ridickle.co.kr.mylittlepet.main.fragment3.fragment3_1.MainF3Fragment1;
import ridickle.co.kr.mylittlepet.main.fragment3.fragment3_2.MainF3Fragment2;
import ridickle.co.kr.mylittlepet.main.fragment3.fragment3_3.MainF3Fragment3;
import ridickle.co.kr.mylittlepet.main.fragment3.fragment3_4.MainF3Fragment4;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class Fragment3TabAdapter extends FragmentPagerAdapter {
    private Fragment[] fragmentList = {MainF3Fragment1.newInstance(), MainF3Fragment2.newInstance(), MainF3Fragment3.newInstance(), MainF3Fragment4.newInstance()};
//    private String[] fragmentTitle = {"a", "b", "c", "d"};

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

//    @Override     // 탭 타이틀 사용 시 주석 제거
//    public CharSequence getPageTitle(int position) {
//        return fragmentTitle[position];
//    }
}
