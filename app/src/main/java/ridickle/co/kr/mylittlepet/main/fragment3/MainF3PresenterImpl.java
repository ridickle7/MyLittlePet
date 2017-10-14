package ridickle.co.kr.mylittlepet.main.fragment3;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF3PresenterImpl implements MainF3Presenter {
    private static MainF3Presenter instance;

    private static MainF3Presenter.fragment fragment;
    private static MainF3Model model;

    public static MainF3Presenter getInstance(fragment fr) {
        if (instance == null) {
            instance = new MainF3PresenterImpl();
        }
        fragment = fr;
        model = new MainF3Model();
        return instance;
    }

    @Override
    public void loadItem() {
        fragment.updateView();

    }
}
