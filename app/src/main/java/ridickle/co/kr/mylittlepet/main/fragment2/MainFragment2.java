package ridickle.co.kr.mylittlepet.main.fragment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.Util.RecyclerViewPresenter;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class MainFragment2 extends Fragment implements MainF2Presenter.fragment {
    static MainFragment2 instance;
    MainF2Presenter mPresenter;

    private View convertView;
    public static TextView initTextView;
    int seekBarNum = 2;

    public MainFragment2() {
        // Required empty public constructor
        mPresenter = MainF2PresenterImpl.getInstance(this);
    }

    public static MainFragment2 getInstance() {
        if (instance == null)
            instance = new MainFragment2();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        convertView = inflater.inflate(R.layout.fragment_main_fragment2, container, false);
        mPresenter.loadItem();

        return convertView;
    }

    @Override
    public void updateView() {
        RecyclerView f2RecyclerView = (RecyclerView) convertView.findViewById(R.id.f2SearchList);
        Fragment2ListAdapter f2Adapter = new Fragment2ListAdapter(getActivity());
        initTextView = (TextView) convertView.findViewById(R.id.f2InitText);
        RecyclerViewPresenter.recyclerViewSetting(getActivity(), f2RecyclerView, 1, f2Adapter);
    }
}
