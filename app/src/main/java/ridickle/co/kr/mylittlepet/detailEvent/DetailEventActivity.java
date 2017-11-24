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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Content;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Event;
import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.Util.RecyclerViewPresenter;
import ridickle.co.kr.mylittlepet.detailEvent.fragmentDialog.DetailEventDialogFragment;
import ridickle.co.kr.mylittlepet.main.fragment3.fragment3_1.Fragment3_1ListAdapter;

public class DetailEventActivity extends AppCompatActivity implements DetailEventPresenter.view {
    private static final String TAG = "DetailEventActivity";
    DetailEventPresenter dPresenter;

    private BottomSheetBehavior bottomSheetBehavior;
    private FloatingActionButton fab;
    private LinearLayout layout;
    private String eId = "";

    RecyclerView dRecyclerView;
    ImageView dImageURL;
    TextView dIntroduce, dTerm, dContentNum;
    Fragment3_1ListAdapter dAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailevent);

        dPresenter = DetailEventPresenterImpl.getInstance(this);
        eId = getIntent().getStringExtra("eId");
    }

    @Override   // 이벤트 아이템 가져온 후, 화면 세팅
    public void updateView(Network_Event eventItem) {
        dImageURL = (ImageView) findViewById(R.id.dEventURL);
        dIntroduce = (TextView) findViewById(R.id.dIntroduce);
        dTerm = (TextView) findViewById(R.id.dTerm);

        MyApplication.setImage(getApplicationContext(), dImageURL, eventItem.geteImageURL());
        dIntroduce.setText(eventItem.geteIntroduce());
        dTerm.setText(MyApplication.getFormattedDate(eventItem.geteStartDate()) +
                " ~ " + MyApplication.getFormattedDate(eventItem.geteEndDate()));

        // header 높이 동적 설정
        CollapsingToolbarLayout dHeaderView = (CollapsingToolbarLayout) findViewById(R.id.dHeaderView);
        FrameLayout dHeader = (FrameLayout) findViewById(R.id.dHeader);
        dHeader.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        dHeaderView.setMinimumHeight(dHeader.getMeasuredHeight());

        // footer 높이 동적 설정
        fab = (FloatingActionButton) findViewById(R.id.fab);
        layout = (LinearLayout) findViewById(R.id.dBottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(layout);
        dPresenter.setBottomSheet(fab, layout);

        dPresenter.loadContentList(eId);
    }

    @Override   // 컨텐츠 리스트 가져온 후, 화면 세팅
    public void updateRecyclerView(ArrayList<Network_Content> contentList) {
        dContentNum = (TextView) findViewById(R.id.dContentNum);
        dContentNum.setText(contentList.size() + "개 컨텐츠");

        dRecyclerView = (RecyclerView) findViewById(R.id.dRecyclerView);
        dAdapter = new Fragment3_1ListAdapter(DetailEventActivity.this, 1, contentList);
        RecyclerViewPresenter.recyclerViewSetting(getApplicationContext(), dRecyclerView, 3, dAdapter);
    }

    @Override   // FloatButton과 BottomSheet 내의 아이템 클릭 이벤트 등록
    public void addClickEvent() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dPresenter.clickFloatButton(bottomSheetBehavior);
            }
        });

        MyApplication.cameraSelectorSetting(this, layout);
    }

    @Override   // 카메라에서 데이터 가져오고 인텐트를 다이얼로그에 넘김
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "requestCode : " + requestCode + "\nresultCode : " + resultCode);
        DetailEventDialogFragment dDialog = DetailEventDialogFragment.newInstance(data);
        if (resultCode == RESULT_OK) {
            if (requestCode == MyApplication.CAMERALIBRARY) {
                dDialog.show(getSupportFragmentManager(), "");
            } else if (requestCode ==MyApplication.CAMERACAPTURE) {
                dDialog.show(getSupportFragmentManager(), "");
            }
        } else {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        dPresenter.loadEventItem(eId);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
