package ridickle.co.kr.mylittlepet.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

    public static <T> ArrayList<T> getJSONArray(String str) {
        JSONArray jArr = null;
        ArrayList<T> returnArr = new ArrayList<>();
        try {
            jArr = new JSONArray(str);
            for(int i=0 ; i<jArr.length() ; i++){
                returnArr.add((T)jArr.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnArr;
    }

}
