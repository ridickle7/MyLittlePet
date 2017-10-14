package ridickle.co.kr.mylittlepet.main.fragment2;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class MainF2PresenterImpl implements MainF2Presenter {
    private static MainF2Presenter instance = null;
    private static MainF2Presenter.fragment fragment;
    private static MainF2Model model;

    public static MainF2Presenter getInstance(MainF2Presenter.fragment fr) {
        if(instance == null){
            instance = new MainF2PresenterImpl();
        }
        fragment = fr;
        model = new MainF2Model();
        return instance;
    }

    @Override
    public void loadItem() {
        fragment.updateView();
    }
}
