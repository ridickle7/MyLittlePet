package ridickle.co.kr.mylittlepet.detailContent;

import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Content;

/**
 * Created by ridickle on 2017. 10. 14..
 */

public interface DetailContentPresenter {

    void loadItem();

    public interface view{

        void updateView(Network_Content contentItem);
    }
}
