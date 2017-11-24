package ridickle.co.kr.mylittlepet.main.fragment1;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF1PresenterImpl implements MainF1Presenter {
    private static MainF1Presenter instance;

    private static MainF1Presenter.fragment fragment;
    private static MainF1Model model;

    public static MainF1Presenter getInstance(fragment fr) {
        if (instance == null) {
            instance = new MainF1PresenterImpl();
        }
        fragment = fr;
        model = new MainF1Model();
        return instance;
    }

    @Override
    public void loadItem() {
        fragment.updateView();

    }
}
