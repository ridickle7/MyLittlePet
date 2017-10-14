package ridickle.co.kr.mylittlepet.main.fragment1.fragment1_4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.Util.RecyclerViewPresenter;

/**
 * Created by ridickle on 2017. 9. 30..
 */

public class MainF1Fragment4 extends Fragment implements MainF1_4Presenter.fragment {
    private static final int GRID_NUM = 1;
    private static MainF1Fragment4 instance;
    private MainF1_4Presenter mPresenter;
    private RecyclerView f1_4RecyclerView;
    private Fragment1_4ListAdapter f1_4ListAdapter;
    private View convertView;

    public MainF1Fragment4() {
        // Required empty public constructor
        mPresenter = MainF1_4PresenterImpl.getInstance(this);
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
        convertView = inflater.inflate(R.layout.fragment_tab1_fragment4, container, false);

        mPresenter.loadItem();
        return convertView;
    }

    @Override
    public void updateView() {
        f1_4RecyclerView = (RecyclerView) convertView.findViewById(R.id.f1_4RecyclerView);
        f1_4ListAdapter = new Fragment1_4ListAdapter(getActivity());
        RecyclerViewPresenter.recyclerViewSetting(getActivity(), f1_4RecyclerView, GRID_NUM, f1_4ListAdapter);
    }
}
