package ridickle.co.kr.mylittlepet.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ridickle.co.kr.mylittlepet.R;

/**
 * Created by ridickle on 2017. 9. 30..
 */

public class MainF1Fragment1 extends Fragment implements MainPresenter.Fragment1_1 {
    MainPresenter mPresenter;
    RecyclerView f1_1RecyclerView;

    public MainF1Fragment1() {
        // Required empty public constructor
        mPresenter = MainPresenterImpl.newInstance(getActivity(), this);
    }

    public static MainF1Fragment1 newInstance() {
        MainF1Fragment1 fragment = new MainF1Fragment1();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_tab1_fragment1, container, false);
        f1_1RecyclerView = (RecyclerView) convertView.findViewById(R.id.f1_1recyclerView);

        return convertView;
    }

    @Override
    public void viewSetting() {

    }
}
