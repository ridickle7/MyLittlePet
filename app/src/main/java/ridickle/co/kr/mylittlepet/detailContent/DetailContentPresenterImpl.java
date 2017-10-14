package ridickle.co.kr.mylittlepet.detailContent;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ridickle.co.kr.mylittlepet.MySharedPreference;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Content;
import ridickle.co.kr.mylittlepet.Network.NetworkPresenter;

/**
 * Created by ridickle on 2017. 10. 14. .
 */

public class DetailContentPresenterImpl implements DetailContentPresenter{
    private static final String TAG = "DetailContentActivity";
    private static DetailContentPresenterImpl instance;
    private static DetailContentPresenter.view activity;

    public static synchronized DetailContentPresenterImpl getInstance(DetailContentPresenter.view view){
        if(instance == null){
            instance = new DetailContentPresenterImpl();
        }
        activity = view;
        return instance;
    }

    @Override
    public void loadItem() {
        Call<Network_Content> call = NetworkPresenter.getRetrofitService().get_contentDetail(MySharedPreference.getDefaultInstance().getStringData(MySharedPreference.CONTENT_ID));
        call.enqueue(new Callback<Network_Content>() {
            @Override
            public void onResponse(Call<Network_Content> call, Response<Network_Content> response) {
                activity.updateView(response.body());
            }

            @Override
            public void onFailure(Call<Network_Content> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }
}
