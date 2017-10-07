package ridickle.co.kr.mylittlepet.main;

import android.support.design.widget.TabLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.main.fragment3.MainFragment3;

/**
 * Created by ridickle on 2017. 9. 29..
 */

public class MainPresenterImpl extends MainModel implements MainPresenter {
    public final static int MAINACTIVITY_ACTIVITY      = 0;
    public final static int MAINACTIVITY_FRAGMENT1     = 1;
    public final static int MAINACTIVITY_FRAGMENT2     = 2;
    public final static int MAINACTIVITY_FRAGMENT3     = 3;
    public final static int MAINACTIVITY_FRAGMENT4     = 4;
    public final static int MAINACTIVITY_FRAGMENT5     = 5;
    public final static int MAINACTIVITY_FRAGMENT1_1   = 6;
    public final static int MAINACTIVITY_FRAGMENT1_2   = 7;
    public final static int MAINACTIVITY_FRAGMENT1_3   = 8;
    public final static int MAINACTIVITY_FRAGMENT1_4   = 9;


    private static MainPresenterImpl instance = null;

    public MainPresenterImpl() {
    }

    // 1. view 설정
    public static synchronized MainPresenterImpl newInstance() {
        if (instance == null) {
            instance = new MainPresenterImpl();
        }
        return instance;
    }

    @Override
    public void fragmentSetting(final TabLayout tabLayout, final MainPresenter.View activity, final MainPresenter.Fragment fragment, int flag) {

        // 메인 액티비티
        if (flag == MAINACTIVITY_ACTIVITY) {
            tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
            tabLayout.addTab(tabLayout.newTab().setText("Send"));
            tabLayout.addTab(tabLayout.newTab().setText("Send & Post"));

            // 탭 클릭 리스너
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    setCurrentTabFragment(activity, tabLayout.getSelectedTabPosition(), MAINACTIVITY_ACTIVITY);
                    activity.tabClick("Tab" + tabLayout.getSelectedTabPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            replaceFragment(activity, MainFragment3.newInstance(), R.id.frameLayout);
            activity.viewSetting();
        }

        // 메인 액티비티 - 1번 프레그먼트
        else if (flag == MAINACTIVITY_FRAGMENT1) {
            tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));

            // 탭 클릭 리스너
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    setCurrentTabFragment(activity, tabLayout.getSelectedTabPosition(), MAINACTIVITY_FRAGMENT1);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            fragment.viewSetting();
        }

        else if (flag == MAINACTIVITY_FRAGMENT1_3) {
            fragment.viewSetting();
        }
    }

    @Override
    public void uiSetting(MainPresenter.View activity) {
        activity.settingUI();
    }

    @Override
    public void uiSetting(android.view.View view, Fragment fragment) {
        fragment.settingUI(view);
    }

    @Override
    public void f1_3Tag(android.view.View view, Fragment1_3 fragment) {
        ArrayList<String> dummyList = new ArrayList<>();
        for(int i=0 ; i<12 ; i++)
            dummyList.add("a");

        ArrayList<Integer> idList = tagInflating((LinearLayout)view, dummyList);
        fragment.tagSetting(view, idList);
    }

    @Override
    public void clickTag(Fragment1_3 fragment, String tagName) {
        fragment.tagClicked(tagName);
    }

    @Override
    public void f1_3Info(Fragment1_3 fragment) {
        ArrayList<String> dummyList = new ArrayList<>();
        for(int i=0 ; i<5 ; i++)
            dummyList.add("a" + i);

        fragment.infoSetting(dummyList);
    }
}
