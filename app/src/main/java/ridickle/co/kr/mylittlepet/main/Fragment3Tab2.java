package ridickle.co.kr.mylittlepet.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.DogInfo;
import ridickle.co.kr.mylittlepet.R;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class Fragment3Tab2 extends Fragment {

    private Fragment3ListAdapter la;
    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    private RecyclerView tab3RecyclerView;
    private RecyclerView.LayoutManager tab3LayoutManager;

    public Fragment3Tab2() {
        // Required empty public constructor
    }

    public static Fragment3Tab2 newInstance() {
        Fragment3Tab2 fragment = new Fragment3Tab2();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab3_first, container, false);

        // setting RecyclerView
        tab3RecyclerView = (RecyclerView) v.findViewById(R.id.tab3RecyclerView);

        ArrayList<DogInfo> list = new ArrayList<>();

        // 더미 데이터
        list.add(new DogInfo("a","태호시 태호구 태호동",0,"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTW68XNUfjWy2DrPBa65WhxCC7AlOi4VRVmEorGupckpHCuuQn41pzEEF8",0,0));
        list.add(new DogInfo("a","태호시 태호구 태호동",0,"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTW68XNUfjWy2DrPBa65WhxCC7AlOi4VRVmEorGupckpHCuuQn41pzEEF8",0,0));
        list.add(new DogInfo("a","태호시 태호구 태호동",0,"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTW68XNUfjWy2DrPBa65WhxCC7AlOi4VRVmEorGupckpHCuuQn41pzEEF8",0,0));
        list.add(new DogInfo("a","태호시 태호구 태호동",0,"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTW68XNUfjWy2DrPBa65WhxCC7AlOi4VRVmEorGupckpHCuuQn41pzEEF8",0,0));
        list.add(new DogInfo("a","태호시 태호구 태호동",0,"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTW68XNUfjWy2DrPBa65WhxCC7AlOi4VRVmEorGupckpHCuuQn41pzEEF8",0,0));

        la = new Fragment3ListAdapter(getActivity(), list);
        tab3RecyclerView.setAdapter(la);

        tab3LayoutManager = new GridLayoutManager(getActivity(),2);
        mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
        tab3RecyclerView.setLayoutManager(tab3LayoutManager);


        return v;
    }
}
