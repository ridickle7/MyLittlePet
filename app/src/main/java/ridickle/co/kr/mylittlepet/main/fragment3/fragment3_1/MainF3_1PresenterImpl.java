package ridickle.co.kr.mylittlepet.main.fragment3.fragment3_1;

import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ridickle.co.kr.mylittlepet.MySharedPreference;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Content;
import ridickle.co.kr.mylittlepet.Network.NetworkPresenter;
import ridickle.co.kr.mylittlepet.R;

import static ridickle.co.kr.mylittlepet.MyApplication.CAMERACAPTURE;
import static ridickle.co.kr.mylittlepet.MyApplication.CAMERALIBRARY;
import static ridickle.co.kr.mylittlepet.MyApplication.getDefaultContext;
import static ridickle.co.kr.mylittlepet.main.fragment3.fragment3_1.MainF3Fragment1.TAG;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF3_1PresenterImpl implements MainF3_1Presenter {
    private static MainF3_1Presenter instance;
    private static MainF3_1Presenter.fragment fragment;

    public static MainF3_1Presenter getInstance(fragment fr){
        if(instance == null){
            instance = new MainF3_1PresenterImpl();
        }
        fragment = fr;
        return instance;
    }

    @Override
    public void loadItem() {
        Call<ArrayList<Network_Content>> call = NetworkPresenter.getRetrofitService().get_content(MySharedPreference.getInstance(getDefaultContext()).getStringData(MySharedPreference.USER_ID));
        call.enqueue(new Callback<ArrayList<Network_Content>>() {
            @Override
            public void onResponse(Call<ArrayList<Network_Content>> call, Response<ArrayList<Network_Content>> response) {
                fragment.updateView(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Network_Content>> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }

    @Override
    public void cameraSelectorSetting(LinearLayout view){
        view.findViewById(R.id.cameraLibrary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                ((MainF3Fragment1)fragment).startActivityForResult(intent, CAMERALIBRARY);
            }
        });

        view.findViewById(R.id.cameraCapture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                ((MainF3Fragment1)fragment).startActivityForResult(intent, CAMERACAPTURE);
            }
        });
    }
}
