package ridickle.co.kr.mylittlepet.main.fragment4;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF4PresenterImpl implements MainF4Presenter {
    private static MainF4Presenter instance;

    private static MainF4Presenter.fragment fragment;
    private static MainF4Model model;

    public static MainF4Presenter getInstance(MainF4Presenter.fragment fr) {
        if(instance == null){
            instance = new MainF4PresenterImpl();
        }
        fragment = fr;
        model = new MainF4Model();
        return instance;
    }

    @Override
    public void loadItem() {
        fragment.updateView();
    }
}
