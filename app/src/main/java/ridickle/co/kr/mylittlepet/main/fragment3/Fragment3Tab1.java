package ridickle.co.kr.mylittlepet.main.fragment3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ridickle.co.kr.mylittlepet.DogInfo;
import ridickle.co.kr.mylittlepet.R;
import ridickle.co.kr.mylittlepet.RecyclerViewPresenter;

/**
 * Created by ridickle on 2017. 6. 2..
 */

public class Fragment3Tab1 extends Fragment {

    private Fragment3ListAdapter f3ListAdapter;

    public Fragment3Tab1() {
        // Required empty public constructor
    }

    public static Fragment3Tab1 newInstance() {
        Fragment3Tab1 fragment = new Fragment3Tab1();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab3_first, container, false);

        // setting RecyclerView
        ArrayList<DogInfo> list = new ArrayList<>();

        // 더미 데이터
        list.add(new DogInfo("a","태호시 태호구 태호동",0,"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTW68XNUfjWy2DrPBa65WhxCC7AlOi4VRVmEorGupckpHCuuQn41pzEEF8",0,0));
        list.add(new DogInfo("a","태호시 태호구 태호동",0,"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTW68XNUfjWy2DrPBa65WhxCC7AlOi4VRVmEorGupckpHCuuQn41pzEEF8",0,0));
        list.add(new DogInfo("a","태호시 태호구 태호동",0,"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTW68XNUfjWy2DrPBa65WhxCC7AlOi4VRVmEorGupckpHCuuQn41pzEEF8",0,0));
        list.add(new DogInfo("a","태호시 태호구 태호동",0,"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTW68XNUfjWy2DrPBa65WhxCC7AlOi4VRVmEorGupckpHCuuQn41pzEEF8",0,0));
        list.add(new DogInfo("a","태호시 태호구 태호동",0,"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTW68XNUfjWy2DrPBa65WhxCC7AlOi4VRVmEorGupckpHCuuQn41pzEEF8",0,0));

        f3ListAdapter = new Fragment3ListAdapter(getActivity(), list);
        RecyclerViewPresenter.recyclerViewSetting(getActivity(), (RecyclerView) v.findViewById(R.id.tab3RecyclerView), 2, f3ListAdapter);

        return v;
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
