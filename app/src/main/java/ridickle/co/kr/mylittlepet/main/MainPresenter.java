package ridickle.co.kr.mylittlepet.main;

import android.support.design.widget.TabLayout;

import java.util.ArrayList;

/**
 * Created by ridickle on 2017. 9. 29..
 */

public interface MainPresenter {
    void fragmentSetting(TabLayout tabLayout, MainPresenter.View activity, MainPresenter.Fragment fragment, int flag);

    // activity 에서 사용되는 ui Setting
    void uiSetting(MainPresenter.View activity);

    // fragment 에서 사용되는 ui Setting
    void uiSetting(android.view.View view, MainPresenter.Fragment fragment);

    void f1_3Tag(android.view.View view, Fragment1_3 fragment);
    void clickTag(Fragment1_3 fragment, String tagName);
    void f1_3Info(Fragment1_3 fragment);

//    void f1_5ClickItem(Fragment5 fragment);

    // 3. 액티비티(뷰)에서 실행될 녀석
    public interface View {
        void tabClick(String str);
        void viewSetting();
        void settingUI();
    }

    // 3. 프레그먼트에서 실행될 녀석
    public interface Fragment {
        void viewSetting();
        void settingUI(android.view.View view);
    }

    public interface Fragment1 extends Fragment {

    }

    public interface Fragment1_1 extends Fragment1 {

    }

    public interface Fragment1_2 extends Fragment1 {

    }

    public interface Fragment1_3 extends Fragment1 {
        void tagSetting(android.view.View view, ArrayList<Integer> idList);
        void tagClicked(String tagName);
        void infoSetting(ArrayList<String> dummyList);
    }

    public interface Fragment1_4 extends Fragment1 {

    }

    public interface Fragment2 extends Fragment {
        void seekBarSetting();
    }

    public interface Fragment3 extends Fragment {

    }

    public interface Fragment4 extends Fragment {

    }

    public interface Fragment5 extends Fragment {
//        void itemClicked();

    }

}
