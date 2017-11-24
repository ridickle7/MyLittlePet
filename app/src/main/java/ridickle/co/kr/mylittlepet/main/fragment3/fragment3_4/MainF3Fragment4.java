package ridickle.co.kr.mylittlepet.main.fragment3.fragment3_4;

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

public class MainF3Fragment4 extends Fragment implements MainF3_4Presenter.fragment {
    private static final int GRID_NUM = 1;
    private static MainF3Fragment4 instance;
    private MainF3_4Presenter mPresenter;
    private RecyclerView f3_4RecyclerView;
    private Fragment3_4ListAdapter f3_4ListAdapter;
    private View convertView;

    public MainF3Fragment4() {
        // Required empty public constructor
        mPresenter = MainF3_4PresenterImpl.getInstance(this);
    }

    public static MainF3Fragment4 newInstance() {
        if (instance == null)
            instance = new MainF3Fragment4();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        convertView = inflater.inflate(R.layout.fragment_tab3_fragment4, container, false);

        mPresenter.loadItem();
        return convertView;
    }

    @Override
    public void updateView() {
        f3_4RecyclerView = (RecyclerView) convertView.findViewById(R.id.f3_4RecyclerView);
        f3_4ListAdapter = new Fragment3_4ListAdapter(getActivity());
        RecyclerViewPresenter.recyclerViewSetting(getActivity(), f3_4RecyclerView, GRID_NUM, f3_4ListAdapter);
    }
}
