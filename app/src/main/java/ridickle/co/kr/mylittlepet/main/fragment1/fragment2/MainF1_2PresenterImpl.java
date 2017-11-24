package ridickle.co.kr.mylittlepet.main.fragment1.fragment2;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;
import ridickle.co.kr.mylittlepet.Network.NetworkPresenter;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF1_2PresenterImpl implements MainF1_2Presenter {
    private static MainF1_2Presenter instance;
    private static MainF1_2Presenter.fragment fragment;
    private static MainF1_2Model model;

    public static MainF1_2Presenter getInstance(MainF1_2Presenter.fragment fr) {
        if(instance == null){
            instance = new MainF1_2PresenterImpl();
        }
        fragment = fr;
        model = new MainF1_2Model();
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
