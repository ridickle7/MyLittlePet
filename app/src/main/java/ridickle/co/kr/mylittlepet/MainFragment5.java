package ridickle.co.kr.mylittlepet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class MainFragment5 extends Fragment {

    public MainFragment5() {
        // Required empty public constructor
    }

    public static MainFragment5 newInstance() {
        MainFragment5 fragment = new MainFragment5();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_fragment5, container, false);
    }
}
