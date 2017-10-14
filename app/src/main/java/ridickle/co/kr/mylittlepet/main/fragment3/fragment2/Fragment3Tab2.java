package ridickle.co.kr.mylittlepet.main.fragment3.fragment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.Network.DataBody.Network_User;
import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.Util.RecyclerViewPresenter;
import ridickle.co.kr.mylittlepet.main.fragment3.Fragment3ListAdapter;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class Fragment3Tab2 extends Fragment implements MainF3_2Presenter.fragment {

    private static Fragment3Tab2 instance;
    private MainF3_2Presenter mPresenter;

    private View convertView;
    private RecyclerView f3_2RecyclerView;
    private Fragment3ListAdapter f3ListAdapter;

    public Fragment3Tab2() {
        // Required empty public constructor
        mPresenter = MainF3_2PresenterImpl.getInstance(this);
    }

    public static Fragment3Tab2 newInstance() {
        if (instance == null)
            instance = new Fragment3Tab2();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        convertView = inflater.inflate(R.layout.fragment_tab3_second, container, false);

        mPresenter.loadItem();
        return convertView;
    }

    @Override
    public void updateView(ArrayList<Network_User> userList) {
        // setting RecyclerView

        f3_2RecyclerView = (RecyclerView) convertView.findViewById(R.id.tab3RecyclerView);
        f3ListAdapter = new Fragment3ListAdapter(getActivity(), userList);
        RecyclerViewPresenter.recyclerViewSetting(getActivity(), f3_2RecyclerView, 2, f3ListAdapter);
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
