package ridickle.co.kr.mylittlepet.main.fragment3.fragment3_2;

import android.content.Intent;
import android.util.Log;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_FamousFace;
import ridickle.co.kr.mylittlepet.Network.DataBody.faces;
import ridickle.co.kr.mylittlepet.Network.NetworkPresenter;
import ridickle.co.kr.mylittlepet.Util.PhotoPresenter;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF3_2PresenterImpl implements MainF3_2Presenter {
    private static MainF3_2Presenter instance = null;
    private static MainF3_2Presenter.fragment fragment = null;
    private static MainF3_2Model model = null;

    public static synchronized MainF3_2Presenter getInstance(MainF3_2Presenter.fragment fr) {
        if (instance == null) {
            instance = new MainF3_2PresenterImpl();
        }
        model = new MainF3_2Model();
        fragment = fr;

        return instance;
    }

    @Override
    public void loadItem() {
        fragment.updateItem();
    }

    public void searchByImage(File imageFile) {
        RequestBody image = RequestBody.create(MediaType.parse("image/*"), imageFile);
        Call<Network_FamousFace> call = NetworkPresenter.getNaverRetrofitService().post_visionCelebrity(image);

        call.enqueue(new Callback<Network_FamousFace>() {
            @Override
            public void onResponse(Call<Network_FamousFace> call, Response<Network_FamousFace> response) {
                String toastMessage = "";

                if (response.body() != null) {
                    faces[] faceList = response.body().getFaces();
                    for (int i = 0; i < faceList.length; i++) {
                        Log.d(MainF3Fragment2.TAG, faceList[i].getCelebrity().getValue());

                        if (i == faceList.length - 1)
                            toastMessage += i + "순위 : " + faceList[i].getCelebrity().getValue();

                        else
                            toastMessage += i + "순위 : " + faceList[i].getCelebrity().getValue() + "\n";
                    }
                } else {
                    Log.d(MainF3Fragment2.TAG, "닮은 것 따위 없음");
                    toastMessage = "닮은 유명인이 없습니다 ㅠㅠ";
                }

                fragment.loadToastMessage(toastMessage);
            }

            @Override
            public void onFailure(Call<Network_FamousFace> call, Throwable t) {
                Log.d("error : ", t.toString());
            }
        });
    }

    @Override
    public File loadImageFile(Intent intent) {
        MainF3Fragment2 tempFragment = ((MainF3Fragment2) fragment);

        PhotoPresenter.getInstance().setBitmap(tempFragment.getActivity(), intent);
        File file = new File(tempFragment.getActivity().getCacheDir(), "image");
        file = PhotoPresenter.getInstance().getFile(file);

        return file;
    }
}
