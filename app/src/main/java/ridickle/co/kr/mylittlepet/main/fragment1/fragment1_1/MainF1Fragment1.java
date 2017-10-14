package ridickle.co.kr.mylittlepet.main.fragment1.fragment1_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Content;
import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.Util.RecyclerViewPresenter;
import ridickle.co.kr.mylittlepet.detailEvent.fragmentDialog.DetailEventDialogFragment;
import ridickle.co.kr.mylittlepet.main.fragment1.MainFragment1;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ridickle on 2017. 9. 30..
 */

public class MainF1Fragment1 extends Fragment implements MainF1_1Presenter.fragment {
    public static final String TAG = "MainF1Fragment1";
    private static final int GRID_NUM = 3;
    MainF1_1Presenter mf1_1Presenter;
    static MainF1Fragment1 instance;

    private View convertView;
    private FloatingActionButton fButton;
    LinearLayout mBottomSheet;
    TextView f1_1Capture, f1_1Library;

    private Animation animTransUp, animTransDown;

    RecyclerView f1_1RecyclerView;
    Fragment1_1ListAdapter f1_1ListAdapter;

    public MainF1Fragment1() {
        // Required empty public constructor
        mf1_1Presenter = MainF1_1PresenterImpl.getInstance(this);
    }

    public static MainF1Fragment1 newInstance() {
        if (instance == null)
            instance = new MainF1Fragment1();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        convertView = inflater.inflate(R.layout.fragment_tab1_fragment1, container, false);

        mf1_1Presenter.loadItem();
        return convertView;
    }

    @Override
    public void updateView(ArrayList<Network_Content> contentList) {
        MainFragment1.getInstance().setF1LeftNumText(contentList.size() + "");

        fButton = (FloatingActionButton) convertView.findViewById(R.id.f1_1imageAdd);
        mBottomSheet = (LinearLayout) convertView.findViewById(R.id.f1_1BottomSheet);
        f1_1Capture = (TextView) convertView.findViewById(R.id.cameraCapture);
        f1_1Library = (TextView) convertView.findViewById(R.id.cameraLibrary);

        animTransUp = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_translate_up);
        animTransDown = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_translate_down);

        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBottomSheet.getVisibility() == View.GONE) {
                    mBottomSheet.startAnimation(animTransUp);
                    mBottomSheet.setVisibility(View.VISIBLE);
                } else {
                    mBottomSheet.startAnimation(animTransDown);
                    mBottomSheet.setVisibility(View.GONE);
                }
            }
        });

        mf1_1Presenter.cameraSelectorSetting(mBottomSheet);

        f1_1RecyclerView = (RecyclerView) convertView.findViewById(R.id.f1_1recyclerView);
        f1_1ListAdapter = new Fragment1_1ListAdapter(getActivity(), 0, contentList);

        RecyclerViewPresenter.recyclerViewSetting(getActivity(), f1_1RecyclerView, GRID_NUM, f1_1ListAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "requestCode : " + requestCode + "\nresultCode : " + resultCode);
        DetailEventDialogFragment dDialog = DetailEventDialogFragment.newInstance(data);
        if (resultCode == RESULT_OK) {
            if (requestCode == MyApplication.CAMERALIBRARY) {
                getFragmentManager().beginTransaction().add(dDialog, "DetailEventDialogFragment").commitAllowingStateLoss();
//                dDialog.show(getFragmentManager(), "");
            } else if (requestCode == MyApplication.CAMERACAPTURE) {
//                dDialog.show(getFragmentManager(), "");
                getFragmentManager().beginTransaction().add(dDialog, "DetailEventDialogFragment").commitAllowingStateLoss();
            }
        } else {

        }
    }
}
