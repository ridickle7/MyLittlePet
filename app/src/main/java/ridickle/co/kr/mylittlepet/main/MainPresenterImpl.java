package ridickle.co.kr.mylittlepet.main;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;

import ridickle.co.kr.mylittlepet.R;

/**
 * Created by ridickle on 2017. 9. 29..
 */

public class MainPresenterImpl extends MainModel implements MainPresenter {
    public final int MAINACTIVITY_ACTIVITY = 0;
    public final int MAINACTIVITY_FRAGMENT1 = 1;
    public final int MAINACTIVITY_FRAGMENT2 = 2;
    public final int MAINACTIVITY_FRAGMENT3 = 3;
    public final int MAINACTIVITY_FRAGMENT4 = 4;
    public final int MAINACTIVITY_FRAGMENT5 = 5;


    private static MainPresenterImpl instance = null;
    public static FragmentActivity activity;
    private MainPresenter.View view;
    private MainPresenter.Fragment fragment;

    public MainPresenterImpl(MainPresenter.View view) {
        this.activity = (FragmentActivity) view;
    }

    public MainPresenterImpl(Activity activity) {
        this.activity = (FragmentActivity) activity;
    }

    // 1. view 설정
    public static synchronized MainPresenterImpl newInstance(MainPresenter.View view) {
        if (instance == null) {
            instance = new MainPresenterImpl(view);
        }

        instance.view = view;

        return instance;
    }

    // 1-2. fragment 설정
    public static synchronized MainPresenterImpl newInstance(Activity activity, MainPresenter.Fragment fragment) {
        if (instance == null) {
            instance = new MainPresenterImpl(activity);
        }

        instance.fragment = fragment;

        return instance;
    }


    @Override
    public void tabClickEvent(TabLayout.Tab tab, int position) {
        instance.view.tabClickEvent("Tab" + position);

    }

    @Override
    public void fragmentSetting(final TabLayout tabLayout, int flag) {
        if (flag == 0) {
            tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
            tabLayout.addTab(tabLayout.newTab().setText("Send"));
            tabLayout.addTab(tabLayout.newTab().setText("Send & Post"));

            // 탭 클릭 리스너
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    setCurrentTabFragment(tabLayout.getSelectedTabPosition(), MAINACTIVITY_ACTIVITY);
                    tabClickEvent(tab, tabLayout.getSelectedTabPosition());

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            replaceFragment(MainFragment3.newInstance(), R.id.frameLayout);
            instance.view.viewSetting();
        }

        else if (flag == 1) {
            tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));

            // 탭 클릭 리스너
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    setCurrentTabFragment(tabLayout.getSelectedTabPosition(), MAINACTIVITY_FRAGMENT1);
                    tabClickEvent(tab, tabLayout.getSelectedTabPosition());

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            ((Fragment1)(instance.fragment)).viewSetting();
        }
    }
}
