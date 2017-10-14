package ridickle.co.kr.mylittlepet.main.fragment1.fragment1_3;

import android.widget.LinearLayout;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.MyApplication;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF1_3PresenterImpl implements MainF1_3Presenter {
    private static MainF1_3Presenter instance = null;
    private static MainF1_3Presenter.fragment fragment = null;
    private static MainF1_3Model model = null;


    public static synchronized MainF1_3Presenter getInstance(MainF1_3Presenter.fragment fr) {
        if(instance == null){
            instance = new MainF1_3PresenterImpl();
        }
        model = new MainF1_3Model();
        fragment = fr;

        return instance;
    }

    @Override
    public void loadItem() {
        fragment.updateView(MyApplication.getUser());
    }

    @Override
    public ArrayList<Integer> setTag(LinearLayout f1_3TagList) {
        ArrayList<String> dummyList = new ArrayList<>();
        for(int i=0 ; i<12 ; i++)
            dummyList.add("a");

        return model.tagInflating((LinearLayout)f1_3TagList, dummyList);
    }
}
