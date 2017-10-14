package ridickle.co.kr.mylittlepet.detailEvent.fragmentDialog;

import android.content.Context;
import android.content.Intent;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.MySharedPreference;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Content;
import ridickle.co.kr.mylittlepet.Network.NetworkPresenter;
import ridickle.co.kr.mylittlepet.Util.PhotoPresenter;

/**
 * Created by ridickle on 2017. 10. 13..
 */

public class DetailEventDialogPresenterImpl implements DetailEventDialogPresenter {
    private static final String TAG = "DetailDialogFragment";
    private static DetailEventDialogPresenterImpl instance;
    private static DetailEventDialogPresenter.fragment fragment;
    private static DetailEventDialogModel model;

    public static synchronized DetailEventDialogPresenter getInstance(DetailEventDialogPresenter.fragment fr) {
        if (instance == null) {
            instance = new DetailEventDialogPresenterImpl();
        }
        fragment = fr;
        model = new DetailEventDialogModel();
        return instance;
    }

    @Override
    public void loadItem(Context context, Intent intent) {
        PhotoPresenter.getInstance().setBitmap(context, intent);
        fragment.updateView(PhotoPresenter.getInstance().getBitmap());
    }

    @Override
    public void clickOk(String eId, String cText) {
        String uId = MySharedPreference.getDefaultInstance().getStringData(MySharedPreference.USER_ID);
        String fileName = uId + System.currentTimeMillis() + ".png";
        String imageURL = MyApplication.getProp().getProperty("AWSS3URL") + fileName;

        File file = new File(((DetailEventDialogFragment) fragment).getActivity().getCacheDir(), fileName);

        model.sendImageToAmazon(fileName, file);

        Call<Network_Content> call;
        if (!(eId.equals("")))
            call = NetworkPresenter.getRetrofitService().post_eventsContent(uId, eId, cText, imageURL);
        else
            call = NetworkPresenter.getRetrofitService().post_content(uId, cText, imageURL);

        call.enqueue(new Callback<Network_Content>() {
            @Override
            public void onResponse(Call<Network_Content> call, Response<Network_Content> response) {
                fragment.executeOk(response.body());
            }

            @Override
            public void onFailure(Call<Network_Content> call, Throwable t) {

            }
        });
    }
}
