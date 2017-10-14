package ridickle.co.kr.mylittlepet.detailEvent.fragmentDialog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Content;

/**
 * Created by ridickle on 2017. 10. 13..
 */

public interface DetailEventDialogPresenter {

    void loadItem(Context context, Intent intent);
    void clickOk(String eId, String cText);

    public interface fragment{
        void updateView(Bitmap bitmap);
        void executeOk(Network_Content contentItem);
    }
}
