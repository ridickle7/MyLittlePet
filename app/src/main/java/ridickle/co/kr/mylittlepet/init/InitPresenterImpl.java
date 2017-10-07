package ridickle.co.kr.mylittlepet.init;

/**
 * Created by ridickle on 2017. 10. 5..
 */

public class InitPresenterImpl implements InitPresenter {
    static InitPresenterImpl instance;

    public static synchronized InitPresenterImpl newInstance() {
        if (instance == null) {
            instance = new InitPresenterImpl();
        }
        return instance;
    }

    @Override
    public void uiSetting(InitPresenter.view activity) {
        activity.settingUI();
    }

    @Override
    public void uiSetting(InitPresenter.fragment fragment, int resId) {

    }
}
