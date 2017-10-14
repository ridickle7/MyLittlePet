package ridickle.co.kr.mylittlepet.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.main.fragment1.MainFragment1;
import ridickle.co.kr.mylittlepet.main.fragment2.MainFragment2;
import ridickle.co.kr.mylittlepet.main.fragment3.MainFragment3;
import ridickle.co.kr.mylittlepet.main.fragment4.MainFragment4;
import ridickle.co.kr.mylittlepet.main.fragment5.MainFragment5;

/**
 * Created by ridickle on 2017. 9. 29..
 */

public class MainModel {
    // FrameLayout 내 fragment 교체하기
    public static void replaceFragment(FragmentActivity activity, Fragment fragment, int frameLayoutId) {
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(frameLayoutId, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commitAllowingStateLoss();
    }

    // 각각의 탭을 눌렀을 때 반응
    public static void setCurrentTabFragment(FragmentActivity activity, int tabPosition) {
        switch (tabPosition) {
            case 0:
                replaceFragment(activity, MainFragment1.getInstance(), R.id.frameLayout);
                break;
            case 1:
                replaceFragment(activity, MainFragment2.getInstance(), R.id.frameLayout);
                break;
            case 2:
                replaceFragment(activity, MainFragment3.getInstance(), R.id.frameLayout);
                break;
            case 3:
                replaceFragment(activity, MainFragment4.getInstance(), R.id.frameLayout);
                break;
            case 4:
                replaceFragment(activity, MainFragment5.getInstance(), R.id.frameLayout);
                break;
        }
    }
}
