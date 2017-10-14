package ridickle.co.kr.mylittlepet.main.fragment1.fragment1_1;

import android.widget.LinearLayout;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Content;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public interface MainF1_1Presenter{
    void loadItem();

    void cameraSelectorSetting(LinearLayout mBottomSheet);

    public interface fragment{
        // 해당 Presenter에서 사용할 View 구현
        void updateView(ArrayList<Network_Content> contentList);
    }
}
