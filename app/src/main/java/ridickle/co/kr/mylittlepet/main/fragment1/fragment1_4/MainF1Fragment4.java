package ridickle.co.kr.mylittlepet.main.fragment1.fragment1_4;

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

public class MainF1Fragment4 extends Fragment implements MainPresenter.Fragment1_4 {
    static MainF1Fragment4 instance;
    MainPresenter mPresenter;
    RecyclerView f1_4RecyclerView;
    Fragment1_4ListAdapter f1_4ListAdapter;

    public MainF1Fragment4() {
        // Required empty public constructor
        mPresenter = MainPresenterImpl.newInstance();
    }

    public static MainF1Fragment4 newInstance() {
        if (instance == null)
            instance = new MainF1Fragment4();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_tab1_fragment4, container, false);

        mPresenter.uiSetting(convertView, this);
        return convertView;
    }

    @Override
    public void viewSetting() {

    }

    @Override
    public void settingUI(View view) {
        f1_4ListAdapter = new Fragment1_4ListAdapter(getActivity());
        RecyclerViewPresenter.recyclerViewSetting(getActivity(), (RecyclerView) view.findViewById(R.id.f1_4RecyclerView), 1, f1_4ListAdapter);
    }
}
