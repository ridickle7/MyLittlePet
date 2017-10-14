package ridickle.co.kr.mylittlepet.init;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import java.io.File;

import ridickle.co.kr.mylittlepet.Util.PhotoPresenter;
import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;

/**
 * Created by ridickle on 2017. 10. 5..
 */

public class InitPresenterImpl implements InitPresenter {
    private static InitPresenterImpl instance;
    private static InitPresenter.view activity;
    private static InitModel iModel = null;

    private LocationListener locationListener;
    private Address address;
    private float latitude, longitude;


    public static synchronized InitPresenterImpl newInstance(InitPresenter.view view) {
        if (instance == null) {
            instance = new InitPresenterImpl();
        }
        activity = view;
        iModel = new InitModel();
        return instance;
    }

    public static view getActivity() {
        return activity;
    }

    @Override
    public void uiSetting() {
        setLocation();

    }

    @Override
    public void ivClick(Intent intent) {
        PhotoPresenter.getInstance().setBitmap(((InitActivity) activity).getApplicationContext(), intent);

        activity.clickingIv(PhotoPresenter.getInstance().getBitmap());
    }

    @Override
    public void buttonClick(Network_User user) {
        //create a file to write bitmap data
        File file = new File(((InitActivity) activity).getCacheDir(), user.getuNickname());
        file = PhotoPresenter.getInstance().getFile(file);
        MyApplication.sendImage(user.getuNickname() + ".png", file);

        activity.clickButton(user);
    }


    private void setLocation() {
        // Acquire a reference to the system Location Manager
        final LocationManager locationManager = (LocationManager) ((InitActivity) activity).getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // 콜백함수로 위치값이 업데이트 되면 호출된다.
                latitude = (float) location.getLatitude();
                longitude = (float) location.getLongitude();
                address = iModel.makeUseOfNewLocation(location);

                activity.settingUI(latitude, longitude, address);
                locationManager.removeUpdates(locationListener);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        if (ActivityCompat.checkSelfPermission(((InitActivity) activity), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(((InitActivity) activity), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 0, locationListener);
    }
}
