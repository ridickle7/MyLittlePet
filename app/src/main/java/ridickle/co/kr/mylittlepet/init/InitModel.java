package ridickle.co.kr.mylittlepet.init;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * Created by ridickle on 2017. 10. 5..
 */

public class InitModel {
    public Address makeUseOfNewLocation(Location location) {
        Log.w("lks", "get location - " + location.getLatitude() + " - " + location.getLongitude());
        Geocoder gCoder = new Geocoder(((InitActivity) InitPresenterImpl.getActivity()), Locale.getDefault());
        List<Address> addr = null;
        try {
            addr = gCoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addr.get(0);
    }
}
