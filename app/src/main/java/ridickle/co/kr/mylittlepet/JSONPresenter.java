package ridickle.co.kr.mylittlepet;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ridickle on 2017. 10. 7..
 */

public class JSONPresenter {
    public static JSONObject getJSONObject(String str) {
        JSONObject jObj = null;
        try {
            jObj = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jObj;
    }

    public static JSONObject getJSONObject(String str, String key) {
        JSONObject jObj = null;
        try {
            jObj = new JSONObject(str).getJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jObj;
    }
}
