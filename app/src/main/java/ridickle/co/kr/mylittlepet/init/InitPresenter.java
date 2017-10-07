package ridickle.co.kr.mylittlepet.init;

/**
 * Created by ridickle on 2017. 10. 5..
 */

public interface InitPresenter {
    // activity 에서 사용되는 ui Setting
    void uiSetting(InitPresenter.view activity);
    // fragment 에서 사용되는 ui Setting
    void uiSetting(InitPresenter.fragment fragment, int resId);

    interface view{
        void settingUI();
    }

    interface fragment {
        void settingUI(android.view.View view);
    }
}
