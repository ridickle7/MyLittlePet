package ridickle.co.kr.mylittlepet.main.fragment1.fragment1_4;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF1_4PresenterImpl implements MainF1_4Presenter {
    private static MainF1_4Presenter instance = null;
    private static MainF1_4Model model = null;
    private static MainF1_4Presenter.fragment fragment;

    public static synchronized MainF1_4Presenter getInstance(MainF1_4Presenter.fragment fr) {
        if (instance == null) {
            instance = new MainF1_4PresenterImpl();
        }
        model = new MainF1_4Model();
        fragment = fr;

        return instance;
    }

    @Override
    public void loadItem() {
        fragment.updateView();
    }
}
