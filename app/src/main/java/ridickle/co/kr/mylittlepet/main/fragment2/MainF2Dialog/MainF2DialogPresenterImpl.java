package ridickle.co.kr.mylittlepet.main.fragment2.MainF2Dialog;

import android.content.Context;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF2DialogPresenterImpl implements MainF2DialogPresenter {
    private static MainF2DialogPresenter instance = null;
    private static MainF2DialogPresenter.fragment fragment;
    private static MainF2DialogModel model;

    public static MainF2DialogPresenter getInstance(MainF2DialogPresenter.fragment fr) {
        if(instance == null){
            instance = new MainF2DialogPresenterImpl();
        }
        fragment = fr;
        model = new MainF2DialogModel();
        return instance;
    }

    @Override
    public void loadItem(Context context, int index) {
        fragment.updateView();
    }
}
