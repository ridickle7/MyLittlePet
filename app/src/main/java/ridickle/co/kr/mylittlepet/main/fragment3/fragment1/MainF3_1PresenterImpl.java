package ridickle.co.kr.mylittlepet.main.fragment3.fragment1;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;
import ridickle.co.kr.mylittlepet.Network.NetworkPresenter;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF3_1PresenterImpl implements MainF3_1Presenter {
    private static MainF3_1Presenter instance;

    private static MainF3_1Presenter.fragment fragment;
    private static MainF3_1Model model;

    public static MainF3_1Presenter getInstance(fragment fr) {
        if(instance == null){
            instance = new MainF3_1PresenterImpl();
        }
        fragment = fr;
        model = new MainF3_1Model();
        return instance;
    }

    @Override
    public void loadItem() {
        Call<ArrayList<Network_User>> call = NetworkPresenter.getRetrofitService().get_users(0);
        call.enqueue(new Callback<ArrayList<Network_User>>() {
            @Override
            public void onResponse(Call<ArrayList<Network_User>> call, Response<ArrayList<Network_User>> response) {
                fragment.updateView(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Network_User>> call, Throwable t) {

            }
        });
    }
}
