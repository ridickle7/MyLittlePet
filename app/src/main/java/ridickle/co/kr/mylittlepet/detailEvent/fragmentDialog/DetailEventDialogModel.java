package ridickle.co.kr.mylittlepet.detailEvent.fragmentDialog;

import java.io.File;

import ridickle.co.kr.mylittlepet.Util.PhotoPresenter;
import ridickle.co.kr.mylittlepet.MyApplication;

/**
 * Created by ridickle on 2017. 10. 13..
 */

public class DetailEventDialogModel {

    // 아마존으로 사진파일 보냄
    public void sendImageToAmazon(String fileName, File file) {
        file = PhotoPresenter.getInstance().getFile(file);
        MyApplication.sendImage(fileName, file);
    }
}
