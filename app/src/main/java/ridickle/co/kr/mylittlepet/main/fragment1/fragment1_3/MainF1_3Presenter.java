package ridickle.co.kr.mylittlepet.main.fragment1.fragment1_3;

import android.widget.LinearLayout;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public interface MainF1_3Presenter {

    void loadItem();

    ArrayList<Integer> setTag(LinearLayout f1_3TagList);

    public interface fragment{
        void updateView(Network_User user);
    }
}
