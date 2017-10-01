package ridickle.co.kr.mylittlepet.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nhn.android.maps.NMapCompassManager;
import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.NMapView.OnMapStateChangeListener;
import com.nhn.android.maps.NMapView.OnMapViewTouchEventListener;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

import ridickle.co.kr.mylittlepet.MyApplication;
import ridickle.co.kr.mylittlepet.R;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class MainFragment4 extends Fragment implements MainPresenter.Fragment4  {

    private static final String LOG_TAG = "MainFragment4 : ";
    private static final boolean DEBUG = false;

    private NMapContext mapContext;

    private NMapLocationManager mapLocationManager;
    private NMapController mapController;

    private NMapCompassManager mapCompassManager;

    private NMapOverlayManager overlayManager;
    private NMapViewerResourceProvider mMapViewerResourceProvider;


    private OnMapStateChangeListener onMapStateChangeListener = new OnMapStateChangeListener() {
        NGeoPoint currentPosition;

        @Override
        public void onMapInitHandler(final NMapView mapView, final NMapError errorInfo) {
            if (errorInfo == null) { // success
                // restore map view state such as map center position and zoom level.
                // restore();
                final Handler handler = new Handler();
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (mapLocationManager.isMyLocationFixed()) {
                            currentPosition = mapLocationManager.getMyLocation();
                            mapController.setMapCenter(currentPosition.getLongitude(), currentPosition.getLatitude(), 11);
                        } else {
                            mapLocationManager.enableMyLocation(false);
                            onMapInitHandler(mapView, errorInfo);       // 재귀
                        }
                    }
                };
                handler.postDelayed(runnable, 3000);

            } else { // fail
                Log.e(LOG_TAG, "onMapInitHandler: error=" + errorInfo.toString());
            }
        }

        @Override
        public void onAnimationStateChange(NMapView mapView, int animType, int animState) {
            if (DEBUG) {
                Log.i(LOG_TAG, "onAnimationStateChange: animType=" + animType + ", animState=" + animState);
            }
        }

        @Override
        public void onMapCenterChange(NMapView mapView, NGeoPoint center) {
            if (DEBUG) {
                Log.i(LOG_TAG, "onMapCenterChange: center=" + center.toString());
                mapController.animateTo(center);
            }
        }

        @Override
        public void onZoomLevelChange(NMapView mapView, int level) {
            if (DEBUG) {
                Log.i(LOG_TAG, "onZoomLevelChange: level=" + level);
            }
        }

        @Override
        public void onMapCenterChangeFine(NMapView mapView) {

        }
    };
    private OnMapViewTouchEventListener onMapViewTouchEventListener = new NMapView.OnMapViewTouchEventListener() {
        @Override
        public void onLongPress(NMapView mapView, MotionEvent ev) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onLongPressCanceled(NMapView mapView) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSingleTapUp(NMapView mapView, MotionEvent ev) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTouchDown(NMapView mapView, MotionEvent ev) {

        }

        @Override
        public void onScroll(NMapView mapView, MotionEvent e1, MotionEvent e2) {
        }

        @Override
        public void onTouchUp(NMapView mapView, MotionEvent ev) {
            // TODO Auto-generated method stub

        }

    };
    private NMapLocationManager.OnLocationChangeListener onLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {
        @Override
        public boolean onLocationChanged(NMapLocationManager nMapLocationManager, NGeoPoint myLocation) {
            if (mapController != null) {
                mapController.animateTo(myLocation);
            }

            return true;
        }

        @Override
        public void onLocationUpdateTimeout(NMapLocationManager nMapLocationManager) {
            // stop location updating
            //			Runnable runnable = new Runnable() {
            //				public void run() {
            //					stopMyLocation();
            //				}
            //			};
            //			runnable.run();

            Toast.makeText(getActivity(), "Your current location is temporarily unavailable.", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onLocationUnavailableArea(NMapLocationManager nMapLocationManager, NGeoPoint nGeoPoint) {

            Toast.makeText(getActivity(), "Your current location is unavailable area.", Toast.LENGTH_LONG).show();

            stopMyLocation();
        }
    };

    private NMapMyLocationOverlay myLocationOverlay;
    private NMapPOIdataOverlay.OnStateChangeListener onPOIdataStateChangeListener;
    NMapPOIdataOverlay poiDataOverlay;

    private NMapView mapView;

    /* Local Functions */
    private static boolean mIsMapEnlared = false;

    public MainFragment4() {
        // Required empty public constructor
    }

    public static MainFragment4 newInstance() {
        MainFragment4 fragment = new MainFragment4();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_fragment4, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapContext = new NMapContext(super.getActivity());
        mapContext.onCreate();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mapView = (NMapView) getView().findViewById(R.id.mapView);
        mapView.setClientId(MyApplication.ClientId);// 클라이언트 아이디 설정
        mapContext.setupMapView(mapView);

        // initialize map view
        mapView.setClickable(true);

        // register listener for map state changes
        mapView.setOnMapStateChangeListener(onMapStateChangeListener);

        // register listener for mapView touch event
        mapView.setOnMapViewTouchEventListener(onMapViewTouchEventListener);

        // use map controller to zoom in/out, pan and set map center, zoom level etc.
        mapController = mapView.getMapController();

        // use built in zoom controls
        mapView.setBuiltInZoomControls(true, null);

        mapLocationManager = new NMapLocationManager(super.getActivity());
        mapLocationManager.setOnLocationChangeListener(onLocationChangeListener);

        // compass manager
        mapCompassManager = new NMapCompassManager(getActivity());

        // create resource provider
        mMapViewerResourceProvider = new NMapViewerResourceProvider(super.getActivity());

        // create overlay manager
        overlayManager = new NMapOverlayManager(super.getActivity(), mapView, mMapViewerResourceProvider);

        mapLocationManager.enableMyLocation(false);
    }


    private void startMyLocation() {
        if (myLocationOverlay != null) {
            if (!overlayManager.hasOverlay(myLocationOverlay)) {
                overlayManager.addOverlay(myLocationOverlay);
            }

            if (mapLocationManager.isMyLocationEnabled()) {

                if (!mapView.isAutoRotateEnabled()) {
                    myLocationOverlay.setCompassHeadingVisible(true);

                    mapCompassManager.enableCompass();

                    mapView.setAutoRotateEnabled(true, false);
                } else {
                    stopMyLocation();
                }
                mapView.postInvalidate();
            } else {
                boolean isMyLocationEnabled = mapLocationManager.enableMyLocation(true);
                if (!isMyLocationEnabled) {
                    Toast.makeText(getActivity(), "Please enable a My Location source in system settings",
                            Toast.LENGTH_LONG).show();

                    Intent goToSettings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(goToSettings);

                    return;
                }
            }
        }
    }

    private void stopMyLocation() {
        if (myLocationOverlay != null) {
            mapLocationManager.disableMyLocation();

            if (mapView.isAutoRotateEnabled()) {
                myLocationOverlay.setCompassHeadingVisible(false);

                mapCompassManager.disableCompass();

                mapView.setAutoRotateEnabled(false, false);
            }
        }
    }

    private void createOverLay() {
        int markerId = NMapPOIflagType.PIN;

        // set POI data
        NMapPOIdata poiData = new NMapPOIdata(2, mMapViewerResourceProvider);
        poiData.beginPOIdata(2);        // POI 아이템 추가 시작
        poiData.addPOIitem(127.0630205, 37.5091300, "Pizza 777-111", markerId, 0);
        poiData.addPOIitem(127.061, 37.51, "Pizza 123-456", markerId, 0);
        poiData.endPOIdata();           // POI 아이템 추가 종료

        //POI data overlay 객체 생성(여러 개의 오버레이 아이템을 포함할 수 있는 오버레이 클래스)
        poiDataOverlay = overlayManager.createPOIdataOverlay(poiData, null);
        //  poiDataOverlay.showAllPOIdata(0); //모든 POI 데이터를 화면에 표시(zomLevel)

        settingPOIdataStateChangeListener(poiDataOverlay);
    }

    private void settingPOIdataStateChangeListener(NMapPOIdataOverlay poiDataOverlay) {
        onPOIdataStateChangeListener = new NMapPOIdataOverlay.OnStateChangeListener() {

            @Override
            public void onCalloutClick(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
                if (DEBUG) {
                    Log.i(LOG_TAG, "onCalloutClick: title=" + item.getTitle());
                }

                // [[TEMP]] handle a click event of the callout
                Toast.makeText(getActivity(), "onCalloutClick: " + item.getTitle(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFocusChanged(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
                if (DEBUG) {
                    if (item != null) {
                        Log.i(LOG_TAG, "onFocusChanged: " + item.toString());
                    } else {
                        Log.i(LOG_TAG, "onFocusChanged: ");
                    }
                }
            }
        };

        //POI 아이템이 선택 상태 변경 시 호출되는 콜백 인터페이스 설정
        poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);
        myLocationOverlay = overlayManager.createMyLocationOverlay(mapLocationManager, mapCompassManager);
    }


    @Override
    public void onStart() {
        super.onStart();
        mapContext.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapContext.onResume();

        createOverLay();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapContext.onPause();
    }

    @Override
    public void onStop() {
        mapContext.onStop();
        stopMyLocation();

        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mapContext.onDestroy();
        super.onDestroy();
    }
}
