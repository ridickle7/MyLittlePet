package ridickle.co.kr.mylittlepet.detailEvent;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.MySharedPreference;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Content;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Event;
import ridickle.co.kr.mylittlepet.Network.NetworkPresenter;

/**
 * Created by ridickle on 2017. 10. 3..
 */

public class DetailEventPresenterImpl implements DetailEventPresenter {
    static DetailEventPresenterImpl instance;
    private static DetailEventPresenter.view activity;

    public static synchronized DetailEventPresenterImpl getInstance(DetailEventPresenter.view view) {
        if (instance == null) {
            instance = new DetailEventPresenterImpl();
        }
        activity = view;
        return instance;
    }

    @Override   // 이벤트 id 등록하고, id에 해당하는 이벤트 정보 가져옴
    public void loadEventItem(String eId) {
        MySharedPreference.getDefaultInstance().setData(MySharedPreference.EVENT_ID, eId);
        Call<Network_Event> call = NetworkPresenter.getRetrofitService().get_event(eId);
        call.enqueue(new Callback<Network_Event>() {
            @Override
            public void onResponse(Call<Network_Event> call, Response<Network_Event> response) {
                Network_Event eventItem = response.body();
                activity.updateView(eventItem);
            }

            @Override
            public void onFailure(Call<Network_Event> call, Throwable t) {

            }
        });
    }

    @Override   // 클릭 시 BottomSheet가 나왔다가 들어갔다가 함
    public void setBottomSheet(FloatingActionButton fab, LinearLayout linearLayout) {
        linearLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        bottomSheetBehavior.setPeekHeight(linearLayout.getMeasuredHeight());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        activity.addClickEvent();
    }

    @Override   // FloatButton 클릭 시 이벤트 등록
    public void clickFloatButton(BottomSheetBehavior behavior) {
        if (behavior.getState() == BottomSheetBehavior.STATE_HIDDEN)
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        else
            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override   // 컨텐츠 리스트 추가
    public void loadContentList(String eId) {
        Call <ArrayList<Network_Content>> call2 = NetworkPresenter.getRetrofitService().get_eventContent(eId);
        call2.enqueue(new Callback<ArrayList<Network_Content>>() {
            @Override
            public void onResponse(Call<ArrayList<Network_Content>> call, Response<ArrayList<Network_Content>> response) {
                ArrayList<Network_Content> contentList = MyApplication.ifNull(response.body());

                activity.updateRecyclerView(contentList);
            }

            @Override
            public void onFailure(Call<ArrayList<Network_Content>> call, Throwable t) {

            }
        });
    }
}

