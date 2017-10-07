package ridickle.co.kr.mylittlepet.detailEvent;

import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.widget.LinearLayout;

/**
 * Created by ridickle on 2017. 10. 3..
 */

public interface DetailEventPresenter {

    // activity 에서 사용되는 ui Setting
    void uiSetting(DetailEventPresenter.view activity);
    // fragment 에서 사용되는 ui Setting
    void uiSetting(DetailEventPresenter.fragment fragment, int resId);

    void floatButtonSetting(DetailEventPresenter.view activity, FloatingActionButton fab, LinearLayout linearLayout);
    void floatButtonClicking(DetailEventPresenter.view activity, BottomSheetBehavior bottomSheetBehavior);

    void cameraLibraryClicking(DetailEventPresenter.view activity);
    void cameraCaptureClicking(DetailEventPresenter.view activity);


    public interface view{
        void settingUI();
        void settingFloatButton();
        void clickingFloatButton();

        void clickingCameraLibrary(Intent intent);
        void clickingCameraCapture(Intent intent);
    }

    public interface fragment{
        void settingUI(android.view.View view);
    }
}
