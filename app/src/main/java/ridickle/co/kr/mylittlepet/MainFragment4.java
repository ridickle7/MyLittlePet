package ridickle.co.kr.mylittlepet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class MainFragment4 extends Fragment {

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
}
