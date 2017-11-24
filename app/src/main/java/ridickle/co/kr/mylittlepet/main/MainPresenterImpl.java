package ridickle.co.kr.mylittlepet.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;

import ridickle.co.kr.mylittlepet.R;

/**
 * Created by ridickle on 2017. 9. 29..
 */

public class MainPresenterImpl implements MainPresenter {
    private static MainPresenterImpl instance;
    private static MainPresenter.view activity;
    private static MainModel model;

    public static MainPresenter getInstance(MainPresenter.view view) {
        if(instance == null){
            instance = new MainPresenterImpl();
        }
        activity = view;
        model = new MainModel();
        return instance;
    }

    @Override
    public void loadItem() {
        activity.updateView();
    }

    public TabLayout tabSetting(final TabLayout tabLayout) {
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab1_main));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab2_main));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab4_main));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab5_main));

        // 탭 클릭 리스너
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                MainModel.setCurrentTabFragment((FragmentActivity)activity, tabLayout.getSelectedTabPosition());
                activity.tabClick(tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return tabLayout;
    }
}
