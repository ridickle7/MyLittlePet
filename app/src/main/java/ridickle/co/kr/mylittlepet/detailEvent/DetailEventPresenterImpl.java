package ridickle.co.kr.mylittlepet.detailEvent;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by ridickle on 2017. 10. 3..
 */

public class DetailEventPresenterImpl implements DetailEventPresenter {
    static DetailEventPresenterImpl instance;

    public static synchronized DetailEventPresenterImpl newInstance() {
        if (instance == null) {
            instance = new DetailEventPresenterImpl();
        }
        return instance;
    }

    @Override
    public void uiSetting(DetailEventPresenter.view activity) {
        activity.settingUI();
    }

    @Override
    public void uiSetting(DetailEventPresenter.fragment fragment, int resId) {
        LayoutInflater inflater = ((Fragment)fragment).getActivity().getLayoutInflater();
        View convertView = inflater.inflate(resId, null);
        fragment.settingUI(convertView);
    }

    @Override
    public void floatButtonSetting(final DetailEventPresenter.view activity, FloatingActionButton fab, LinearLayout linearLayout) {
        linearLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        bottomSheetBehavior.setPeekHeight(linearLayout.getMeasuredHeight());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        activity.settingFloatButton();
    }

    @Override
    public void floatButtonClicking(DetailEventPresenter.view activity, BottomSheetBehavior behavior) {
        if (behavior.getState() == BottomSheetBehavior.STATE_HIDDEN)
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        else
            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        activity.clickingFloatButton();
    }

    @Override
    public void cameraLibraryClicking(DetailEventPresenter.view activity) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        activity.clickingCameraLibrary(intent);
    }

    @Override
    public void cameraCaptureClicking(DetailEventPresenter.view activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        activity.clickingCameraCapture(intent);
    }
}
