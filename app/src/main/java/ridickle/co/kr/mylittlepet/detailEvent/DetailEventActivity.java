package ridickle.co.kr.mylittlepet.detailEvent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.RecyclerViewPresenter;
import ridickle.co.kr.mylittlepet.main.fragment1.fragment1_1.Fragment1_1ListAdapter;

public class DetailEventActivity extends AppCompatActivity implements DetailEventPresenter.view {
    private static final String TAG = "DetailEventActivity";
    private final int DetailEventActivity_CAMERALIBRARY = 0;
    private final int DetailEventActivity_CAMERACAPTURE = 1;
    DetailEventPresenter dPresenter;
    Fragment1_1ListAdapter dAdapter;

    private DetailEventPresenter.view activity = this;
    private BottomSheetBehavior bottomSheetBehavior;
    private FloatingActionButton fab;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailevent);

        dPresenter = DetailEventPresenterImpl.newInstance();
        dPresenter.uiSetting(this);
    }

    @Override
    public void settingUI() {
        // header 높이 동적 설정
        CollapsingToolbarLayout dHeaderView = (CollapsingToolbarLayout) findViewById(R.id.dHeaderView);
        FrameLayout dHeader = (FrameLayout) findViewById(R.id.dHeader);
        dHeader.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        dHeaderView.setMinimumHeight(dHeader.getMeasuredHeight());

        // footer 높이 동적 설정
        fab = (FloatingActionButton) findViewById(R.id.fab);
        layout = (LinearLayout) findViewById(R.id.dBottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(layout);
        dPresenter.floatButtonSetting(this, fab, layout);

        dAdapter = new Fragment1_1ListAdapter(this, 1);
        RecyclerViewPresenter.recyclerViewSetting(getApplicationContext(), (RecyclerView) findViewById(R.id.dRecyclerView), 3, dAdapter);

    }

    @Override
    public void settingFloatButton() {
        findViewById(R.id.dCameraLibrary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dPresenter.cameraLibraryClicking(activity);
            }
        });

        findViewById(R.id.dCameraCapture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dPresenter.cameraCaptureClicking(activity);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dPresenter.floatButtonClicking(activity, bottomSheetBehavior);
            }
        });
    }

    @Override
    public void clickingFloatButton() {

    }

    @Override
    public void clickingCameraLibrary(Intent intent) {
        startActivityForResult(intent, DetailEventActivity_CAMERALIBRARY);
    }

    @Override
    public void clickingCameraCapture(Intent intent) {
        startActivityForResult(intent, DetailEventActivity_CAMERACAPTURE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "requestCode : " + requestCode + "\nresultCode : " + resultCode);
        DetailEventDialogFragment dDialog = DetailEventDialogFragment.newInstance(data, dPresenter);
        if (resultCode == RESULT_OK) {
            if (requestCode == DetailEventActivity_CAMERALIBRARY) {
                dDialog.show(getSupportFragmentManager(), "");
            } else if (requestCode == DetailEventActivity_CAMERACAPTURE) {
                dDialog.show(getSupportFragmentManager(), "");
            }
        } else {

        }
    }
}
