package ridickle.co.kr.mylittlepet.Noti;

/**
 * Created by ridickle on 2017. 9. 29..
 */

public interface NotiPresenter {

    void loadItem();

    // 3. 액티비티(뷰)에서 실행될 녀석
    public interface view {
        void updateView();
    }
}
