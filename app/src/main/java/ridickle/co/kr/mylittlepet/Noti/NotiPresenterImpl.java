package ridickle.co.kr.mylittlepet.Noti;

/**
 * Created by ridickle on 2017. 9. 29..
 */

public class NotiPresenterImpl implements NotiPresenter {
    private static NotiPresenterImpl instance;
    private static view activity;
    private static NotiModel model;

    public static NotiPresenter getInstance(view view) {
        if(instance == null){
            instance = new NotiPresenterImpl();
        }
        activity = view;
        model = new NotiModel();
        return instance;
    }

    @Override
    public void loadItem() {
        activity.updateView();
    }
}
