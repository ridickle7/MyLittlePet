package ridickle.co.kr.mylittlepet.main.fragment2.MainF2Dialog;

import android.content.Context;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public interface MainF2DialogPresenter {

    void loadItem(Context context, int index);

    public interface fragment{
        void updateView();
    }
}
