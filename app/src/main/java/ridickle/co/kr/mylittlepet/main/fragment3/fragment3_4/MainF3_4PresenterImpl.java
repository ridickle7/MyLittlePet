package ridickle.co.kr.mylittlepet.main.fragment3.fragment3_4;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF3_4PresenterImpl implements MainF3_4Presenter {
    private static MainF3_4Presenter instance = null;
    private static MainF3_4Model model = null;
    private static MainF3_4Presenter.fragment fragment;

    public static synchronized MainF3_4Presenter getInstance(MainF3_4Presenter.fragment fr) {
        if (instance == null) {
            instance = new MainF3_4PresenterImpl();
        }
        model = new MainF3_4Model();
        fragment = fr;

        return instance;
    }

    @Override
    public void loadItem() {
        fragment.updateView();
    }
}
