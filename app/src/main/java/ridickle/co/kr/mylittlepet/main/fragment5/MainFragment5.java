package ridickle.co.kr.mylittlepet.main.fragment5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.Network.DataBody.Network_Event;
import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.Util.RecyclerViewPresenter;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class MainFragment5 extends Fragment implements MainF5Presenter.fragment {
    private MainF5Presenter mPresenter;
    private static MainFragment5 instance;

    private View convertView;
    private RecyclerView f5RecyclerView;
    private Fragment5ListAdapter f5Adapter;

    public MainFragment5() {
        // Required empty public constructor
        mPresenter = MainF5PresenterImpl.getInstance(this);
    }

    public static MainFragment5 getInstance() {
        if (instance == null)
            instance = new MainFragment5();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        convertView = inflater.inflate(R.layout.fragment_main_fragment5, container, false);
        mPresenter.loadItem();

        return convertView;
    }

    @Override
    public void updateView(ArrayList<Network_Event> eventList) {
        f5RecyclerView = (RecyclerView) convertView.findViewById(R.id.f5RecyclerView);
        f5Adapter = new Fragment5ListAdapter(getActivity(), eventList);

        RecyclerViewPresenter.recyclerViewSetting(getActivity(), f5RecyclerView, 1, f5Adapter);
    }
}
