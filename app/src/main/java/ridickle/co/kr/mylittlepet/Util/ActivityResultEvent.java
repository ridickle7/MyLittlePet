package ridickle.co.kr.mylittlepet.Util;

import android.content.Intent;

/**
 * Created by ridickle on 2017. 10. 14..
 */

// onActivityResult로 받는 requestCode, resultCode, intent정보를 가지고 fragment에 전달해 주는 용도
public class ActivityResultEvent {
    private int requestCode;
    private int resultCode;
    private Intent data;

    public static ActivityResultEvent create(int requestCode, int resultCode, Intent intent) {
        return new ActivityResultEvent(requestCode, resultCode, intent);
    }

    private ActivityResultEvent(int requestCode, int resultCode, Intent data) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public Intent getData() {
        return data;
    }
}