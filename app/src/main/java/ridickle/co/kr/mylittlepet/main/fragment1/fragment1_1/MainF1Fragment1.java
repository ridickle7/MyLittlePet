package ridickle.co.kr.mylittlepet.main.fragment1.fragment1_1;

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
 * Created by ridickle on 2017. 9. 30..
 */

public class MainF1Fragment1 extends Fragment implements MainPresenter.Fragment1_1 {
    MainPresenter mPresenter;
    static MainF1Fragment1 instance;

    RecyclerView f1_1RecyclerView;
    Fragment1_1ListAdapter f1_1ListAdapter;

    public MainF1Fragment1() {
        // Required empty public constructor
        mPresenter = MainPresenterImpl.newInstance();
    }

    public static MainF1Fragment1 newInstance() {
        if (instance == null)
            instance = new MainF1Fragment1();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_tab1_fragment1, container, false);

        mPresenter.uiSetting(convertView, this);
        return convertView;
    }

    // PresenterImpl.uiSetting -> fragment.settingUI
    @Override
    public void settingUI(View view) {
        f1_1ListAdapter = new Fragment1_1ListAdapter(getActivity(), 0);

        RecyclerViewPresenter.recyclerViewSetting(getActivity(), (RecyclerView) view.findViewById(R.id.f1_1recyclerView), 3, f1_1ListAdapter);
    }

    @Override
    public void viewSetting() {

    }
}
