package ridickle.co.kr.mylittlepet.main.fragment5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.RecyclerViewPresenter;
import ridickle.co.kr.mylittlepet.main.MainPresenter;
import ridickle.co.kr.mylittlepet.main.MainPresenterImpl;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class MainFragment5 extends Fragment implements MainPresenter.Fragment5 {
    private MainPresenter mPresenter;
    private static MainFragment5 instance;
    private Fragment5ListAdapter f5Adapter;

    public MainFragment5() {
        // Required empty public constructor
        mPresenter = MainPresenterImpl.newInstance();
    }

    public static MainFragment5 newInstance() {
        if (instance == null)
            instance = new MainFragment5();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_main_fragment5, container, false);
        mPresenter.uiSetting(convertView, this);

        return convertView;
    }

    @Override
    public void viewSetting() {

    }

    @Override
    public void settingUI(View view) {
        f5Adapter = new Fragment5ListAdapter(getActivity());
        RecyclerViewPresenter.recyclerViewSetting(getActivity(), (RecyclerView) view.findViewById(R.id.f5RecyclerView), 1, f5Adapter);
    }
}
