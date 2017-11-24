package ridickle.co.kr.mylittlepet.main.fragment1.fragment1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.DogInfo;
import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;
import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.Util.RecyclerViewPresenter;
import ridickle.co.kr.mylittlepet.main.fragment1.Fragment1ListAdapter;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class Fragment1Tab1 extends Fragment implements MainF1_1Presenter.fragment{

    private static Fragment1Tab1 instance;
    private MainF1_1Presenter mPresenter;

    private View convertView;
    private RecyclerView f1_2RecyclerView;
    private Fragment1ListAdapter f1ListAdapter;


    public Fragment1Tab1() {
        // Required empty public constructor
        mPresenter = MainF1_1PresenterImpl.getInstance(this);
    }

    public static Fragment1Tab1 newInstance() {
        if (instance == null)
            instance = new Fragment1Tab1();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        convertView = inflater.inflate(R.layout.fragment_tab1_first, container, false);

        mPresenter.loadItem();
        return convertView;
    }

    @Override
    public void updateView(ArrayList<Network_User> userList) {
        // setting RecyclerView
        ArrayList<DogInfo> list = new ArrayList<>();

        f1_2RecyclerView = (RecyclerView) convertView.findViewById(R.id.tab1RecyclerView);
        f1ListAdapter = new Fragment1ListAdapter(getActivity(), userList);
        RecyclerViewPresenter.recyclerViewSetting(getActivity(), f1_2RecyclerView, 2, f1ListAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
