package ridickle.co.kr.mylittlepet.main.fragment5;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Event;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public interface MainF5Presenter {
    void loadItem();

    public interface fragment{
        void updateView(ArrayList<Network_Event> eventList);
    }
}
