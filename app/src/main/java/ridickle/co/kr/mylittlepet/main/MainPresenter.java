package ridickle.co.kr.mylittlepet.main;

import android.support.design.widget.TabLayout;

/**
 * Created by ridickle on 2017. 9. 29..
 */

public interface MainPresenter {
    void tabClickEvent(TabLayout.Tab tab, int position);

    void fragmentSetting(TabLayout tabLayout, int flag);

    // 3. 액티비티(뷰)에서 실행될 녀석
    public interface View {
        void tabClickEvent(String str);

        void viewSetting();
    }

    // 3. 프레그먼트에서 실행될 녀석
    public interface Fragment {
    }

    public interface Fragment1 extends Fragment {
        void viewSetting();
    }

    public interface Fragment1_1 extends Fragment1 {

    }

    public interface Fragment1_2 extends Fragment1 {

    }

    public interface Fragment1_3 extends Fragment1 {

    }

    public interface Fragment1_4 extends Fragment1 {

    }

    public interface Fragment2 extends Fragment {

    }

    public interface Fragment3 extends Fragment {

    }

    public interface Fragment4 extends Fragment {

    }

    public interface Fragment5 extends Fragment {

    }

}
