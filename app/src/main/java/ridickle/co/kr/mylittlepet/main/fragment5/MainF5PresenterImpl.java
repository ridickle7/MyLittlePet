package ridickle.co.kr.mylittlepet.main.fragment5;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.Network.NetworkPresenter;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Event;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF5PresenterImpl implements MainF5Presenter {
    private static MainF5Presenter instance;

    private static MainF5Presenter.fragment fragment;
    private static MainF5Model model;

    public static MainF5Presenter getInstance(MainF5Presenter.fragment fr) {
        if(instance == null){
            instance = new MainF5PresenterImpl();
        }
        fragment = fr;
        model = new MainF5Model();
        return instance;
    }

    @Override
    public void loadItem() {
        Call<ArrayList<Network_Event>> call = NetworkPresenter.getRetrofitService().get_event();
        call.enqueue(new Callback<ArrayList<Network_Event>>() {
            @Override
            public void onResponse(Call<ArrayList<Network_Event>> call, Response<ArrayList<Network_Event>> response) {
                fragment.updateView(MyApplication.ifNull(response.body()));
            }

            @Override
            public void onFailure(Call<ArrayList<Network_Event>> call, Throwable t) {

            }
        });



    }
}
