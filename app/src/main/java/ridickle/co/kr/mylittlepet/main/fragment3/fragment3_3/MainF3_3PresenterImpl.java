package ridickle.co.kr.mylittlepet.main.fragment3.fragment3_3;

import android.widget.LinearLayout;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.MyApplication;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF3_3PresenterImpl implements MainF3_3Presenter {
    private static MainF3_3Presenter instance = null;
    private static MainF3_3Presenter.fragment fragment = null;
    private static MainF3_3Model model = null;


    public static synchronized MainF3_3Presenter getInstance(MainF3_3Presenter.fragment fr) {
        if(instance == null){
            instance = new MainF3_3PresenterImpl();
        }
        model = new MainF3_3Model();
        fragment = fr;

        return instance;
    }

    @Override
    public void loadItem() {
        fragment.updateView(MyApplication.getUser());
    }

    @Override
    public ArrayList<Integer> setTag(LinearLayout f3_3TagList) {
        ArrayList<String> dummyList = new ArrayList<>();
        for(int i=0 ; i<12 ; i++)
            dummyList.add("a");

        return model.tagInflating((LinearLayout)f3_3TagList, dummyList);
    }
}
