package yapp.co.kr.mlp.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import yapp.co.kr.mlp.Helper.MySingleton;
import yapp.co.kr.mlp.R;

/**
 * Created by home on 2015-11-02.
 */
public class SectionsFragment3 extends Fragment {
    RequestQueue mQueue2;
    Bitmap image_bitmap1, image_bitmap2;
    ArrayList<String> temp;

    public SectionsFragment3() {
    }

    private static MapView mMapView;
    private static GoogleMap mMap;
    private static double latitude, longitude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        View rootView = inflater.inflate(R.layout.fragment_page3, container, false);
        // Passing harcoded values for latitude & longitude. Please change as per your need. This is just used to drop a Marker on the Map
        mMapView = (MapView) rootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        MapsInitializer.initialize(this.getActivity());

        mMap = mMapView.getMap();

        //우리집 정보 (더미 데이터)
        latitude = 37.293323;
        longitude = 127.04341;
        setUpMap();

        chkGpsService();        //내 주변 정보 확인
        //frag3_findandMarker();

        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inSampleSize = 4;

        Bitmap bigPictureBitmap1 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.picker_mint);
        image_bitmap1 = Bitmap.createScaledBitmap(bigPictureBitmap1, 100, 133, false);
        Bitmap bigPictureBitmap2 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.boast_ex1);
        image_bitmap2 = Bitmap.createScaledBitmap(bigPictureBitmap2, 70, 70, false);

        image_bitmap1 = overlayMark(image_bitmap1, image_bitmap2, 15, 15);
        temp = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Marker myMarker = mMap.addMarker(new MarkerOptions()            //마커 띄우기
                            .position(new LatLng(37 + 0.2 * i, 127 + 0.2 * i))
                            .title("ridickle" + i)
                            .icon(BitmapDescriptorFactory.fromBitmap(image_bitmap1))
                            .snippet("This is my spot!  " + i)
            );
            temp.add(myMarker.getTitle());
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.d("잘 클릭 된 건가? ", marker.getTitle() + "");


                ThunderFragment dialogFragment = ThunderFragment.newInstance();

                Bundle args = new Bundle();
                args.putString("nickname", marker.getTitle() + "");
                dialogFragment.setArguments(args);

                dialogFragment.setStyle(R.style.Theme_Dialog_Transparent, R.style.Theme_Dialog_Transparent);
                dialogFragment.show(getActivity().getFragmentManager(), "TAG");
                return false;
            }
        });

        return rootView;
    }

    private Bitmap overlayMark(Bitmap bmp1, Bitmap bmp2,int distanceLeft,int distanceTop) {
//        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth() + distanceLeft,
//                bmp1.getHeight() + distanceLeft, bmp1.getConfig());
        Canvas canvas = new Canvas(bmp1);
        canvas.drawBitmap(bmp1, 0, 0, null);
        canvas.drawBitmap(getCircularBitmap(bmp2), distanceLeft, distanceTop, null);
        return bmp1;
    }

    public static Bitmap getCircularBitmap(Bitmap bitmap) {
        Bitmap output;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        } else {
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        float r = 0;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            r = bitmap.getHeight() / 2;
        } else {
            r = bitmap.getWidth() / 2;
        }

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    private boolean chkGpsService() {

        String gps = android.provider.Settings.Secure.getString(this.getActivity().getContentResolver(), android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if (!(gps.matches(".*gps.*") && gps.matches(".*network.*"))) {
            // GPS OFF 일때 Dialog 표시
            final AlertDialog.Builder gsDialog = new AlertDialog.Builder(this.getActivity());
            gsDialog.setTitle("위치 서비스 설정");
            gsDialog.setMessage("무선 네트워크 사용, GPS 위성 사용을 모두 체크하셔야 정확한 위치 서비스가 가능합니다.\n위치 서비스 기능을 설정하시겠습니까?");
            gsDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // GPS설정 화면으로 이동
                    Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    startActivity(intent);
                    dialog.dismiss();
                }
            })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
            return false;

        } else {
            return true;
        }

        //fragment 임시 설정
            /*GpsAcceptFragment dialogFragment = GpsAcceptFragment.newInstance();
            dialogFragment.setStyle(R.style.AppTheme, R.style.AppTheme);
            dialogFragment.show(this.getActivity().getFragmentManager(), "TAG");*/
    }
    /***** Sets up the map if it is possible to do so *****/

    /**
     * This is where we can add markers or lines, add listeners or move the
     * camera.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap}
     * is not null.
     */
    private static void setUpMap() {
        // For showing a move to my loction button
        mMap.setMyLocationEnabled(true);
        // For dropping a marker at a point on the Map
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("My Home").snippet("Home Address"));
        // For zooming automatically to the Dropped PIN Location
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,
                longitude), 12.0f));
    }

//    private static void addMarker(float num_1, float num_2) {
//        final LatLng PERTH = new LatLng(num_1, num_2);
//        Marker perth = mMap.addMarker(new MarkerOptions()
//                .position(PERTH)
//                .draggable(false));
//    }

    public void frag3_findandMarker() {
        // 서버에 뉴스피드 정보 요청
        final String URL_address = "http://52.32.69.220:3000/mypage";
        //mQueue2 = Volley.newRequestQueue(this);       //싱글턴 사용 안할 시
        mQueue2 = MySingleton.getInstance(getActivity()).
                getRequestQueue();

        // Request a string response from the provided URL.
        // 2) Request Obejct인 StringRequest 생성
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_address,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("Response is: ", response.toString());
                        Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG);
                        for (int i = 0; i < 10; i++) {
                            Marker myMarker = mMap.addMarker(new MarkerOptions()            //마커 띄우기
                                            .position(new LatLng(37 + 0.2 * i, 127 + 0.2 * i))
                                            .title("My Spot" + i)
                                            .snippet("This is my spot!  " + i)
                            );
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError) {
                    Log.d("error", error.toString());
                }
                // 네트워크 연결이 모두 끊어진 경우
                else if (error instanceof NoConnectionError) {
                    Log.d("error", error.toString());
                } else if (error instanceof AuthFailureError) {
                    Log.d("error", error.toString());
                }
                // 서버에러, URL에 해당 자료가 없어도 이곳이 불린다.
                else if (error instanceof ServerError) {
                    Log.d("error", error.toString());
                } else if (error instanceof NetworkError) {
                    Log.d("error", error.toString());
                } else if (error instanceof ParseError) {
                    Log.d("error", error.toString());
                }
            }
        });

        // 3) 생성한 StringRequest를 RequestQueue에 추가
        //mQueue2.add(stringRequest);   //싱글턴 사용 안할 시
        MySingleton.getInstance(this.getActivity()).addToRequestQueue(stringRequest);
    }
}
