package ridickle.co.kr.mylittlepet.main.fragment3.fragment1;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public interface MainF3_1Presenter {
    void loadItem();

    public interface fragment{
        void updateView(ArrayList<Network_User> userList);
    }
}
