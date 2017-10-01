package ridickle.co.kr.mylittlepet.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ridickle.co.kr.mylittlepet.R;

/**
 * Created by ridickle on 2017. 9. 29..
 */

public class MainModel {
    // FrameLayout 내 fragment 교체하기
    public void replaceFragment(Fragment fragment, int frameLayoutId) {
        FragmentManager fm = MainPresenterImpl.activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(frameLayoutId, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commitAllowingStateLoss();
    }

    // 각각의 탭을 눌렀을 때 반응
    public void setCurrentTabFragment(int tabPosition, int flag) {
        if (flag == 0)
            switch (tabPosition) {
                case 0:
                    replaceFragment(MainFragment1.newInstance(), R.id.frameLayout);
                    break;
                case 1:
                    replaceFragment(MainFragment2.newInstance(), R.id.frameLayout);
                    break;
                case 2:
                    replaceFragment(MainFragment3.newInstance(), R.id.frameLayout);
                    break;
                case 3:
                    replaceFragment(MainFragment4.newInstance(), R.id.frameLayout);
                    break;
                case 4:
                    replaceFragment(MainFragment5.newInstance(), R.id.frameLayout);
                    break;
            }
    }
}
