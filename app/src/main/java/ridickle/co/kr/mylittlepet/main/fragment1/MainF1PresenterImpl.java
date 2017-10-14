package ridickle.co.kr.mylittlepet.main.fragment1;

import android.support.design.widget.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ridickle.co.kr.mylittlepet.MySharedPreference;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;
import ridickle.co.kr.mylittlepet.Network.NetworkPresenter;
import ridickle.co.kr.mylittlepet.R;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF1PresenterImpl implements MainF1Presenter {
    private static final int MAINACTIVITY_FRAGMENT1 = 1;
    private static MainF1Presenter instance;
    private static MainF1Presenter.fragment fragment;
    private static MainF1Model model;


    public static synchronized MainF1Presenter getInstance(MainF1Presenter.fragment fr) {
        if(instance == null){
            instance = new MainF1PresenterImpl();
        }
        fragment = fr;
        model = new MainF1Model();
        return instance;
    }


    @Override
    public void loadItem() {
        Call<Network_User> call = NetworkPresenter.getRetrofitService().get_usersDetail(MySharedPreference.getDefaultInstance().getStringData(MySharedPreference.USER_ID));
        call.enqueue(new Callback<Network_User>() {
            @Override
            public void onResponse(Call<Network_User> call, Response<Network_User> response) {
                fragment.updateView(response.body());
            }

            @Override
            public void onFailure(Call<Network_User> call, Throwable t) {

            }
        });
    }

    @Override
    public TabLayout tabSetting(final TabLayout tabLayout) {
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));

        // 탭 클릭 리스너
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                MainModel.setCurrentTabFragment(((MainFragment1)fragment).getActivity(), tabLayout.getSelectedTabPosition());
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
