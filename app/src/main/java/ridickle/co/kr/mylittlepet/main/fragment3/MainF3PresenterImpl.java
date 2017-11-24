package ridickle.co.kr.mylittlepet.main.fragment3;

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

public class MainF3PresenterImpl implements MainF3Presenter {
    private static final int MAINACTIVITY_FRAGMENT3 = 1;
    private static MainF3Presenter instance;
    private static MainF3Presenter.fragment fragment;
    private static MainF3Model model;


    public static synchronized MainF3Presenter getInstance(MainF3Presenter.fragment fr) {
        if(instance == null){
            instance = new MainF3PresenterImpl();
        }
        fragment = fr;
        model = new MainF3Model();
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
    public TabLayout tabSetting(final TabLayout mF3TabLayout) {
        mF3TabLayout.addTab(mF3TabLayout.newTab().setIcon(R.mipmap.ic_launcher));
        mF3TabLayout.addTab(mF3TabLayout.newTab().setIcon(R.mipmap.ic_launcher));
        mF3TabLayout.addTab(mF3TabLayout.newTab().setIcon(R.mipmap.ic_launcher));
        mF3TabLayout.addTab(mF3TabLayout.newTab().setIcon(R.mipmap.ic_launcher));

        // 탭 클릭 리스너
        mF3TabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        return mF3TabLayout;
    }
}
