package ridickle.co.kr.mylittlepet.main;

import android.support.design.widget.TabLayout;

/**
 * Created by ridickle on 2017. 9. 29..
 */

public interface MainPresenter {

    void loadItem();
    TabLayout tabSetting(TabLayout tabLayout);

    // 3. 액티비티(뷰)에서 실행될 녀석
    public interface view {
        void updateView();

        void tabClick(String tabIndex);
    }
}
