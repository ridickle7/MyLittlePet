package yapp.co.kr.mlp.Fragment;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
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

import java.util.ArrayList;

import yapp.co.kr.mlp.Helper.MySingleton;
import yapp.co.kr.mlp.Item_adapter.MainItemAdapter;
import yapp.co.kr.mlp.Item_item.Pet;
import yapp.co.kr.mlp.R;
import yapp.co.kr.mlp.component.MyPageActivity;

/**
 * Created by home on 2015-11-02.
 */
public class SectionsFragment1 extends Fragment {
    RequestQueue mQueue2;
    static ArrayList<Pet> petList;
    static GridView grid;

    public SectionsFragment1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_page1, container, false);

        Toolbar toolbar1;
        toolbar1 = (Toolbar) rootView.findViewById(R.id.toolbar1);
        View spinnerContainer = LayoutInflater.from(this.getActivity()).inflate(R.layout.toolbar_spinner,
                toolbar1, false);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        toolbar1.addView(spinnerContainer, lp);


        Spinner spinner = (Spinner) spinnerContainer.findViewById(R.id.toolbar_spinner);

        ArrayList<String> subList = new ArrayList<String>();
        subList.add("최신순");
        subList.add("인기순");
        ArrayAdapter<String> adapters = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, subList);
        spinner.setAdapter(adapters);

        petList = new ArrayList<Pet>();

        Pet temp = new Pet();

        like();

        temp.setId(0);
        temp.setName("봄비");
        temp.setGender("중성");
        temp.setBirthday("2015-03-01");
        temp.setSpecific("망아지");
        temp.setIntroduction_write("온순하고 똑부러지는 아이입니다. ");

        petList.add(temp);

        temp = new Pet();

        temp.setId(0);
        temp.setName("랄라");
        temp.setGender("남자");
        temp.setBirthday("1111-11-11");
        temp.setSpecific("강아지");
        temp.setIntroduction_write("저녁에는 3발로 다니고 오른쪽 발의 IQ는 430입니다.");

        petList.add(temp);

        temp = new Pet();

        temp.setId(0);
        temp.setName("공포마");
        temp.setGender("남자");
        temp.setBirthday("2015-02-23");
        temp.setSpecific("강아지");
        temp.setIntroduction_write("아침엔 개짖는 소리를 내고, 저녁에는 닭 우는 소리를 냅니다.");
        petList.add(temp);

        grid = (GridView) rootView.findViewById(R.id.grid);
        MainItemAdapter adapter = new MainItemAdapter(this.getActivity(), R.layout.list_pet, petList);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(this., "출력됬다" + position, Toast.LENGTH_LONG);
                Log.v("출력됬다", position + "");
                Intent intent = new Intent(getActivity(), MyPageActivity.class);
                intent.putExtra("showpage", "temp");
                startActivity(intent);
            }
        });
        return rootView;
    }
    public void like() {
        // 서버에 뉴스피드 정보 요청
        final String URL_address = "http://52.32.69.220:3000/mypage";
        //mQueue2 = Volley.newRequestQueue(this);       //싱글턴 사용 안할 시
        mQueue2 = MySingleton.getInstance(this.getActivity()).
                getRequestQueue();

        // Request a string response from the provided URL.
        // 2) Request Obejct인 StringRequest 생성
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_address,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("fr1 Response is: ", response.toString());
                        Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG);
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
        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

}