package ridickle.co.kr.mylittlepet.main.fragment1;

import android.support.design.widget.TabLayout;

import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public interface MainF1Presenter {

    void loadItem();

    TabLayout tabSetting(TabLayout mF1TabLayout);

    public interface fragment {
        void updateView(Network_User user);
    }
}
