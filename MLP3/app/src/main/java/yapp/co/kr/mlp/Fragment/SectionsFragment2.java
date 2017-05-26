package yapp.co.kr.mlp.Fragment;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import yapp.co.kr.mlp.Helper.MySingleton;
import yapp.co.kr.mlp.Item_adapter.BoastAdapter;
import yapp.co.kr.mlp.Item_item.Boast;
import yapp.co.kr.mlp.R;
import yapp.co.kr.mlp.component.BoastActivity;

/**
 * Created by home on 2015-11-02.
 */
public class SectionsFragment2 extends Fragment {
    RequestQueue mQueue2;

    ListView listView01;
    BoastAdapter adapter01;

    ArrayList<String> stitle = new ArrayList<>();
    ArrayList<String> startdate = new ArrayList<>();
    ArrayList<String> enddate = new ArrayList<>();
    //ArrayList<String> photo = new ArrayList<>();
    ArrayList<Drawable> photo = new ArrayList<>();

    int item_num;
    JSONArray jarray;
    JSONObject jObject;
    Bitmap bitmap;

    public SectionsFragment2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        photo.add(getResources().getDrawable(R.mipmap.boast_ex1));
        photo.add(getResources().getDrawable(R.mipmap.boast_ex2));
        photo.add(getResources().getDrawable(R.mipmap.boast_ex3));

        View rootView = inflater.inflate(R.layout.fragment_page2,
                container, false);

        Toolbar toolbar2;
        toolbar2 = (Toolbar) rootView.findViewById(R.id.toolbar2);
        View spinnerContainer = LayoutInflater.from(this.getActivity()).inflate(R.layout.toolbar_spinner,
                toolbar2, false);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        toolbar2.addView(spinnerContainer, lp);


        Spinner spinner = (Spinner) spinnerContainer.findViewById(R.id.toolbar_spinner);

        ArrayList<String> subList = new ArrayList<String>();
        subList.add("최신순");
        subList.add("인기순");
        ArrayAdapter<String> adapters = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, subList);
        spinner.setAdapter(adapters);

        fragment2_get1();
        listView01 = (ListView) rootView.findViewById(R.id.listView_main);

        //listView01.setFocusable(false);

        listView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BoastActivity.class);
                //intent.putExtra("name","mike");
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {

        }
    }

    public void fragment2_get1() {
        // 서버에 뉴스피드 정보 요청

        final String URL_address = "http://52.32.69.220:3000/showpage";
        final String imgURL_address = "http://imgnews.naver.com/image/092/2015/04/30/1JoQeLJIa9nBJ2F5n5Bu_99_20150430111706.jpg";
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
                        Log.d("fr2 Response is: ", response.toString());
                        Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG);

                        try {
                            ArrayList<Boast> list = new ArrayList<>();
                            jarray = new JSONArray(response);

                            for (int i = 0; i < jarray.length(); i++) {
                                jObject = jarray.getJSONObject(i);

                                stitle.add(jObject.getString("stitle"));
                                Log.d("fr2 stitle is: ", jObject.getString("stitle"));
                                startdate.add(jObject.getString("startdate"));
                                enddate.add(jObject.getString("enddate"));

                                //new Streaming().execute(null, null, null); new Boast(new BitmapDrawable(getResources(), bitmap)

                                Resources res = getResources();
                                list.add(new Boast(photo.get(i),
                                        stitle.get(i), startdate.get(i) + " ~ " + enddate.get(i), res.getDrawable(R.mipmap.progressing)));

                                adapter01 = new BoastAdapter(getActivity(), R.layout.list_sub, list);
                                listView01.setAdapter(adapter01);
                            }

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
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


/*

            Response.Listener<Bitmap> image_listener = new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap arg0) {
                    // TODO Auto-generated method stub
                    image.setImageBitmap(arg0);
                }
            };
            Response.ErrorListener image_err_listener = new Response.ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    image.setImageResource(R.mipmap.ic_launcher);
                }
            };

*/

        //ImageRequest는 익명 리스너 사용이 안된다.
//            ImageRequest imageRequest = new ImageRequest(imgURL_address, image_listener, 0, 0, null, image_err_listener);

        // 3) 생성한 StringRequest를 RequestQueue에 추가
        //mQueue2.add(stringRequest);   //싱글턴 사용 안할 시
        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
        //           MySingleton.getInstance(getActivity()).addToRequestQueue(imageRequest);
    }

    //AsyncTask<Params,Progress,Result>
    private class Streaming extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL("http://52.32.69.220:3000/var/www/aws/aws-yapp/dev/dev/Server/" + jObject.getString("photo"));
                //URL url = new URL("http://54.92.98.215:3000/home/ec2-user/image.jpg");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.connect();

                InputStream is = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}