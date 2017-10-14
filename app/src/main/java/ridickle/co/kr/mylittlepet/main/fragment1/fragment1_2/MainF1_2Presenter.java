package ridickle.co.kr.mylittlepet.main.fragment1.fragment1_2;

import android.content.Intent;

import java.io.File;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public interface MainF1_2Presenter {

    void loadItem();
    void searchByImage(File imageFile);

    File loadImageFile(Intent intent);

    public interface fragment{
        void updateItem();

        void loadToastMessage(String toastMessage);
    }
}
