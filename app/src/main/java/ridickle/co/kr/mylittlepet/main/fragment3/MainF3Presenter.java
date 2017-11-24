package ridickle.co.kr.mylittlepet.main.fragment3;

import android.support.design.widget.TabLayout;

import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public interface MainF3Presenter {

    void loadItem();

    TabLayout tabSetting(TabLayout mF3TabLayout);

    public interface fragment {
        void updateView(Network_User user);
    }
}
