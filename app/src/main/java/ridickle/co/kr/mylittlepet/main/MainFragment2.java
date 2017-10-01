package ridickle.co.kr.mylittlepet.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ridickle.co.kr.mylittlepet.R;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class MainFragment2 extends Fragment implements MainPresenter.Fragment2  {

    public MainFragment2() {
        // Required empty public constructor
    }

    public static MainFragment2 newInstance() {
        MainFragment2 fragment = new MainFragment2();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_fragment2, container, false);
    }
}
