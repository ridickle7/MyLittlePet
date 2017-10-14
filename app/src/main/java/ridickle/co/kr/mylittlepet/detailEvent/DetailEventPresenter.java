package ridickle.co.kr.mylittlepet.detailEvent;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Content;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Event;

/**
 * Created by ridickle on 2017. 10. 3..
 */

public interface DetailEventPresenter {

    // activity 에서 사용되는 ui Setting
    void loadEventItem(String eId);

    void setBottomSheet(FloatingActionButton fab, LinearLayout linearLayout);
    void clickFloatButton(BottomSheetBehavior bottomSheetBehavior);

    void loadContentList(String eId);

    public interface view{
        void updateView(Network_Event eventItem);
        void updateRecyclerView(ArrayList<Network_Content> contentList);
        void addClickEvent();
    }
}
