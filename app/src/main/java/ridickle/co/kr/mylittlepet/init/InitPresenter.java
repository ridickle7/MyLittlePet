package ridickle.co.kr.mylittlepet.init;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;

import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;

/**
 * Created by ridickle on 2017. 10. 5..
 */

public interface InitPresenter {
    // activity 에서 사용되는 ui Setting
    void uiSetting();

    // button click 시
    void buttonClick(Network_User user);

    void ivClick(Intent intent);

    interface view{
        void settingUI(float latitude, float longitude, Address address);
        void clickingIv(Bitmap bitmap);
        void clickButton(Network_User user);
    }
}
